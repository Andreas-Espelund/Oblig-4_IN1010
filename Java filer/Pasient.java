class Pasient{

  String navn;
  String fodselsnr;
  int pasientID;

  private static int pasID = 0;
  Stabel<Resept> reseptStabel = new Stabel<Resept>();


  public Pasient(String navn, String fodselsnr){
    this.navn = navn;
    this.fodselsnr = fodselsnr;
    pasientID = pasID++;
  }
  //Oppretter denne for å skrive ut pent i Resept.
  public int hentID(){
    return pasientID;
  }
  //Legger en resept på toppen av stabelen vår
  public void leggTilResept(Resept resept){
    reseptStabel.leggPaa(resept);
  }
  //Skriver ut alle resepter
  public void hentUtReseptlise(){
    for (int i = 0 ; i < reseptStabel.stoerrelse() ; i++){
      System.out.println(reseptStabel.hent(i));
    }
  }

  public String toString(){
    return "Pasient info: " + " | Navn: " + navn + " | Fodselsnummer: " + fodselsnr + " | PasientID: " + pasientID;
  }

}
