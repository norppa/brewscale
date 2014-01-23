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
public class Humala extends Aines {
    
    private double alphaAcid;
    
    public Humala(String nimi, double maara, int yksikko, double alphaAcid) {
        super(nimi, maara, yksikko);
        this.alphaAcid = alphaAcid;
    }
    
    public double getAlphaAcid() {
        return alphaAcid;
    }
    
    
    
}
