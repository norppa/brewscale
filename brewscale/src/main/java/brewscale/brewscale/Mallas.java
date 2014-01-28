/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brewscale.brewscale;

/**
 *
 * @author jtthaavi@cs
 */
public class Mallas extends Aines {
    
    public Mallas(String nimi, double maara, int yksikko) {
        super(nimi, maara, yksikko);
    }
    
    public Mallas(String nimi, double maara) {
        this(nimi, maara, 0);
    }
    
}
