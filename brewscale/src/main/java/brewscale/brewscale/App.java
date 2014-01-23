package brewscale.brewscale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Resepti resepti = new Resepti("Tester Ale", 10);
        Mallas m1 = new Mallas("Pale Ale", 1.0, 2);
        Mallas m2 = new Mallas("Crystal 100", 2.0, 2);
        resepti.lisaaMallas(m1);
        resepti.lisaaMallas(m2);
        
        Humala h1 = new Humala("Hallertau", 1, 1, 5.5);
        Humala h2 = new Humala("Fuggles", 15,0, 7.0);
        resepti.lisaaHumala(h1);
        resepti.lisaaHumala(h2);
        
        Aines a1 = new Aines("Sokeri", 500);
        resepti.lisaaAines(a1);
        
        System.out.println(resepti.reseptiTeksti());
        Brewscale b = new Brewscale(resepti);
        
        Resepti uusi = b.muutaGrammoiksi();
        System.out.println(uusi.reseptiTeksti());

    }
}
