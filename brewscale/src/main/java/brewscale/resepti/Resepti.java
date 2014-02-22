/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

import java.util.ArrayList;

/**
 * Resepti-olio pitää sisällään kaikki tiedot yhdestä reseptistä.
 *
 * @author Jari Haavisto
 */
public class Resepti {

    /**
     * Reseptin nimi.
     */
    private String nimi;
    /**
     * Reseptin koko.
     */
    private double koko;
    /**
     * Yksikkö, jossa reseptin koko on ilmoitettu.
     */
    private String kokoYksikko;
    /**
     * Lista reseptissä käytettävistä maltaista.
     */
    private ArrayList<Mallas> maltaat;
    /**
     * Lista reseptissä käytettävistä humalista.
     */
    private ArrayList<Humala> humalat;
    /**
     * Lista reseptissä käytettävistä muista aineista.
     */
    private ArrayList<MuuAines> muutAinekset;
    /**
     * Lista kaikista reseptin aineista. Pitää sisällään kaikki maltaat, humalat ja muut aineet.
     */
    private ArrayList<Aines> ainekset;
    /**
     * Reseptin ohje, eli varsinainen reseptiosa.
     */
    private String ohje;
    /**
     * Reseptiin tehdyt muistiinpanot.
     */
    private String muistiinpanot;

    public Resepti(String nimi, double koko, String kokoYksikko) {
        this.nimi = nimi;
        if (koko < 0) {
            koko = 0;
        }
        this.koko = koko;
        this.kokoYksikko = kokoYksikko;
        this.maltaat = new ArrayList<Mallas>();
//        this.maltaat = new ArrayList<RaakaAine>();
        this.humalat = new ArrayList<Humala>();
        this.muutAinekset = new ArrayList<MuuAines>();
        this.ainekset = new ArrayList<Aines>();
        this.ohje = "";
        this.muistiinpanot = "";
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

    /**
     * Asettaa yksikön jolla reseptin koko ilmaistaan. Ainoat kelvolliset arvot
     * ovat "l" ja "gal".
     *
     * @param kokoYksikko
     */
    public void setKokoYksikko(String kokoYksikko) {
        if (!kokoYksikko.equals("l") && !kokoYksikko.equals("gal")) {
            return;
        }
        this.kokoYksikko = kokoYksikko;
    }

    /**
     * Lisää reseptiin yhden maltaan. Mallas lisätään sekä maltaiden että
     * ainesten listaan.
     *
     * @param lisattava Lisättävä mallas.
     */
    public void lisaaMallas(Mallas lisattava) {
        maltaat.add(lisattava);
        ainekset.add(lisattava);
    }

    /**
     * Luo uuden maltaan ja lisää sen reseptiin käyttäen metodia lisaaMallas().
     *
     * @param nimi Luotavan maltaan nimi.
     * @param maara Luotavan maltaan määrä.
     * @param yksikko Yksikkö, jonka avulla määrä on esitetty.
     */
    public void lisaaMallas(String nimi, String maara, String yksikko) {
        Double maaraDbl = muutaNumeroksi(maara);
        if (maaraDbl == null) {
            return;
        }
        lisaaMallas(new Mallas(nimi, maaraDbl, yksikko));
    }

    /**
     * Lisää reseptiin yhden humalan. Humala lisätään sekä humalien että
     * ainesten listaan.
     *
     * @param lisattava Lisättävä humala.
     */
    public void lisaaHumala(Humala lisattava) {
        humalat.add(lisattava);
        ainekset.add(lisattava);
    }

    /**
     * Luo uuden humalan ja lisää sen reseptiin käyttäen metodia lisaaHumala().
     *
     * @param nimi Luotavan humalan nimi.
     * @param maara Luotavan humalan määrä.
     * @param yksikko Yksikkö, jonka avulla määrä on esitetty.
     * @param alpha Humalan alpha-happopitoisuus (%).
     */
    public void lisaaHumala(String nimi, String maara, String yksikko, String alpha) {
        Double maaraDbl = muutaNumeroksi(maara);
        Double alphaDbl = muutaNumeroksi(alpha);
        if (maaraDbl == null || alphaDbl == null) {
            return;
        }
        lisaaHumala(new Humala(nimi, maaraDbl, yksikko, alphaDbl));
    }

    /**
     * Korvaa reseptistä yhden humalan. Uuden humalan määrä lasketaan
     * automaattisesti siten että alpha-happojen määrä pysyy vakiona.
     * 
     * Ei implementoitu.
     *
     * @param numero Korvattavan humalan numero humalien listassa.
     * @param nimi Uuden humalan nimi.
     * @param alphaAcid Uuden humalan alpha-happopitoisuus (%).
     */
//    public void korvaaHumala(int numero, String nimi, double alphaAcid) {
//        double aau = humalat.get(numero).laskeAAU();
//        double maaraUnsseina = aau / alphaAcid;
//        double maara = maaraUnsseina * 28.3495231;
//        maara = Math.round(maara * 100) / 100;
//        Humala uusiHumala = new Humala(nimi, maara, "g", alphaAcid);
//        humalat.remove(numero);
//        humalat.add(numero, uusiHumala);
//    }

    /**
     * Lisää aktiiviseen reseptiin uuden aineksen. Aines lisätään sekä muiden
     * ainesten että kaikkien ainesten listaan.
     *
     * @param lisattava Lisättävä aines.
     */
    public void lisaaMuuAines(MuuAines lisattava) {
        muutAinekset.add(lisattava);
        ainekset.add(lisattava);
    }
    /**
     * Luo uuden aineksen ja lisaa sen aktiiviseen reseptiin käyttäen metodia lisaaMuuAines(MuuAines)
     * 
     * @param nimi uuden aineksen nimi
     * @param maara uuden aineksen määrä
     * @param yksikko uuden aineksen yksikko
     */
    public void lisaaMuuAines(String nimi, String maara, String yksikko) {
        Double maaraDbl = muutaNumeroksi(maara);
        if (maaraDbl == null) {
            return;
        }
        MuuAines uusiAines = new MuuAines(nimi, maaraDbl, yksikko);
        lisaaMuuAines(uusiAines);
    }

    /**
     * Tarkistaa, että annettu String voidaan muuttaa muotoon Double.
     * 
     * @param numero muutettava numero String-muodossa
     * @return numero Double-muodossa tai null, mikäli muutosta ei voitu tehdä
     */
    private Double muutaNumeroksi(String numero) {
        try {
            return Double.parseDouble(numero);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Tyhjentää kaikki aineslistat
     */
    public void tyhjennaAinekset() {
        maltaat.clear();
        humalat.clear();
        muutAinekset.clear();
        ainekset.clear();
    }

    public ArrayList<Mallas> getMaltaat() {
        return maltaat;
    }

    public ArrayList<Humala> getHumalat() {
        return humalat;
    }

    public ArrayList<MuuAines> getMuutAinekset() {
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

    public void setMuistiinpanot(String muistiinpanot) {
        this.muistiinpanot = muistiinpanot.trim();
    }

    public String getMuistiinpanot() {
        return muistiinpanot;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje.trim();
    }

    public String getOhje() {
        return ohje;
    }

    /**
     * Antaa String-muotoisen esityksen, jossa on reseptin nimi ja koko.
     * 
     * @return String-muotoinen esitys reseptistä.
     */
    public String toString() {
        return nimi + " (" + koko + " " + kokoYksikko + ")";
    }

}
