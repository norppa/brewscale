/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.resepti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtthaavi@cs
 */
public class HumalaTest {

    public HumalaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAlphaAcidToimii() {
        Humala humala = new Humala("Humala 1", 10, "g", 4.5);
        assertEquals(4.5, humala.getAlphaAcid(), 0.001);
    }

    @Test
    public void laskeAAUToimiiGrammoilla() {
        Humala humala = new Humala("Humala 1", 28.35, "g", 5.0);
        assertEquals(5, humala.laskeAAU(), 0.001);
    }

    @Test
    public void laskeAAUToimiiUnsseilla() {
        Humala humala = new Humala("Humala 1", 1, "oz", 5.0);
        assertEquals(5, humala.laskeAAU(), 0.001);
    }

    @Test
    public void laskeAAUToimiiPaunoilla() {
        Humala humala = new Humala("Humala 1", 0.0625, "lbs", 5.0);
        assertEquals(5, humala.laskeAAU(), 0.001);
    }
}
