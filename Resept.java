abstract class Resept{
    protected static int nesteId = 0;
    protected int id;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;
    protected int pris;

    //konstruktoeren til Resept tar inn info om resepten og tilordnar det til
    //instansvariablane. Den faar en unik id.
    public Resept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.pasient = pasient;
        this.reit = reit;
        this.id = nesteId ++;

        pris = legemiddel.hentPris();
        pasient.leggTilResept(this);
        lege.settInnResept(this);


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
        return pasient.hentID();
    }

    public Pasient hentPasient(){
      return pasient;
    }

    //returnerer reseptens gjenstaande reit
    public int hentReit(){
        return reit;
    }

    //reduserer reit med 1 om den ble brukt
    public boolean bruk() {
        if (reit > 0){
            reit --;
            return true;
        }else{
            return false;
        }
    }

    //abstrakt metode for aa hente reseptens farge
    abstract public String farge();

    //abstrakt metode for aa hente pris
    abstract public int prisAaBetale();

    //toString returnerer informasjon om resepten
    public String toString(){
        return "ID: " + id + " | Legemiddel: " + legemiddel.hentNavn() +
        " | Lege: " + lege.hentNavn() + " | Pasient ID: " + hentPasientId() + " | Reit: " + reit + " | Pris: " + this.prisAaBetale();
    }
}
