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
public class TekoOhje {
    private double keittoaika, maskayslampo, maskaysaika;
    
    public TekoOhje() {
        
    }
    
    public void setKeittoaika(double keittoaika) {
        this.keittoaika = keittoaika;
    }
    
    public void setMaskayslampo(double maskayslampo) {
        this.maskayslampo = maskayslampo;
    }
    
    public void setMaskaysaika(double maskaysaika) {
        this.maskaysaika = maskaysaika;
    }
    
    public double getKeittoaika() {
        return keittoaika;
    }
    
    public double getMaskayslampo() {
        return maskayslampo;
    }
    
    public double getMaskaysaika() {
        return maskaysaika;
    }
}
