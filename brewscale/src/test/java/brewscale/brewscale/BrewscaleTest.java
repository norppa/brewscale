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
        Mallas m1 = new Mallas("Mallas 1", 1.1);
        Mallas m2 = new Mallas("Mallas 2", 2.22);
        Mallas m3 = new Mallas("Mallas 3", 3.333);
        resepti.lisaaMallas(m1);
        resepti.lisaaMallas(m2);
        resepti.lisaaMallas(m3);

        Humala h1 = new Humala("Humala 1", 0.5, 5.0);
        Humala h2 = new Humala("Humala 2", 1.0, 7.0);
        resepti.lisaaHumala(h1);
        resepti.lisaaHumala(h2);

        Aines a1 = new Aines("Aines 1", 0.5);
        resepti.lisaaAines(a1);

        brewscale = new Brewscale(resepti);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSkaalainToimiiYkkostaSuuremmilla() {

        Resepti uusi = brewscale.skaalaa(2);
        assertEquals(20.00, uusi.getKoko());
    }
    
    public void testSkaalainToimiiYkkostaPienemmilla() {
        Resepti uusi = brewscale.skaalaa(0.5);
        assertEquals(5.00, uusi.getKoko());
    }
    
    public void testSkaalainToimiiNollaaPienemmilla() {
        Resepti uusi = brewscale.skaalaa(-1);
        assertEquals(10.00, uusi.getKoko());
    }
}
