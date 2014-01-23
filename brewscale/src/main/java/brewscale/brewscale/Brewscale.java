/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

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

    public Resepti skaalaa(double kerroin) {

        if (kerroin < 0) {
            return resepti;
        }

        Resepti uusiResepti = new Resepti(resepti.getNimi(), resepti.getKoko() * kerroin);

        ArrayList<Mallas> maltaat = resepti.getMaltaat();
        for (Mallas aines : maltaat) {
            uusiResepti.lisaaMallas(new Mallas(aines.getNimi(), aines.getMaara() * kerroin, aines.getYksikko()));
        }

        ArrayList<Humala> humalat = resepti.getHumalat();
        for (Humala aines : humalat) {
            uusiResepti.lisaaHumala(new Humala(aines.getNimi(), aines.getMaara() * kerroin, aines.getYksikko(), aines.getAlphaAcid()));
        }

        ArrayList<Aines> aineslista = resepti.getMuutAinekset();
        for (Aines aines : aineslista) {
            uusiResepti.lisaaAines(new Mallas(aines.getNimi(), aines.getMaara() * kerroin, aines.getYksikko()));
        }

        return uusiResepti;
    }

    public Resepti muutaGrammoiksi() {

        Resepti uusiResepti = new Resepti(resepti.getNimi(), resepti.getKoko() * yksikkomuuntokerroin(resepti.getKokoYksikko(), 0));

        ArrayList<Mallas> maltaat = resepti.getMaltaat();
        for (Mallas aines : maltaat) {
            uusiResepti.lisaaMallas(new Mallas(aines.getNimi(), aines.getMaara() * yksikkomuuntokerroin(aines.getYksikko(), 0), 0));
        }

        ArrayList<Humala> humalat = resepti.getHumalat();
        for (Humala aines : humalat) {
            uusiResepti.lisaaHumala(new Humala(aines.getNimi(), aines.getMaara() * yksikkomuuntokerroin(aines.getYksikko(), 0), 0, aines.getAlphaAcid()));
        }

        ArrayList<Aines> aineslista = resepti.getMuutAinekset();
        for (Aines aines : aineslista) {
            uusiResepti.lisaaAines(new Mallas(aines.getNimi(), aines.getMaara() * yksikkomuuntokerroin(aines.getYksikko(), 0), 0));
        }

        return uusiResepti;
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

    public void tallennaUusiResepti(Resepti r) {
        try {
            String tiedostonimi = "./reseptit/" + r + ".txt";
            File uusiResepti = new File(tiedostonimi);
            uusiResepti.createNewFile();
        } catch (IOException e) {
            System.out.println("I/O error tapahtui");
        }
    }

}
