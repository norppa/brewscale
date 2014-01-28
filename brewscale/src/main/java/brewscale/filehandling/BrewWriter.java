/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.filehandling;

import brewscale.brewscale.Aines;
import brewscale.brewscale.Humala;
import brewscale.brewscale.Mallas;
import brewscale.brewscale.Resepti;
import java.io.BufferedWriter;
import java.io.File;
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

    public void setHakemisto(String hakemisto) {
        this.hakemisto = hakemisto;
    }

}
