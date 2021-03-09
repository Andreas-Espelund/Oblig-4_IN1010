//Vanlig er en subklasse av Legemiddel

public class Vanlig extends Legemiddel{
    
    //kaller paa super sin konstruktoer
    public Vanlig(String navn,int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }

}