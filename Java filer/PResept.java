//PResept er en subklasse av Resept

public class PResept extends HvitResept{

    //kaller paa super sin konstrukoer med 3 som argument for reit
    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient){
        super(legemiddel, lege, pasient, 3);
    }

    //legger til typen resept etter farge
    @Override
    public String farge(){
        return super.farge() + "P-";
    }

    //returnerer prisen med 108kr rabatt, minimum 0kr
    @Override
    public int prisAaBetale(){
        if (pris < 108){
            return 0;
        }
        return pris-108;
    }
}
