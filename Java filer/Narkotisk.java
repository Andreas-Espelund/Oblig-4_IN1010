//Narkotisk er en subklasse av Legemiddel

public class Narkotisk extends Legemiddel{
    protected int styrke;

    //konstruktoeren kaller paa super sin konstruktoer i tillegg til aa ta inn styrke
    public Narkotisk(String navn, int pris, double virkestoff,int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    //returnerer styrke
    public int hentNarkotiskStyrke(){
        return styrke;
    }

    //returnerer info om det Narkotiske Legemiddelet
    @Override
    public String toString(){
        return super.toString() + " | Narkotisk styrke: "+styrke;
    } 
}