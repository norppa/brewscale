package brewscale.brewscale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Resepti resepti = new Resepti("Tester Ale", 10);
        Mallas m1 = new Mallas("Pale Ale", 1.2);
        Mallas m2 = new Mallas("Crystal 100", 0.6);
        Mallas m3 = new Mallas("Roast Barley", 0.1);
        resepti.lisaaMallas(m1);
        resepti.lisaaMallas(m2);
        resepti.lisaaMallas(m3);
        
        Humala h1 = new Humala("Hallertau", 12, 5.5);
        Humala h2 = new Humala("Fuggles", 6, 7.0);
        resepti.lisaaHumala(h1);
        resepti.lisaaHumala(h2);
        
        Aines a1 = new Aines("Sokeri", 0.5);
        resepti.lisaaAines(a1);
        
        System.out.println(resepti.reseptiTeksti());
        Brewscale b = new Brewscale(resepti);
        System.out.println(b.skaalaa(2).reseptiTeksti());
    }
}
