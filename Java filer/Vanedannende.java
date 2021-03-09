//Vanedannende er en subklasse av Legemiddel

public class Vanedannende extends Legemiddel{
    protected int styrke;

    //konstruktoeren kaller paa super sin konstruktoer i tillegg til aa ta inn styrke
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    //returnerer styrke
    public int hentVanedannendeStyrke(){
        return styrke;
    }

    //returnerer info om det Vanedannende Legemiddelet
    @Override
    public String toString(){
        return super.toString()+" | Vanedannande styrke: "+styrke;
    }
}