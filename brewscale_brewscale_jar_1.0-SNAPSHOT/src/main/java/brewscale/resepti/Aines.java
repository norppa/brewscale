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
public class Aines implements RaakaAine {

    private String nimi, yksikko;
    private double maara;

    public Aines(String nimi, double maara, String yksikko) {
        this.nimi = nimi;
        if (maara < 0) {
            maara = 0;
        }
        this.maara = maara;
        this.yksikko = yksikko;
    }

    public Aines(String nimi, double maara) {
        this(nimi, maara, "g");
    }
    
    public void setMaara(double uusiMaara) {
        this.maara = uusiMaara;
    }
    
    public void setYksikko(String yksikko) {
        this.yksikko = yksikko;
    }

    public String getNimi() {
        return nimi;
    }

    public double getMaara() {
        return maara;
    }
    
    public String getYksikko() {
        return yksikko;
    }

}
