//Spesialist er en subklasse av Lege og implementerer Godkjenningsfritak

public class Spesialist extends Lege implements Godkjenningsfritak{
    private String kontrollID;

    //kaller paa super sin konstrukoer i tillegg til aa ta inn en kontrollID
    public Spesialist(String navn,String kontrollID){
        super(navn);
        this.kontrollID = kontrollID;
    }

    //returnerer Spesialisten sin kontrollID
    @Override
    public String hentKontrollID(){
        return kontrollID;
    }

    //returnerer info om Spesialisten
    @Override
    public String toString(){
        return "Navn: " + navn + " | Kontroll ID: " + kontrollID;
    }
}