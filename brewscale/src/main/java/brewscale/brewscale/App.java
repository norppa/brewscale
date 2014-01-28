package brewscale.brewscale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Resepti resepti = new Resepti("Tester Ale 2", 10);
        Mallas m1 = new Mallas("Pale Ale", 1.0, 2);
        Mallas m2 = new Mallas("Crystal 100", 2.0, 2);
        resepti.lisaaMallas(m1);
        resepti.lisaaMallas(m2);
        
        Humala h1 = new Humala("Hallertau", 1, 1, 5.5);
        Humala h2 = new Humala("Fuggles", 15,0, 7.0);
        Humala h3 = new Humala("Humala 3", 20, 0, 6.0);
        resepti.lisaaHumala(h1);
        resepti.lisaaHumala(h2);
        
        Aines a1 = new Aines("Sokeri", 500);
        resepti.lisaaAines(a1);
        
        Brewscale b = new Brewscale(resepti);
        System.out.println(b.reseptiTeksti());
        b.tallenna();
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
