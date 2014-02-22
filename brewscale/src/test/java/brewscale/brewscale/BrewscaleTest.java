/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.*;
import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author jtthaavi@cs
 */
public class BrewscaleTest extends TestCase {

    Resepti resepti;
    Brewscale brewscale;

    public BrewscaleTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        resepti = new Resepti("Testiresepti", 10);
        brewscale = new Brewscale(resepti);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testKonstruktoriToimiiIlmanReseptia() {
        Brewscale br = new Brewscale();
        assertTrue(br != null);
    }
    
    public void testKonstruktoriToimiiReseptilla() {
        assertTrue( brewscale != null);
    }
    
    public void testTallennaLuoTiedoston() {
        brewscale.tallenna();
        String path = "./reseptit/" + resepti.toString();
        File file = new File(path);
        assertTrue(file.exists());
        file.delete();
    }

    /*
    Testi olettaa, että reseptit-kansiossa on olemassa validi reseptitiedosto "Esimerkkiresepti (10 l)".
    */
    public void testLataaAktivoiReseptin() {
       brewscale.lataa("Esimerkkiresepti (10.0 l)");
       assertEquals("Esimerkkiresepti", brewscale.getResepti().getNimi());
    }

    public void testSkaalainToimiiVaarillaYksikoilla() {
        brewscale.skaalaa(20, "h");
        assertEquals(10.0, brewscale.getResepti().getKoko(), 0.001);
    }
    
    public void testSkaalainToimii() {
        brewscale.skaalaa(20, "l");
        assertEquals(20.0, brewscale.getResepti().getKoko(), 0.001);
    }
    
    public void testSkaalainMuuttaaAinesmaarat() {
        lisaaAineita("g");
        brewscale.skaalaa(8, "l");
        assertEquals(8, resepti.getMaltaat().get(0).getMaara(), 0.001);
        assertEquals(8, resepti.getHumalat().get(0).getMaara(), 0.001);
        assertEquals(8, resepti.getMuutAinekset().get(0).getMaara(), 0.001);
    }
    
    public void testSkaalainMuuttaaYksikon() {
        lisaaAineita("g");
        brewscale.skaalaa(8, "gal");
        assertTrue(brewscale.getResepti().getKokoYksikko().equals("gal"));
    }
    
    private void lisaaAineita(String yksikko) {
        resepti.lisaaMallas(new Mallas("Mallas 1", 10, yksikko));
        resepti.lisaaHumala(new Humala("Humala 1", 10, yksikko, 5.0));
        resepti.lisaaMuuAines(new MuuAines("Muu Aines 1", 10, yksikko));
    }

    public void testMuutaGrammoiksiToimiiGrammoilla() {
        lisaaAineita("g");
        brewscale.muutaGrammoiksi();
        assertTrue(resepti.getHumalat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMaltaat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMuutAinekset().get(0).getYksikko().equals("g"));
    }

    public void testMuutaGrammoiksiToimiiUnsseilla() {
        lisaaAineita("oz");
        brewscale.muutaGrammoiksi();
        assertTrue(resepti.getHumalat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMaltaat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMuutAinekset().get(0).getYksikko().equals("g"));
    }

    public void testMuutaGrammoiksiToimiiPaunoilla() {
        lisaaAineita("lbs");
        brewscale.muutaGrammoiksi();
        assertTrue(resepti.getHumalat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMaltaat().get(0).getYksikko().equals("g"));
        assertTrue(resepti.getMuutAinekset().get(0).getYksikko().equals("g"));
    }
    
    public void testMuutaGrammoiksiMuuttaaGrammatOikein() {
        lisaaAineita("g");
        brewscale.muutaGrammoiksi();
        assertEquals(10.0, brewscale.getResepti().getMaltaat().get(0).getMaara());
    }
    
    public void testMuutaGrammoiksiMuuttaaUnssitOikein() {
        lisaaAineita("oz");
        brewscale.muutaGrammoiksi();
        assertEquals(283.5, brewscale.getResepti().getMaltaat().get(0).getMaara());
    }
    
    public void testMuutaGrammoiksiMuuttaaPaunatOikein() {
        lisaaAineita("lbs");
        brewscale.muutaGrammoiksi();
        assertEquals(4536.0, brewscale.getResepti().getMaltaat().get(0).getMaara());
    }
    
    public void testReseptiTeksti() {
        lisaaAineita("g");
        brewscale.getResepti().setOhje("Testiohje");
        brewscale.getResepti().setMuistiinpanot("Testimuistiinpanot");
        String toivottu = "Testiresepti (10.0 l)\n\n" +
                "Maltaat:\n10.0 g Mallas 1\n\n" +
                "Humalat:\n10.0 g Humala 1 (5.0%AA)\n\n" +
                "Muut ainekset:\n10.0 g Muu Aines 1\n\n" +
                "Ohjeet:\nTestiohje\n\nMuistiinpanoja:\nTestimuistiinpanot";
        assertTrue(toivottu.equals(brewscale.reseptiTeksti()));
    }

}
