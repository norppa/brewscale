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
    
    public Humala(String nimi, double maara, double alphaAcid) {
        this(nimi, maara, 0, alphaAcid);
    }
    
    public double getAlphaAcid() {
        return alphaAcid;
    }
    
    public double laskeAAU() {
        double unssiMaara = this.getMaara();
        if (this.getYksikko() == 0){
            unssiMaara = unssiMaara / 28.3495231;
        }
        if (this.getYksikko() == 2) {
            unssiMaara = unssiMaara * 16;
        }
        double aau = this.alphaAcid * unssiMaara;
        return aau;
    }
    
    
    
}
