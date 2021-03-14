public class Lege implements Comparable<Lege> {

    protected String navn;
    Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();
  
    //Konstruktør
    public Lege(String navn){
      this.navn = navn;
    }
  
    public String hentNavn(){
      return navn;
    }
  
    public int compareTo(Lege annenLege){
      return this.hentNavn().compareTo(annenLege.hentNavn());
    }

    public void settInnResept(Resept r){
        utskrevedeResepter.leggTil(r);
    }
    public Lenkeliste<Resept> hentReseptliste(){
        return utskrevedeResepter;
    }
  
    public void utskriftAvReseptListe(){
      for (int i = 0; i > utskrevedeResepter.stoerrelse() ; i++){
        System.out.println(utskrevedeResepter.hent(i));
      }
    }
  
    //ToString()-metode som gir utskrift av informasjon om Lege
    public String toString(){
      return "Navn: " + navn;
    }

    
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
    UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this,legemiddel);
        }
        return (new HvitResept(legemiddel, this, pasient, reit));
    }

    public MilitaerResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int reit)
    throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this,legemiddel);
        }
        return (new MilitaerResept(legemiddel, this, pasient, reit));
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this,legemiddel);
        }
        return (new PResept(legemiddel, this, pasient));
    }
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) 
    throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this,legemiddel);
        }
        return (new BlaaResept(legemiddel, this, pasient,reit));
    }


  
  }
  