/**
 * Test for itererbar lenkeliste
 */
public class TestLenkelisteIterator {

    public static void main(String[] args) {
        Lenkeliste<String> liste = new Lenkeliste<String>();

        liste.leggTil("a");
        liste.leggTil("b");
        liste.leggTil("c");
        liste.leggTil("d");
        liste.leggTil("e");
        liste.leggTil("f");

        for (String e : liste){
            System.out.println(e);
        }



    }
}