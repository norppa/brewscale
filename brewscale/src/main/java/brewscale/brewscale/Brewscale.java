/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author jtthaavi@cs
 */
public class Brewscale {

    private Resepti resepti;

    public Brewscale(Resepti resepti) {
        this.resepti = resepti;
    }

    public Brewscale() {
        this(null);
    }

    public void setResepti(Resepti uusi) {
        this.resepti = uusi;
    }

    public Resepti getResepti() {
        return resepti;
    }

    public void tallenna() {
        BrewWriter bw = new BrewWriter();
        bw.tallennaResepti(resepti.toString(), reseptiTeksti());
    }
    
    public void lataa(String tiedostonimi) {
        BrewWriter bw = new BrewWriter();
        resepti = bw.lueResepti(tiedostonimi);
    }

    public void skaalaa(double kerroin) {
        if (kerroin < 0) {
            return;
        }

        resepti.setKoko(resepti.getKoko() * kerroin);
        for (Aines a : resepti.getAinekset()) {
            a.setMaara(a.getMaara() * kerroin);
        }
    }

    public void muutaGrammoiksi() {
        for (Aines a : resepti.getAinekset()) {
            a.setMaara(a.getMaara() * yksikkoMuuntoKerroin(a.getYksikko(), "g"));
            a.setYksikko("g");
        }
    }
    
    private double yksikkoMuuntoKerroin(String alku, String loppu) {
        double unssi = 28.35;
        double pauna = 453.60;

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
        return 0;
    }

    public void lueResepti() {
        try {
            File reseptikansio = new File("./reseptit");
            File[] reseptit = reseptikansio.listFiles();
            for (File resepti : reseptit) {
                if (resepti.isFile()) {
                    System.out.println(resepti.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            System.out.println("Reseptikansio puuttuu");
        }
    }

    public String reseptiTeksti() {
        String reseptiTeksti = ""
                + "Nimi: " + resepti.getNimi() + "\n"
                + "Koko: " + resepti.getKoko() + "\n"
                + "Yksikkö: " + resepti.getKokoYksikko() + "\n"
                + "\nMaltaat:\n";
        for (Mallas m : resepti.getMaltaat()) {
            reseptiTeksti += m.getMaara() + " " + m.getYksikko() + " " + m.getNimi() + "\n";
        }
        reseptiTeksti += "\nHumalat:\n";
        for (Humala h : resepti.getHumalat()) {
            reseptiTeksti += h.getMaara() + " " + h.getYksikko() + " " + h.getNimi() + "\n";
        }
        reseptiTeksti += "\nMuut ainekset:\n";
        for (Aines a : resepti.getMuutAinekset()) {
            reseptiTeksti += a.getMaara() + " " + a.getYksikko() + " " + a.getNimi() + "\n";
        }
        reseptiTeksti += "\nMuistiinpanoja:\n" + resepti.muistiinpanot();

        return reseptiTeksti;
    }

}
