/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brewscale.resepti;

/**
 *
 * @author jtthaavi@cs
 */
public class Mallas extends Aines {
    
        public Mallas(String nimi, double maara, String yksikko) {
        super(nimi, maara, yksikko);
    }

    public Mallas(String nimi, double maara) {
        super(nimi, maara, "g");
    }

}
