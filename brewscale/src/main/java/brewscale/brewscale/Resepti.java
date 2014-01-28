/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

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
    
    public Resepti(Resepti kloonattava) {
        this(kloonattava.getNimi(), kloonattava.getKoko(), kloonattava.getKokoYksikko());
        this.muistiinpanot = kloonattava.muistiinpanot();
        this.ainekset = new ArrayList<Aines>();
        this.maltaat = new ArrayList<Mallas>();
        ArrayList<Mallas> klooniMaltaat = kloonattava.getMaltaat();
        for (Mallas m: klooniMaltaat) {
            Mallas uusiMallas = new Mallas(m.getNimi(), m.getMaara(), m.getYksikko());
            maltaat.add(uusiMallas);
            ainekset.add(uusiMallas);
        }
        this.humalat = new ArrayList<Humala>();
        ArrayList<Humala> klooniHumalat = kloonattava.getHumalat();
        for(Humala h: klooniHumalat) {
            Humala uusiHumala = new Humala(h.getNimi(), h.getMaara(), h.getYksikko(), h.getAlphaAcid());
            humalat.add(uusiHumala);
            ainekset.add(uusiHumala);
        }
        this.muutAinekset = new ArrayList<Aines>();
        ArrayList<Aines> klooniAinekset = kloonattava.getMuutAinekset();
        for (Aines a: klooniAinekset) {
            Aines uusiAines = new Aines(a.getNimi(), a.getMaara(), a.getYksikko());
            muutAinekset.add(uusiAines);
            ainekset.add(uusiAines);
        }
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setKoko(double koko) {
        this.koko = koko;
    }
    
    public void setKokoYksikko(String kokoYksikko) {
        this.kokoYksikko = kokoYksikko;
    }

    public void lisaaMallas(Mallas lisattava) {
        maltaat.add(lisattava);
    }

    public void lisaaHumala(Humala lisattava) {
        humalat.add(lisattava);
    }
    
    public void korvaaHumala(int numero, String nimi, double alphaAcid) {
        double aau = humalat.get(numero).laskeAAU();
        double maaraUnsseina = aau / alphaAcid;
        double maara = maaraUnsseina * 28.3495231;
        Humala uusiHumala = new Humala(nimi, maara, 0, alphaAcid);
        humalat.remove(numero);
        humalat.add(numero, uusiHumala);
    }
    
    public void lisaaAines(Aines lisattava) {
        muutAinekset.add(lisattava);
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
