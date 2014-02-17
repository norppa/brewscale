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
public class Humala extends Aines {
    
    private double alphaAcid;
    
    public Humala(String nimi, double maara, String yksikko, double alphaAcid) {
        super(nimi, maara, yksikko);
        this.alphaAcid = alphaAcid;
    }
    
    public Humala(String nimi, double maara, double alphaAcid) {
        this(nimi, maara, "g", alphaAcid);
    }
    
    public double getAlphaAcid() {
        return alphaAcid;
    }
    
    public double laskeAAU() {
        double unssiMaara = this.getMaara();
        if (this.getYksikko().equals("g")){
            unssiMaara = unssiMaara / 28.3495231;
        }
        if (this.getYksikko().equals("lbs")) {
            unssiMaara = unssiMaara * 16;
        }
        double aau = this.alphaAcid * unssiMaara;
        return aau;
    }
    
    
    
}
