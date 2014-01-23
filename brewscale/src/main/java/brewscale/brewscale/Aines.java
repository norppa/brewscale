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
public class Aines {
    
    private String nimi;
    private double maara;
    private int yksikko;
    
    public Aines(String nimi, double maara) {
        this.nimi = nimi;
        this.maara = maara;
        this.yksikko = 0;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public double getMaara() {
        return maara;
    }
    
}
