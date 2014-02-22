/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

/**
 * Abstrakti yläluokka, jonka kaikki erilliset ainekset perivät.
 *
 * @author Jari Haavisto
 */
public abstract class Aines {

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
