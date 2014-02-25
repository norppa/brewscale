/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

import brewscale.resepti.Mallas;
import brewscale.resepti.Humala;
import brewscale.resepti.MuuAines;
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

    public void testUudenReseptinKokoPositiivinen() {
        resepti = new Resepti("", 1, "l");
        assertEquals(1.0, resepti.getKoko());
    }

    public void testUuderReseptinKokoNolla() {
        resepti = new Resepti("", 0, "l");
        assertEquals(0.0, resepti.getKoko(), 0.001);
    }

    public void testUudenReseptinKokoNegatiivinen() {
        resepti = new Resepti("", -1, "l");
        assertEquals(0.0, resepti.getKoko(), 0.001);
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

    public void testSetKokoYksikkoVaarallaYksikolla() {
        resepti.setKokoYksikko("g");
        assertTrue(resepti.getKokoYksikko().equals("l"));
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

    public void testMaltaanLisaysToimiiOliolla() {
        Mallas m = new Mallas("Mallas", 5.0);
        resepti.lisaaMallas(m);

        assertTrue(resepti.getMaltaat().contains(m));
    }

    public void testMaltaanLisaysEiLisaaVirheellisellaMaaralla() {
        resepti.lisaaMallas("Virhemallas", "ei numero", "g");
        boolean mallasLoytyy = false;
        for (Mallas m : resepti.getMaltaat()) {
            if (m.getNimi().equals("Virhemallas")) {
                mallasLoytyy = true;
                break;
            }
        }
        assertFalse(mallasLoytyy);
    }

    public void testMaltaanLisaysToimiiTekstilla() {
        resepti.lisaaMallas("Testimallas", "200", "g");
        boolean mallasLoytyy = false;
        for (Mallas m : resepti.getMaltaat()) {
            if (m.getNimi().equals("Testimallas")) {
                mallasLoytyy = true;
                break;
            }
        }
        assertTrue(mallasLoytyy);
    }

    public void testHumalanLisaysToimiiOliolla() {
        Humala h = new Humala("Humala", 5.0, 5.0);
        resepti.lisaaHumala(h);
        assertTrue(resepti.getHumalat().contains(h));
    }

    public void testHumalanLisaysToimiiTekstilla() {
        resepti.lisaaHumala("Testihumala", "200", "g", "5.0");
        boolean humalaLoytyy = false;
        for (Humala h : resepti.getHumalat()) {
            if (h.getNimi().equals("Testihumala")) {
                humalaLoytyy = true;
                break;
            }
        }
        assertTrue(humalaLoytyy);
    }

    public void testHumalanLisaysEiLisaaVirheellisellaMaaralla() {
        resepti.lisaaHumala("Virhehumala", "ei numero", "g", "0.5");
        resepti.lisaaHumala("Virhehumala", "10", "g", "ei numero");
        boolean humalaLoytyy = false;
        for (Humala h : resepti.getHumalat()) {
            if (h.getNimi().equals("Virhehumala")) {
                humalaLoytyy = true;
                break;
            }
        }
        assertFalse(humalaLoytyy);
    }

//    public void testKorvaaHumalaToimii() {
//        resepti.lisaaHumala(new Humala("Humala 1", 50, "g", 5.0));
//        resepti.lisaaHumala(new Humala("Humala 2", 50, "g", 5.0));
//        resepti.lisaaHumala(new Humala("Humala 3", 50, "g", 5.0));
//        resepti.korvaaHumala(1, "Humala 4", 10);
//        assertEquals("Humala 4", resepti.getHumalat().get(1).getNimi());
//        assertEquals(25.0, resepti.getHumalat().get(1).getMaara());
//    }

    public void testAineksenLisaysToimiiOliolla() {
        MuuAines a = new MuuAines("Aines", 5.0);
        resepti.lisaaMuuAines(a);
        assertTrue(resepti.getMuutAinekset().contains(a));
    }

    public void testAineksenLisaysToimiiTekstilla() {
        resepti.lisaaMuuAines("Testiaines", "200", "g");
        boolean ainesLoytyy = false;
        for (MuuAines a : resepti.getMuutAinekset()) {
            if (a.getNimi().equals("Testiaines")) {
                ainesLoytyy = true;
                break;
            }
        }
        assertTrue(ainesLoytyy);
    }

    public void testMuuAinesLisaysEiLisaaVirheellisellaMaaralla() {
        resepti.lisaaMuuAines("Virheaines", "ei numero", "g");
        boolean ainesLoytyy = false;
        for (MuuAines m : resepti.getMuutAinekset()) {
            if (m.getNimi().equals("Virheaines")) {
                ainesLoytyy = true;
                break;
            }
        }
        assertFalse(ainesLoytyy);
    }

    public void testTyhjennaAineksetToimii() {
        lisaaAineita("g");
        resepti.tyhjennaAinekset();
        assertTrue(resepti.getAinekset().isEmpty());
        assertTrue(resepti.getMaltaat().isEmpty());
        assertTrue(resepti.getMuutAinekset().isEmpty());
        assertTrue(resepti.getHumalat().isEmpty());
    }

    public void testMuistiinpanoOnAluksiTyhja() {
        assertEquals("", resepti.getMuistiinpanot());
    }

    public void testToStringToimii() {
        assertEquals("Testiresepti (10.0 l)", resepti.toString());
    }

    private void lisaaAineita(String yksikko) {
        resepti.lisaaMallas(new Mallas("Mallas 1", 10, yksikko));
        resepti.lisaaHumala(new Humala("Humala 1", 10, yksikko, 5.0));
        resepti.lisaaMuuAines(new MuuAines("Muu Aines 1", 10, yksikko));
    }
}
