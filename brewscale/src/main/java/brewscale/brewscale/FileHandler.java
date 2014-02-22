/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FileHandler hoitaa kaiken tiedostojen lukemiseen ja kirjoittamiseen liittyvän.
 *
 * @author Jari Haavisto
 */
public class FileHandler {

    /**
     * Reseptihakemiston (työhakemiston) suhteellinen osoite
     */
    private String hakemisto;

    public FileHandler() {
        hakemisto = "./reseptit/";
    }
    
    /**
     * Tarkistaa onko parametrina annetun niminen tiedosto olemassa reseptikansiossa.
     * 
     * @param nimi Tiedoston nimi.
     * @return True jos tiedosto on olemassa, muuten false.
     */
    public boolean onkoOlemassa(String nimi) {
        File tiedosto = new File(hakemisto + nimi);
        return tiedosto.exists();
    }

    /**
     * Tallentaa tiedostoon annetun tekstin. Jos tiedosto on jo olemassa, se
     * korvataan.
     *
     * @param nimi Tiedosto, johon teksti tallennetaan
     * @param teksti Tallennettava teksti
     */
    public void tallennaResepti(String nimi, String teksti) {
        try {
            File tiedosto = new File(hakemisto + nimi);
            tiedosto.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tiedosto))) {
                writer.write(teksti);
            }

        } catch (IOException e) {
        }
    }

    /**
     * Lukee tekstimuotoisena annetun tekstitiedoston ja palauttaa
     * Resepti-olion.
     *
     * @param nimi Tiedoston nimi
     * @return Resepti-olio
     */
    public Resepti lueResepti(String nimi) {
        File tiedosto = new File(hakemisto + nimi);
        return lueResepti(tiedosto);
    }

    /**
     * Lukee parametrinä saadun tiedoston ja palauttaa sen pohjalta rakennetun
     * Resepti-olion.
     *
     * @param tiedosto Tiedoston nimi
     * @return Resepti-olio
     */
    public Resepti lueResepti(File tiedosto) {
        Resepti resepti = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
            String rivi = reader.readLine();
            String[] otsikko = erotteleOtsikko(rivi);

            resepti = new Resepti(otsikko[0], Double.parseDouble(otsikko[1]), otsikko[2]);

            reader.readLine();
            reader.readLine();

            rivi = reader.readLine();
            while (!rivi.equals("")) {
                String[] osat = erotteleRivi(rivi);
                resepti.lisaaMallas(osat[2], osat[0], osat[1]);
                rivi = reader.readLine();
            }

            reader.readLine();
            rivi = reader.readLine();

            while (!rivi.equals("")) {
                String[] osat = erotteleRivi(rivi);
                int n = osat[2].length() - 1;
                int p = 0;
                while (osat[2].charAt(n) != '(') {
                    if (osat[2].charAt(n) == '%') {
                        p = n;
                    }
                    n--;
                }
                String humalaNimi = osat[2].substring(0, n);
                double humalaAlphaAcid = Double.parseDouble(osat[2].substring(n + 1, p));
                resepti.lisaaHumala(new Humala(humalaNimi, Double.parseDouble(osat[0]), osat[1], humalaAlphaAcid));
                rivi = reader.readLine();
            }

            reader.readLine();
            rivi = reader.readLine();
            while (!rivi.equals("")) {
                String[] osat = erotteleRivi(rivi);
                resepti.lisaaMuuAines(new MuuAines(osat[2], Double.parseDouble(osat[0]), osat[1]));
                rivi = reader.readLine();
            }

            reader.readLine();
            String ohjeet = "";
            rivi = reader.readLine();
            while (!rivi.equals("")) {
                ohjeet += rivi + "\n";
                rivi = reader.readLine();
            }
            resepti.setOhje(ohjeet);

            reader.readLine();
            String muistiinpanot = "";
            rivi = reader.readLine();
            while (rivi != null) {
                muistiinpanot += rivi + "\n";
                rivi = reader.readLine();
            }
            resepti.setMuistiinpanot(muistiinpanot);

            reader.close();
        } catch (IOException e) {

        }

        return resepti;

    }

    /**
     * Erottelee tiedoston otsikkorivistä reseptin nimen, koon ja yksikön.
     *
     * @param rivi Kolmiarvoinen String array, jossa nimi, koko ja yksikkö.
     * @return
     */
    private String[] erotteleOtsikko(String rivi) {
        String[] osat = new String[3];  // nimi, koko, yksikko
        rivi = rivi.trim();
        int i = rivi.length() - 1;
        if (rivi.charAt(i) != ')') {
            return null;
        }
        while (rivi.charAt(i) != '(') {
            i--;
            if (i == 0) {
                return null;
            }
        }
        osat[0] = rivi.substring(0, i - 1);
        int j = i + 2;
        while (rivi.charAt(j) != ' ') {
            j++;
            if (j == rivi.length()) {
                return null;
            }
        }
        osat[1] = rivi.substring(i + 1, j);
        osat[2] = rivi.substring(j + 1, rivi.length() - 1);

        return osat;
    }

    /**
     * Erottelee tiedoston rivistä aineksen määrän, yksikön ja muut tiedot
     *
     * @param rivi Kolmiarvoinen String array, jossa määrä, yksikkö ja muut
     * tiedot
     * @return
     */
    private String[] erotteleRivi(String rivi) {
        String[] osat = new String[3]; // määrä, yksikkö, nimi
        int n = 0;
        int i = 0;
        int j = 0;

        while (i < rivi.length() && n < 2) {
            if (rivi.charAt(i) == ' ') {
                osat[n] = rivi.substring(j, i);
                j = i + 1;
                n++;
            }
            i++;
        }

        osat[2] = rivi.substring(j, rivi.length());
        return osat;
    }

    public void setHakemisto(String hakemisto) {
        this.hakemisto = hakemisto;
    }

}
