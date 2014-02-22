/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

/**
 * Jokaisesta reseptiin käytettävästä aineksesta, joka ei ole mallas tai humala, tehdään oma
 * MuuAines-luokan edustaja.
 *
 * @author Jari Haavisto
 */
public class MuuAines extends Aines {

    public MuuAines(String nimi, double maara, String yksikko) {
        super(nimi, maara, yksikko);
    }

    public MuuAines(String nimi, double maara) {
        super(nimi, maara, "g");
    }
}
