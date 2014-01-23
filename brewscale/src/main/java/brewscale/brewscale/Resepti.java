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
    private ArrayList<Mallas> maltaat;
    private ArrayList<Humala> humalat;
    private ArrayList<Aines> muutAinekset;
    private ArrayList<Aines> ainekset;

    public Resepti(String nimi, double koko) {
        this.nimi = nimi;
        this.koko = koko;
        this.muistiinpanot = "";
        this.maltaat = new ArrayList<Mallas>();
        this.humalat = new ArrayList<Humala>();
        this.muutAinekset = new ArrayList<Aines>();
        this.ainekset = new ArrayList<Aines>();

    }

    public void lisaaMallas(Mallas lisattava) {
        maltaat.add(lisattava);
    }

    public void lisaaHumala(Humala lisattava) {
        humalat.add(lisattava);
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

    public String getNimi() {
        return nimi;
    }

    public double getKoko() {
        return koko;
    }

    public String reseptiTeksti() {
        String reseptiTeksti = toString() + "\n"
                + "Maltaat:\n";
        for (Mallas m : maltaat) {
            reseptiTeksti += m.getMaara() + " " + m.getNimi() + "\n";
        }
        reseptiTeksti += "Humalat:\n";
        for (Humala h : humalat) {
            reseptiTeksti += h.getMaara() + " " + h.getNimi() + "\n";
        }
        reseptiTeksti += "Muut ainekset:\n";
        for (Aines a : muutAinekset) {
            reseptiTeksti += a.getMaara() + " " + a.getNimi() + "\n";
        }
        
        return reseptiTeksti;
    }

    public String toString() {
        return nimi + " (" + koko + " l)";
    }
}
