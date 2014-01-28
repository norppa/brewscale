/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.filehandling.BrewWriter;
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

    public void uusiResepti(Resepti uusi) {
        this.resepti = uusi;
    }

    public Resepti getResepti() {
        return resepti;
    }

    public void tallenna() {
        BrewWriter bw = new BrewWriter();
        bw.tallennaResepti(resepti.toString(), reseptiTeksti());
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
            a.setMaara(a.getMaara() * yksikkomuuntokerroin(a.getYksikko(), 0));
            a.setYksikko(0);
        }
    }

    private double yksikkomuuntokerroin(int alku, int loppu) {
        double unssi = 28.3495231;
        double pauna = 453.59237;

        if (alku == loppu) {
            return 1;
        }
        if (loppu == 0 && alku == 1) {
            return unssi;
        }
        if (loppu == 0 && alku == 2) {
            return pauna;
        }
        if (loppu == 1 && alku == 0) {
            return 1 / unssi;
        }
        if (loppu == 1 && alku == 2) {
            return 16;
        }
        if (loppu == 2 && alku == 0) {
            return 1 / pauna;
        }
        if (loppu == 2 && alku == 1) {
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
        String reseptiTeksti = resepti.toString() + "\n"
                + "\nMaltaat:\n";
        for (Mallas m : resepti.getMaltaat()) {
            reseptiTeksti += m.getMaara() + " " + m.getNimi() + "\n";
        }
        reseptiTeksti += "\nHumalat:\n";
        for (Humala h : resepti.getHumalat()) {
            reseptiTeksti += h.getMaara() + " " + h.getNimi() + "\n";
        }
        reseptiTeksti += "\nMuut ainekset:\n";
        for (Aines a : resepti.getMuutAinekset()) {
            reseptiTeksti += a.getMaara() + " " + a.getNimi() + "\n";
        }
        reseptiTeksti += "\nMuistiinpanoja:\n" + resepti.muistiinpanot();

        return reseptiTeksti;
    }

}
