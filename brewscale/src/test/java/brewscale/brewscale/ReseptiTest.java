/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.brewscale;

import junit.framework.TestCase;

/**
 *
 * @author jtthaavi@cs
 */
public class ReseptiTest extends TestCase {

    Resepti resepti;

    public ReseptiTest(String testName) {
        super(testName);

        resepti = new Resepti("Testiresepti", 10);
//        Mallas m1 = new Mallas("Mallas 1", 1.1);
//        Mallas m2 = new Mallas("Mallas 2", 2.22);
//        Mallas m3 = new Mallas("Mallas 3", 3.333);
//        resepti.lisaaMallas(m1);
//        resepti.lisaaMallas(m2);
//        resepti.lisaaMallas(m3);
//
//        Humala h1 = new Humala("Humala 1", 0.5, 5.0);
//        Humala h2 = new Humala("Humala 2", 1.0, 7.0);
//        resepti.lisaaHumala(h1);
//        resepti.lisaaHumala(h2);
//
//        Aines a1 = new Aines("Aines 1", 0.5);
//        resepti.lisaaAines(a1);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUudenReseptinNimiOikein() {
        assertEquals("Testiresepti", resepti.getNimi());
    }

    public void testUudenReseptinKokoOikein() {
        assertEquals(10.0, resepti.getKoko());
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

//    public void testReseptiTekstiToimii() {
//        Mallas m = new Mallas("Mallas", 5.0);
//        resepti.lisaaMallas(m);
//        Humala h = new Humala("Humala", 5.0, 5.0);
//        resepti.lisaaHumala(h);
//        Aines a = new Aines("Aines", 5.0);
//        resepti.lisaaAines(a);
//        assertEquals("Testiresepti (10.0 l)\nMaltaat:\n5.0 Mallas\nHumalat:\n5.0 Humala\n"
//                + "Muut ainekset:\n5.0 Aines\n", resepti.reseptiTeksti());
//    }

    public void testToStringToimii() {
        assertEquals("Testiresepti (10.0 l)", resepti.toString());
    }
}
