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
