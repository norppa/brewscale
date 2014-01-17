package brewscale.brewscale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Resepti resepti = new Resepti("Testiresepti", 4.5);
        Tyokalut tkl = new Tyokalut(resepti);
        resepti = tkl.skaalaa(2);
        System.out.println(resepti);
    }
}
