package brewscale.brewscale;

import brewscale.resepti.Mallas;
import brewscale.resepti.Aines;
import brewscale.resepti.Humala;
import brewscale.resepti.Resepti;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Resepti resepti = new Resepti("Tester Ale 2", 10);
        Mallas m1 = new Mallas("Pale Ale", 1.0, "lbs");
        Mallas m2 = new Mallas("Crystal 100", 2.0, "lbs");
        resepti.lisaaMallas(m1);
        resepti.lisaaMallas(m2);
        
        Humala h1 = new Humala("Hallertau", 1, "oz", 5.5);
        Humala h2 = new Humala("Fuggles", 15, "g", 7.0);
        Humala h3 = new Humala("Humala 3", 20, "g", 6.0);
        resepti.lisaaHumala(h1);
        resepti.lisaaHumala(h2);
        
        Aines a1 = new Aines("Sokeri", 500);
        resepti.lisaaAines(a1);
        
        Brewscale b = new Brewscale(resepti);
        System.out.println(b.getResepti().getAinekset().get(0).getYksikko());
        b.muutaGrammoiksi();
        System.out.println(b.getResepti().getAinekset().get(0).getYksikko());
//        
//        Resepti uusi = new Resepti(resepti);
//        Brewscale brsc = new Brewscale();
//        brsc.uusiResepti(uusi);
//        brsc.skaalaa(2);
//        System.out.println(brsc.getResepti().reseptiTeksti());
//        brsc.muutaGrammoiksi();
//        System.out.println(brsc.getResepti().reseptiTeksti());
    }
}
