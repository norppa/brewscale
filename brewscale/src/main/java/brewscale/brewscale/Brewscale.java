/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.*;
import java.io.File;
import java.io.IOException;

/**
 * Brewscale on ohjelman toiminnallisuuden pääluokka,
 *
 * @author Jari Haavisto
 */
public class Brewscale {

    /**
     * Luokan Resepti instanssi jota Brewscale käsittelee
     *
     * @see brewscale.resepti.Resepti
     */
    private Resepti resepti;

    public Brewscale(Resepti resepti) {
        this.resepti = resepti;
    }

    public Brewscale() {
        this(new Resepti("", 0, "l"));
    }

    public void setResepti(Resepti uusi) {
        this.resepti = uusi;
    }

    public Resepti getResepti() {
        return resepti;
    }

    /**
     * Tallentaa aktiivisen reseptin tiedostoon. Käyttää FileHandler-luokkaa.
     *
     * @see brewscale.brewscale.FileHandler
     */
    public void tallenna() {
        FileHandler bw = new FileHandler();
        bw.tallennaResepti(resepti.toString(), reseptiTeksti());
    }

    /**
     * Lataa reseptin tiedostosta.
     *
     * @param tiedostonimi Ladattavan tiedoston nimi.
     */
    public void lataa(String tiedostonimi) {
        FileHandler bw = new FileHandler();
        resepti = bw.lueResepti(tiedostonimi);
    }

    /**
     * Metodi skaalaa reseptin kaikki raaka-aineet annetun kertoimen mukaisesti.
     * Mikäli kerroin on negatiivinen, metodi ei tee mitään
     *
     * @param kerroin Annettu kerroin
     */
//    public void skaalaa(double kerroin) {
//        if (kerroin < 0) {
//            return;
//        }
//
//        resepti.setKoko(resepti.getKoko() * kerroin);
//        for (Aines a : resepti.getAinekset()) {
//            a.setMaara(a.getMaara() * kerroin);
//        }
//    }

    /**
     * Metodi skaalaa aktiivisen reseptin kaikki raaka-aineet annettuun
     * lopputilavuuteen
     *
     * @param loppuTilavuus haluttu lopputilavuus
     * @param loppuYksikko yksikkö, jolla lopputilavuus on annettu
     */
    public void skaalaa(double loppuTilavuus, String loppuYksikko) {
        double kerroin = loppuTilavuus / resepti.getKoko() / yksikkoMuuntoKerroin(resepti.getKokoYksikko(), loppuYksikko);
        resepti.setKoko(loppuTilavuus);
        resepti.setKokoYksikko(loppuYksikko);
        for (Aines a : resepti.getAinekset()) {
            a.setMaara(a.getMaara() * kerroin);
        }
    }

    /**
     * Muuttaa aktiivisen reseptin yksiköt grammoiksi.
     */
    public void muutaGrammoiksi() {
        for (Aines a : resepti.getAinekset()) {
            a.setMaara(a.getMaara() * yksikkoMuuntoKerroin(a.getYksikko(), "g"));
            a.setYksikko("g");
        }
    }

    /**
     * Laskee muuntokertoimen muunnettaessa yksiköstä toiseen
     *
     * @param alku Yksikkö josta muutetaan.
     * @param loppu Yksikkö johon muutetaan.
     * @return Muuntokerroin.
     */
    private double yksikkoMuuntoKerroin(String alku, String loppu) {
        double unssi = 28.35;
        double pauna = 453.60;
        double gallona = 4.546;

        if (alku.equals(loppu)) {
            return 1;
        }
        if (loppu.equals("g") && alku.equals("oz")) {
            return unssi;
        }
        if (loppu.equals("g") && alku.equals("lbs")) {
            return pauna;
        }
        if (loppu.equals("oz") && alku.equals("g")) {
            return 1 / unssi;
        }
        if (loppu.equals("oz") && alku.equals("lbs")) {
            return 16;
        }
        if (loppu.equals("lbs") && alku.equals("g")) {
            return 1 / pauna;
        }
        if (loppu.equals("lbs") && alku.equals("oz")) {
            return 1 / 16;
        }

        if (loppu.equals("l") && alku.equals("gal")) {
            return gallona;
        }
        if (loppu.equals("gal") && alku.equals("l")) {
            return 1 / gallona;
        }

        return -1;
    }

    /**
     * Muotoilee reseptistä tekstimuotoisen esityksen, joka sisältää kaikki
     * reseptin tiedot
     *
     * @return Reseptin tekstimuotoinen esitys
     */
    public String reseptiTeksti() {
        String reseptiTeksti = resepti.getNimi() + " (" + resepti.getKoko() + " " + resepti.getKokoYksikko() + ")\n\nMaltaat:\n";
        for (Mallas m : resepti.getMaltaat()) {
            reseptiTeksti += m.getMaara() + " " + m.getYksikko() + " " + m.getNimi() + "\n";
        }
        reseptiTeksti += "\nHumalat:\n";
        for (Humala h : resepti.getHumalat()) {
            reseptiTeksti += h.getMaara() + " " + h.getYksikko() + " " + h.getNimi() + " (" + h.getAlphaAcid() + "%AA)\n";
        }
        reseptiTeksti += "\nMuut ainekset:\n";
        for (MuuAines a : resepti.getMuutAinekset()) {
            reseptiTeksti += a.getMaara() + " " + a.getYksikko() + " " + a.getNimi() + "\n";
        }
        reseptiTeksti += "\nOhjeet:\n" + resepti.getOhje();
        reseptiTeksti += "\n\nMuistiinpanoja:\n" + resepti.getMuistiinpanot();

        return reseptiTeksti;
    }

}
