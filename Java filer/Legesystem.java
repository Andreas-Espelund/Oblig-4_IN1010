import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class Legesystem {
    //beholdere for pasienter, legemidler, resepter og leger
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    private static Lenkeliste<Lege> leger = new Lenkeliste<Lege>();
    public static void main(String[] args)throws FileNotFoundException {



        //initialisering av program
        skrivFraFil("storFil.txt");

        kommandoLoekke();
        //kommandoloekka er ferdig
        System.out.println("avslutter...");

    }



    /*
        skrivFrafil leser inn linjer og legger til riktig type element
        innesingsfilene kan inneholde feil, disse linjene og feil blir printa til terminal
        og blir ikkje lagt inn.
    */
    public static void kommandoLoekke(){
        // Kommandoloekke
        int valg = 0;
        while (valg != 6){
            skrivMeny();
            valg = taInput();

            // skriver ut fullstendig oversikt over pasienter, leger, legemidler og reseptar
            if (valg == 1){
                System.out.println("plassholder for meny 1");
                skrivUtOversikt();
                gaaTilbake();
            }


            //Oppretter og legger til nye elementer i systemet
            else if (valg == 2){
                System.out.println("plassholder for meny 2");
                leggTilElementer();
                gaaTilbake();
            }


            //bruker en gitt resept fra listen til en pasient
            else if (valg == 3){
                System.out.println("plassholder for meny 3");
                brukResept();
                gaaTilbake();
            }


            //undermeny for aa skrive ut forskjellig statestikk
            else if (valg == 4){
                System.out.println("plassholder for meny 4");
                skrivUtStatestikk();
                gaaTilbake();
            }


            //skriver all data til fil
            else if(valg == 5){
                System.out.println("plassholder for meny 5");
                skrivAlleDataTilFil();
                gaaTilbake();
            }

        }
    }

    public static void skrivFraFil(String filnavn)throws FileNotFoundException{
        try{
            File fil = new File(filnavn);
            Scanner sc = new Scanner(fil);
            int i  = 0;

            while (sc.hasNextLine()){
                String linje = sc.nextLine();
                String[] biter = linje.split(",");
                if (linje.contains("#")){
                    i ++;
                }

                //Legger til pasienter
                else if (i == 1){
                    try{
                        String navn = biter[0];
                        String foedselsNr = biter[1];
                        pasienter.leggTil(new Pasient(navn,foedselsNr));

                    }catch(Exception e){
                        System.out.println("Kunne ikkje legge til linje: "+linje);
                    }

                }

                //Legger til legemidler
                else if (i == 2){
                    try{
                        String navn = biter[0];
                        String type = biter[1];
                        double Dpris = Double.parseDouble(biter[2]);
                        int pris = (int) Dpris;
                        double virkestoff = Double.parseDouble(biter[3]);

                        //sjekker kva type legemiddel som skal lages og legger det til i legemidler
                        if (biter.length == 5){
                            int styrke = Integer.parseInt(biter[4]);

                            if (type.equals("vanedannende")){
                                legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                            }
                            else if (type.equals("narkotisk")){
                                legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                            }
                        }
                        else{
                            legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                        }
                    }catch(Exception e){
                        System.out.println("Kunne ikkje legge til linje: "+linje);
                    }

                }

                //legger til leger
                else if (i == 3){
                    try{
                        String navn = biter[0];
                        String kontrollID = biter[1];
                        if (kontrollID.equals("0")){
                            leger.leggTil(new Lege(navn));
                        }else{
                            leger.leggTil(new Spesialist(navn, kontrollID));
                        }
                    }catch(Exception e){
                        System.out.println("Kunne ikkje legge til linje: "+linje);
                    }

                }

                //legger til resepter
                else if (i == 4){
                    try{
                        int legemiddelnummer = Integer.parseInt(biter[0]);
                        String legeNavn = biter[1];
                        int pasientID = Integer.parseInt(biter[2]);
                        String type = biter[3];
                        int reit = 0;

                        Legemiddel legemiddel = null;
                        Pasient pasient = null;
                        Lege lege = null;


                        if (biter.length == 5){
                            reit = Integer.parseInt(biter[4]);
                        }

                        //for lokkene henter objektene fra legemiddelnummer, legeNavn og pasientID
                        for (Legemiddel l : legemidler){
                            if (l.hentId() == legemiddelnummer){
                                legemiddel = l;
                            }
                        }
                        if (legemiddel == null){
                            System.out.println("\nFant ikke legemiddelet med nummer:" +legemiddelnummer);
                        }

                        for (Lege l : leger){
                            if (l.hentNavn().equals(legeNavn)){
                                lege = l;
                            }
                        }
                        if(lege == null){
                            System.out.println("\nFant ikke legen med navn: "+ legeNavn);
                        }

                        for (Pasient p : pasienter){
                            if (p.hentID() == pasientID){
                                pasient = p;
                            }
                        }
                        if(pasient == null){
                            System.out.println("\nFant ikke pasienten med id: " + pasientID);
                        }

                        //sjekker kva type resept som skal lages og legger den til i resepter
                        if (type.equals("hvit")){
                            resepter.leggTil(new HvitResept(legemiddel, lege, pasient, reit));
                        }
                        else if (type.equals("blaa")){
                            resepter.leggTil(new BlaaResept(legemiddel, lege, pasient, reit));
                        }
                        else if (type.equals("militaer")){
                            resepter.leggTil(new MilitaerResept(legemiddel, lege, pasient, reit));
                        }
                        else if (type.equals("p")){
                            resepter.leggTil(new PResept(legemiddel, lege, pasient));
                        }

                    }catch(Exception e){
                        System.out.println("Kunne ikkje legge til linje: "+linje);
                    }
                }
            }
        //her fanger vi opp fileNotFound, og lar brukeren skrive inn nytt filnavn
        }catch (FileNotFoundException e){
            System.out.println("Ugyldig filnavn! Skriv inn riktig filnavn (skriv 'm' for aa gaa til hovedmeny) >");
            Scanner inp = new Scanner(System.in);
            String str = inp.nextLine();
            if(str.equals("m")){
                System.out.println("returnerer..");
            }else{
                skrivFraFil(str);
            }

        }
        //venter med aa gaa til hovedmeny dersom bruker vi sjaa feilmeldingar
        Scanner videre = new Scanner(System.in);
        System.out.println("\n============================================ \n|| Trykk 'enter' for aa gaa til hovedmeny ||\n============================================");
        videre.nextLine();
    }

    public static void skrivUtOversikt(){}

    public static void leggTilElementer(){}

    public static void brukResept(){
      // Skriver ut alle pasientene som det er resepter for:
      int teller = 0;
      System.out.print("Kva for ein pasient vil du sjå reseptane til:\n");

      //Itererer gjennom listen av pasienter og skriver ut med ett nummer forran:
      for (Pasient pasient : pasienter){
        System.out.println(teller + ": " + pasient.navn + "(fnr: " + pasient.fodselsnr + ")");
        teller ++;
      }

      //Tar imot input fra bruker, hvor nummer skal stemme med pasientens nummer i utskriften:
      Scanner inp = new Scanner(System.in);
      int i = inp.nextInt();
      Pasient valgtPas = null;
      //Sjekker at inputen er en av pasientene:
      if(i > 0 || i <= teller){
        valgtPas = pasienter.hent(i);
        System.out.println("Valgt pasient: " + valgtPas.navn +"(fnr: " + valgtPas.fodselsnr + ").");
      } else {
        System.out.println("Ugyldig indeks.");
      }


      System.out.println("Kva for ein resept onsker du aa bruke?\n");

      //Skriver ut de reseptene som hører til pasienten som er valgt:
      teller = 1;
      // int valgtPasID = valgtPas.hentID();
      //Itererer gjennom reseptene:
      for(Resept resept : resepter ){
        // Legger her inn tyr-catch fordi jeg ikke skjønner hva som går galt her...
        try {
        //Om resepten har samme pasientID som den valgte pasienten:
        if (resept.hentPasientId() == valgtPas.hentID()){
          System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + "(" + resept.hentReit() + " reit).");
          teller ++;
        }
      } catch (Exception e){
        System.out.println("Fant ikke denne");
      }
    }

    //Tar imot input fra bruker, hvor nummer skal stemme med resept i utskriften:
    Scanner inpR = new Scanner(System.in);
    int r = inpR.nextInt();

    Resept valgtRes = null;
    //Sjekker at inputen er en av pasientene:
    if(r > 0 || i <= teller){
      valgtRes = resepter.hent(r);
      System.out.println("Valgt pasient: " + valgtPas.navn +"(fnr: " + valgtPas.fodselsnr + ").");
      valgtRes.bruk();
    } else {
      System.out.println("Ugyldig indeks.");
    }

    System.out.println("Brukte resept paa " + valgtRes.hentLegemiddel().hentNavn() + ". Antall gjennværende reit " + valgtRes.hentReit() + ".");

  }


    public static void skrivUtStatestikk(){}

    public static void skrivAlleDataTilFil(){}

    public static void leggTilLege(){}

    public static void leggTilPasient(){}

    public static void leggTilResept(){}

    public static void leggTilLegemiddel(){}


    //skriver ut menyen med tomme linjer over slik at skjermen blir "resetta"
    public static void skrivMeny(){
        for (int i = 0; i < 30 ; i ++){
            System.out.println();
        }

        System.out.println("========LEGESYSTEM========");
        System.out.println("|1.| Skriv ut oversikt   |");
        System.out.println("|2.| Legg til            |");
        System.out.println("|3.| Bruk resept         |");
        System.out.println("|4.| Skriv statestikk    |");
        System.out.println("|5.| Skriv data til fil  |");
        System.out.println("|6.| Avslutt             |");
        System.out.println("==========================");

    }

    //tar input fra bruker med scanner og lar kun brukaren skrive heiltall mellom 1 og 6
    public static int taInput(){
        System.out.print("Velg funksjon:");
        try{
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            //if testen sjekkar at tallet er innafor menyvalga
            if (i < 1 || i > 6){
                System.out.println("Velg tall mellom 1 og 6");
                i = taInput();
            }
            return i;
        //catch blokka fanger opp om brukeren skriver inn tegn som ikkje er en int
        }catch(InputMismatchException e){
            System.out.println("Ugyldig inntasting! Kun heltall!");
            return taInput();
        }

    }

    //ventar til brukeren trykker paa enter for aa gaa tilbake til hovedmenyen
    public static void gaaTilbake(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Trykk 'enter' for aa gaa tilbake >");
        sc.nextLine();
    }
}
