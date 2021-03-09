//BlaaResept er en subklasse av Resept

public class BlaaResept extends Resept{

    //kaller paa superklassen sin konstrukoer
    public BlaaResept(Legemiddel legemiddel, Lege lege, int pasientId,int reit){
        super(legemiddel, lege, pasientId, reit);
    }

    //returnerer reseptens farge
    @Override
    public String farge(){
        return "blaa ";
    }

    //returnerer prisen aa betale (25%)
    @Override
    public int prisAaBetale(){
        return pris/4;
    }

    //legger til farge til super sin toString
    @Override
    public String toString(){
        return this.farge()+"resept | " + super.toString();
    }
}