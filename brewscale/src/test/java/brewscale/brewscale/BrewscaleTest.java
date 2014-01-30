/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.*;
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

    public void testSkaalainToimiiYkkostaSuuremmilla() {

        brewscale.skaalaa(2);
        assertEquals(20.00, brewscale.getResepti().getKoko());
    }

    public void testSkaalainToimiiYkkostaPienemmilla() {
        brewscale.skaalaa(0.5);
        assertEquals(5.00, brewscale.getResepti().getKoko());
    }

    public void testSkaalainToimiiNollaaPienemmilla() {
        brewscale.skaalaa(-0.1);
        assertEquals(10.00, brewscale.getResepti().getKoko());
    }
    
    public void testSkaalainToimiiAineksille() {
        lisaaAineita("g");
        brewscale.skaalaa(1.5);
        for (Aines a : brewscale.getResepti().getAinekset()) {
            assertEquals(15, a.getMaara(), 0.001);
        }
    }
    
    public void testSkaalainToimiiAineksilleNollalla() {
        lisaaAineita("g");
        brewscale.skaalaa(0);
        for (Aines a: brewscale.getResepti().getAinekset()) {
            assertEquals(0, a.getMaara(), 0.001);
        }
    }

    private void lisaaAineita(String yksikko) {
        resepti.lisaaMallas(new Mallas("Mallas 1", 10, yksikko));
        resepti.lisaaHumala(new Humala("Humala 1", 10, yksikko, 5.0));
        resepti.lisaaAines(new Aines("Aines 1", 10, yksikko));
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

}
