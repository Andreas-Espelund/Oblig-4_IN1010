class Pasient{

  String navn;
  String fodselsnr;
  int pasientID;

  private static int pasID = 1;
  Stabel<Resept> reseptStabel = new Stabel<Resept>();


  public Pasient(String navn, String fodselsnr){
    this.navn = navn;
    this.fodselsnr = fodselsnr;
    this.pasientID = pasID++;
  }
  //Oppretter denne for å skrive ut pent i Resept.
  public int hentID(){
    
    return pasientID;
  }

  public String hentNavn(){
    return navn;
  }

  public String hentFoedselsnummer(){
    return fodselsnr;
  }
  //Legger en resept på toppen av stabelen vår
  public void leggTilResept(Resept resept){
    reseptStabel.leggPaa(resept);
  }
  //Skriver ut alle resepter
  public void skrivUtReseptlise(){
    for (int i = 0 ; i < reseptStabel.stoerrelse() ; i++){
      System.out.println(reseptStabel.hent(i));
    }
  }

  public Stabel<Resept> hentResepter(){
    return reseptStabel;
  }

  public String toString(){
    return "Pasient info: " + " | Navn: " + navn + " | Fodselsnummer: " + fodselsnr + " | PasientID: " + pasientID;
  }

}
