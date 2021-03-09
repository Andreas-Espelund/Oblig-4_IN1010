//MilitaerResept er en subklasse av HvitResept

public class MilitaerResept extends HvitResept{

    //kaller paa super sin konstrukoer
    public MilitaerResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
        super(legemiddel, lege, pasient, reit);
    }

    //legger til typen resept etter farge
    @Override
    public String farge(){
        return super.farge() + "militaer-";
    }

    //returnerer prisen aa betale som alltid er 0kr
    @Override
    public int prisAaBetale(){
        return 0;
    }
}
