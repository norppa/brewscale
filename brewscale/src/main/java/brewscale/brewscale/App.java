package brewscale.brewscale;

import brewscale.gui.BrewscaleGUI;
import brewscale.resepti.Mallas;
import brewscale.resepti.MuuAines;
import brewscale.resepti.Humala;
import brewscale.resepti.Resepti;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        
        Brewscale brewscale = new Brewscale();
        BrewscaleGUI gui = new BrewscaleGUI(brewscale);
        gui.run();
        
//        Resepti resepti = new Resepti("Tester Ale", 10);
//        Mallas m1 = new Mallas("Pale Ale", 1.0, "lbs");
//        Mallas m2 = new Mallas("Crystal 100", 2.0, "lbs");
//        resepti.lisaaMallas(m1);
//        resepti.lisaaMallas(m2);
//        
//        Humala h1 = new Humala("Hallertau", 1, "oz", 5.5);
//        Humala h2 = new Humala("Fuggles", 15, "g", 7.0);
//        Humala h3 = new Humala("Humala 3", 20, "g", 6.0);
//        resepti.lisaaHumala(h1);
//        resepti.lisaaHumala(h2);
//        
//        Aines a1 = new Aines("Sokeri", 500);
//        resepti.lisaaAines(a1);
//        
//        resepti.setOhje("Keittele kunnes valmista");
//        
//        Brewscale b = new Brewscale();
//        b.setResepti(resepti);
//        
//        BrewscaleGUI gui = new BrewscaleGUI(b);
//        gui.run();


    }
}
