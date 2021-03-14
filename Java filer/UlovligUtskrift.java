public class UlovligUtskrift extends Exception{
    UlovligUtskrift(Lege l, Legemiddel lm){
        super("Legen "+l.hentNavn()+ " har ikke lov til å skrive ut "+ lm.hentNavn());
    }
}
    