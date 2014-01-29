/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.filehandling;

import brewscale.resepti.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author riha
 */
public class BrewWriter {

    private String hakemisto;

    public BrewWriter() {
        hakemisto = "./reseptit/";
    }

    public void tallennaResepti(String nimi, String teksti) {
        try {
            File tiedosto = new File(hakemisto + nimi);
            tiedosto.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tiedosto))) {
//                System.out.println(muotoileReseptiTiedosto(resepti));
                writer.write(teksti);
            }

        } catch (IOException e) {
        }
    }

    public Resepti lueResepti(String nimi) {
        Resepti resepti = null;
        File tiedosto = new File(hakemisto + nimi);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
            String reseptinNimi = reader.readLine();
            if (!reseptinNimi.startsWith("Nimi: ")) {
                return null;
            }
            reseptinNimi = reseptinNimi.substring(6);

            String reseptinKoko = reader.readLine();
            if (!reseptinKoko.startsWith("Koko: ")) {
                return null;
            }
            double reseptinKokoDbl = Double.parseDouble(reseptinKoko.substring(6));

            String reseptinKokoYksikko = reader.readLine();
            if (!reseptinKokoYksikko.startsWith("Yksikkö: ")) {
                return null;
            }
            reseptinKokoYksikko = reseptinKokoYksikko.substring(9);

            resepti = new Resepti(reseptinNimi, reseptinKokoDbl, reseptinKokoYksikko);

            reader.readLine();
            reader.readLine();

            String rivi = reader.readLine();
            while (!rivi.equals("")) {
                System.out.println(rivi);
                String[] osat = erotteleRivi(rivi);
                resepti.lisaaMallas(new Mallas(osat[2], Double.parseDouble(osat[0]), osat[1]));
                rivi = reader.readLine();
            }
            
            reader.close();
        } catch (IOException e) {

        }

        return resepti;

    }

    private String[] erotteleRivi(String rivi) {
        String[] osat = new String[3];
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
