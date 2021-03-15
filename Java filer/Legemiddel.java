abstract public class Legemiddel{
    protected static int nesteId = 1;
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int id;

    //konstruktoeren tar inn info om legemiddelet og tilordner det 
    //til instansvariablane
    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = nesteId;
        nesteId ++;
    }

    //returnerer navn
    public String hentNavn(){
        return navn;
    }

    //returnerer pris
    public int hentPris(){
        return pris;
    }

    //returnerer virkestoff
    public double hentVirkestoff(){
        return virkestoff;
    }

    //returnerer id
    public int hentId(){
        return id;
    }

    //returnerer info om legemiddelet
    public String toString(){
        return "Navn: "+navn+" | Pris: "+pris+" | Virkestoff: "+virkestoff; 
    }

    
}