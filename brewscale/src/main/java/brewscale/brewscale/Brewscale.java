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
        Resepti uusiResepti = new Resepti(resepti.getNimi(), resepti.getKoko() * kerroin);

        ArrayList<Mallas> maltaat = resepti.getMaltaat();
        for (Mallas aines : maltaat) {
            uusiResepti.lisaaMallas(new Mallas(aines.getNimi(), aines.getMaara() * kerroin));
        }

        ArrayList<Humala> humalat = resepti.getHumalat();
        for (Humala aines : humalat) {
            uusiResepti.lisaaHumala(new Humala(aines.getNimi(), aines.getMaara() * kerroin, aines.getAlphaAcid()));
        }

        ArrayList<Aines> aineslista = resepti.getMuutAinekset();
        for (Aines aines : aineslista) {
            uusiResepti.lisaaAines(new Mallas(aines.getNimi(), aines.getMaara() * kerroin));
        }

        return uusiResepti;
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
