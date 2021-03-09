//HvitResept er en subklasse av Resept

public class HvitResept extends Resept{

    //Kaller paa superklassen sin konstruktoer
    HvitResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit){
        super(legemiddel, lege, pasientId, reit);
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