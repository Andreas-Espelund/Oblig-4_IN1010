//HvitResept er en subklasse av Resept

public class HvitResept extends Resept{

    //Kaller paa superklassen sin konstruktoer
    HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
        super(legemiddel, lege, pasient, reit);
    }

    //returnerer reseptens farge
    @Override
    public String farge(){
        return "hvit ";
    }

    //returnerer prisen aa betale
    @Override
    public int prisAaBetale(){
        return pris;
    }

    //legger til fargen til toString metoden til super
    @Override
    public String toString(){
        return this.farge()+"resept | " + super.toString();
    }
}
