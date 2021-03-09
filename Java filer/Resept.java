abstract class Resept{
    private static int nesteId = 1;
    protected int id;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected int pasientId;
    protected int reit;
    protected int pris;

    //konstruktoeren til Resept tar inn info om resepten og tilordnar det til
    //instansvariablane. Den faar en unik id.
    public Resept(Legemiddel legemiddel, Lege lege, int pasientId, int reit){
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.pasientId = pasientId;
        this.reit = reit;
        id = nesteId;
        nesteId ++;
        pris = legemiddel.hentPris();
        

    } 
    
    //returnerer reseptens Legemiddel
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }
   
    //returnerer reseptens Lege
    public Lege hentLege(){
        return lege;
    }
   
    //returnerer reseptens id
    public int hentId(){
        return id;
    }

    //returnerer reseptens pasientId
    public int hentPasientId(){
        return pasientId;
    }

    //returnerer reseptens gjenstaande reit
    public int hentReit(){
        return reit;
    }

    //reduserer reit med 1 om den ble brukt
    public boolean bruk() {
        reit = reit -1;
        return reit >= 0;
    }

    //abstrakt metode for aa hente reseptens farge
    abstract public String farge();

    //abstrakt metode for aa hente pris
    abstract public int prisAaBetale();

    //toString returnerer informasjon om resepten
    public String toString(){
        return "ID: " + id + " | Legemiddel: " + legemiddel.hentNavn() + 
        " | Lege: " + lege + " | Pasient ID: " + pasientId + " | Reit: " + reit + " | Pris aa betale: " + this.prisAaBetale();
    }
}