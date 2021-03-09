public class Lege{
    protected String navn;

    //konstruktoeren tar inn navn og tilordner det til instansvariablen navn
    public Lege(String navn){
        this.navn = navn;
    }

    //returnerer legens navn
    public String hentNavn(){
        return navn;
    }

    //returnerer informasjon om Legen
    public String toString(){
        return "Navn: " + navn;
    }
    
}