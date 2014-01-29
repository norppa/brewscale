/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import brewscale.resepti.Mallas;
import brewscale.resepti.Humala;
import brewscale.resepti.Aines;
import brewscale.resepti.Resepti;
import junit.framework.TestCase;

/**
 *
 * @author jtthaavi@cs
 */
public class ReseptiTest extends TestCase {

    Resepti resepti;

    public ReseptiTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        resepti = new Resepti("Testiresepti", 10);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUudenReseptinNimiOikein() {
        assertEquals("Testiresepti", resepti.getNimi());
    }
    
    public void testSetNimiToimii() {
        resepti.setNimi("Uusi resepti");
        assertTrue(resepti.getNimi().equals("Uusi resepti"));
    }

    public void testUudenReseptinKokoOikein() {
        assertEquals(10.0, resepti.getKoko());
    }
    
    public void testUusiReseptiToimiiNegatiivisilla() {
        resepti = new Resepti("Resepti", -10);
        assertEquals(0.0, resepti.getKoko());
    }
    
    public void testUudenReseptinYksikkoOikein() {
        assertTrue(resepti.getKokoYksikko().equals("l"));
    }
    
    public void testUudenReseptinAsetettuYksikkoOikein() {
        resepti = new Resepti("Resepti", 10, "gal");
        assertTrue(resepti.getKokoYksikko().equals("gal"));
    }
    
    public void testSetKokoYksikkoMuuttaaGallonaksi() {
        resepti.setKokoYksikko("gal");
        assertTrue(resepti.getKokoYksikko().equals("gal"));
    }
    
    public void testSetKokoYksikkoEiHyvaksyVaaraaYksikkoa() {
        resepti.setKokoYksikko("foobar");
        assertTrue(resepti.getKokoYksikko().equals("l"));
        
    }

    public void testGetteritTyhjalla() {
        assertTrue(resepti.getMaltaat().isEmpty() && resepti.getHumalat().isEmpty() && resepti.getMuutAinekset().isEmpty());
    }

    public void testMaltaanLisaysToimii() {
        Mallas m = new Mallas("Mallas", 5.0);
        resepti.lisaaMallas(m);

        assertTrue(resepti.getMaltaat().contains(m));
    }

    public void testHumalanLisaysToimii() {
        Humala h = new Humala("Humala", 5.0, 5.0);
        resepti.lisaaHumala(h);
        assertTrue(resepti.getHumalat().contains(h));
    }
    
    public void testKorvaaHumalaToimii() {
        resepti.lisaaHumala(new Humala("Humala 1", 50, "g", 5.0));
        resepti.lisaaHumala(new Humala("Humala 2", 50, "g", 5.0));
        resepti.lisaaHumala(new Humala("Humala 3", 50, "g", 5.0));
        resepti.korvaaHumala(1, "Humala 4", 10);
        assertEquals("Humala 4", resepti.getHumalat().get(1).getNimi());
        assertEquals(25.0, resepti.getHumalat().get(1).getMaara());
    }

    public void testAineksenLisaysToimii() {
        Aines a = new Aines("Aines", 5.0);
        resepti.lisaaAines(a);
        assertTrue(resepti.getMuutAinekset().contains(a));
    }

    public void testMuistiinpanoOnAluksiTyhja() {
        assertEquals("", resepti.muistiinpanot());
    }

    public void testMuistiinpanonLisaysToimii() {
        resepti.lisaaMuistiinpano("Muistiinpano 1");
        resepti.lisaaMuistiinpano("Muistiinpano 2");
        assertEquals("Muistiinpano 1\nMuistiinpano 2\n", resepti.muistiinpanot());
    }

    public void testToStringToimii() {
        assertEquals("Testiresepti (10.0 l)", resepti.toString());
    }
}
