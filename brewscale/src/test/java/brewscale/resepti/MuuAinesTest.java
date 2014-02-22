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
 * @author riha
 */
public class MuuAinesTest {
    
    public MuuAinesTest() {
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
    public void muuAinesKonstruktori() {
        MuuAines a = new MuuAines("Testiaines", 1, "g");
        assertTrue(a.getNimi().equals("Testiaines"));
        assertEquals(1.0, a.getMaara(), 0.001);
        assertTrue(a.getYksikko().equals("g"));
    }
    
    public void muuAinesKonstruktoriNegatiivisiellaMaaralla() {
        MuuAines a = new MuuAines("Testiaines", -1, "g");
        assertEquals(0.0, a.getMaara(), 0.001);
    }
    
    public void muuAinesKonstruktoriNollamaaralla() {
        MuuAines a = new MuuAines("Testiaines", 0, "g");
        assertEquals(0.0, a.getMaara(), 0.001);
    }
}
