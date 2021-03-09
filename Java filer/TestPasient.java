class TestPasient{
  public static void main(String[] args) {
    Pasient p1 = new Pasient ("Miriam", "150595");

    Vanlig ppille = new Vanlig("P-pille", 150, 2.0);
    Vanedannende sobril = new Vanedannende("Sobril", 350, 8.6, 10);

    Lege legen = new Lege("Heidi");
    BlaaResept bla = new BlaaResept(ppille, legen, p1, 2);
    MilitaerResept resept3 = new MilitaerResept(sobril, legen, p1, 6);

    p1.leggTilResept(bla);
    p1.leggTilResept(resept3);

    p1.hentUtReseptlise();
  }


}
