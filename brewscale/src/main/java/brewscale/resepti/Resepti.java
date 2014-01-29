/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

import java.util.ArrayList;

/**
 *
 * @author jtthaavi@cs
 */
public class Resepti {

    private String nimi, muistiinpanot;
    private double koko;
    private String kokoYksikko;
    private ArrayList<Mallas> maltaat;
    private ArrayList<Humala> humalat;
    private ArrayList<Aines> muutAinekset;
    private ArrayList<Aines> ainekset;

    public Resepti(String nimi, double koko, String kokoYksikko) {
        this.nimi = nimi;
        if (koko < 0) {
            koko = 0;
        }
        this.koko = koko;
        this.kokoYksikko = kokoYksikko;
        this.muistiinpanot = "";
        this.maltaat = new ArrayList<Mallas>();
        this.humalat = new ArrayList<Humala>();
        this.muutAinekset = new ArrayList<Aines>();
        this.ainekset = new ArrayList<Aines>();
    }

    public Resepti(String nimi, double koko) {
        this(nimi, koko, "l");
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKoko(double koko) {
        this.koko = koko;
    }

    public void setKokoYksikko(String kokoYksikko) {
        if (!kokoYksikko.equals("l") && !kokoYksikko.equals("gal")) {
            return;
        }
        this.kokoYksikko = kokoYksikko;
    }

    public void lisaaMallas(Mallas lisattava) {
        maltaat.add(lisattava);
        ainekset.add(lisattava);
    }

    public void lisaaHumala(Humala lisattava) {
        humalat.add(lisattava);
        ainekset.add(lisattava);
    }

    public void korvaaHumala(int numero, String nimi, double alphaAcid) {
        double aau = humalat.get(numero).laskeAAU();
        double maaraUnsseina = aau / alphaAcid;
        double maara = maaraUnsseina * 28.3495231;
        maara = Math.round(maara * 100) / 100;
        Humala uusiHumala = new Humala(nimi, maara, "g", alphaAcid);
        humalat.remove(numero);
        humalat.add(numero, uusiHumala);
    }

    public void lisaaAines(Aines lisattava) {
        muutAinekset.add(lisattava);
        ainekset.add(lisattava);
    }

    public void lisaaMuistiinpano(String mp) {
        muistiinpanot += mp + "\n";
    }

    public ArrayList<Mallas> getMaltaat() {
        return maltaat;
    }

    public ArrayList<Humala> getHumalat() {
        return humalat;
    }

    public ArrayList<Aines> getMuutAinekset() {
        return muutAinekset;
    }

    public ArrayList<Aines> getAinekset() {
        return ainekset;
    }

    public String getNimi() {
        return nimi;
    }

    public double getKoko() {
        return koko;
    }

    public String getKokoYksikko() {
        return kokoYksikko;
    }

    public String muistiinpanot() {
        return muistiinpanot;
    }

    public String toString() {
        String str = nimi + " (" + koko + " " + kokoYksikko + ")";
        return str;
    }

}
