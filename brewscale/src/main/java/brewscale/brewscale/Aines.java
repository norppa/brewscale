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
    private int yksikko; // 0 = g, 1 = oz, 2 = lb

    public Aines(String nimi, double maara, int yksikko) {
        this.nimi = nimi;
        if (maara < 0) {
            maara = 0;
        }
        this.maara = maara;
        this.yksikko = yksikko;
    }

    public Aines(String nimi, double maara) {
        this(nimi, maara, 0);
    }
    
    public void setMaara(double uusiMaara) {
        this.maara = uusiMaara;
    }
    
    public void setYksikko(int yksikko) {
        this.yksikko = yksikko;
    }

    public String getNimi() {
        return nimi;
    }

    public double getMaara() {
        return maara;
    }
    
    public int getYksikko() {
        return yksikko;
    }

}
