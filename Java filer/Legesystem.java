import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class Legesystem {
    //beholdere for pasienter, legemidler, resepter og leger
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    private static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    public static void main(String[] args)throws FileNotFoundException {

        int valg = 0;
        
        //initialisering av program
        skrivFraFil("storFil.txt");
        // Kommandoloekke
        
    
        while (valg != 6){
            skrivMeny();
            valg = taInput(1,6);

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
        //kommandoloekka er ferdig
        System.out.println("avslutter...");
    }

    
    /*
        skrivFrafil leser inn linjer og legger til riktig type element
        innesingsfilene kan inneholde feil, disse linjene og feil blir printa til terminal
        og blir ikkje lagt inn. 

    */
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
                        String kontrollID = biter[1].strip();
                        
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
                        
                        String type = biter[3].strip();
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
                        
                        for (Lege l : leger){
                            if (l.hentNavn().equals(legeNavn)){
                                lege = l;
                            }
                        }
                        
                        for (Pasient p : pasienter){
                            if (p.hentID() == pasientID){
                                pasient = p;
                            }
                        }
                        

                        //sjekker kva type resept som skal lages og legger den til i resepter
                        if (type.equals("hvit")){
                            Resept r = new HvitResept(legemiddel, lege, pasient, reit);
                            resepter.leggTil(r);
                            
                        }
                        else if (type.equals("blaa")){
                            Resept r = new BlaaResept(legemiddel, lege, pasient, reit);
                            resepter.leggTil(r);
                            
                        }
                        else if (type.equals("militaer") || type.equals("millitaer")){
                            Resept r = new MilitaerResept(legemiddel, lege, pasient, reit);
                            resepter.leggTil(r);
                            
                        }
                        else if (type.equals("p")){
                            Resept r = new PResept(legemiddel, lege, pasient);
                            resepter.leggTil(r);
                            
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

    public static void skrivUtOversikt(){
        System.out.println("\n\n==========OVERSIKT OVER LEGER, LEGEMIDDEL, RESEPTER & PASIENTER==========\n");
      //Oppdateres når oppgave D er gjort - "Leger skal skrives ut i ordnet rekkefølge"
      System.out.println("================LEGER=================");

      int l = 0;
      for (Lege lege : leger){
        System.out.println(l + ": " + lege);
        l++;
      }

      System.out.println("======================================");


      System.out.println("\n================LEGEMIDDELOVERSIKT================");

      int lm = 0;
      for(Legemiddel legemiddel : legemidler){
        System.out.println(lm + ": " + legemiddel);
        lm++;
      }

      System.out.println("===================================================");

      //Da det er noe rart i resepter fungerer ikke denne uten å stoppe programmet, fikser når dette er løst.
      System.out.println("\n================RESEPTOVERSIKT================");

      int r = 0;
      for(Resept resept : resepter){
        System.out.println(r + ": " + resept);
        r++;
      }

      System.out.println("==============================================");

      System.out.println("\n================PASIENTOVERSIKT================");

      int p = 0;
      for(Pasient pasient : pasienter){
        System.out.println(p + ": " + pasient);
        p++;
      }

      System.out.println("===============================================");

    }

    public static void leggTilElementer(){
        for (int i = 0; i < 30 ; i ++){
            System.out.println();
        }

        System.out.println("========LEGG TIL========");
        System.out.println("|1.| Lege              |");
        System.out.println("|2.| Pasient           |");
        System.out.println("|3.| Resept            |");
        System.out.println("|4.| Legemiddel        |");
        System.out.println("|5.| Gå tilbake        |");
        System.out.println("========================");

        int valg = taInput(1,5);

        //Oppretter og legger til ny lege i systemet
        if (valg == 1){
            leggTilLege();
        }


        //Oppretter og legger til ny pasient i systemet
        else if (valg == 2){
            leggTilPasient();
        }


        //Oppretter og legger til ny resept i systemet
        else if (valg == 3){
            leggTilResept();
        }


        //Oppretter og legger til nytt legemiddel i systemet
        else if (valg == 4){
            leggTilLegemiddel();
        }

        //Går tilbake til hovedmenyen
        else if (valg == 5){ }

    }

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
        
        int i = taInput(0, teller);
        Pasient valgtPas = null;
        //Sjekker at inputen er en av pasientene:
        
        valgtPas = pasienter.hent(i);
        System.out.println("\nValgt pasient: " + valgtPas.navn +"(fnr: " + valgtPas.fodselsnr + ").");
        
        if (valgtPas.hentResepter().stoerrelse() == 0){
            System.out.println(valgtPas.hentNavn() + " har ingen resepter!");
        }else{
            System.out.println("Kva for ein resept onsker du aa bruke?\n");
  
            //Skriver ut de reseptene som hører til pasienten som er valgt:
            teller = 0;
            // int valgtPasID = valgtPas.hentID();
            //Itererer gjennom reseptene:
            for(Resept resept : valgtPas.hentResepter() ){
              // Legger her inn tyr-catch fordi jeg ikke skjønner hva som går galt her...
              
              //Om resepten har samme pasientID som den valgte pasienten:
              
                System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + "(" + resept.hentReit() + " reit).");
                
                teller ++;
              
            
          }
      
             //Tar imot input fra bruker, hvor nummer skal stemme med resept i utskriften:
          
            int r = taInput(0, teller-1);
      
            Resept valgtRes = null;
            //Sjekker at inputen er en av pasientene:
          
            valgtRes = valgtPas.hentResepter().hent(r);
            System.out.println("Valgt pasient: " + valgtPas.navn +"(fnr: " + valgtPas.fodselsnr + ").");
            if(valgtRes.bruk()){
                System.out.println("Brukte resept paa " + valgtRes.hentLegemiddel().hentNavn() + ". Antall gjennvaerende reit " + valgtRes.hentReit() + ".");
            }else{
                System.out.println("Kunne ikke bruke resept paa" + valgtRes.hentLegemiddel().hentNavn() + "(ingen gjenvarende reit).");
            }
        
      
      
        }
   
        
    }

    public static void skrivUtStatestikk(){
       
    }

    public static void skrivAlleDataTilFil(){}

    public static void leggTilLege(){
        // Oppretter scanner-objekt
        Scanner tastatur = new Scanner(System.in);

        // Henter informasjon fra bruker og legger til
        System.out.print("Navn (Lege): ");
        String legeNavn = tastatur.nextLine();
        leger.leggTil(new Lege(legeNavn));
        System.out.println("Lege " + legeNavn + " er lagt til.");
    }

    public static void leggTilPasient(){
        // Oppretter scanner-objekt
        Scanner tastatur = new Scanner(System.in);

        // Henter informasjon fra bruker og legger til
        System.out.print("Navn (Pasient): ");
        String pasientNavn = tastatur.nextLine();
        System.out.print("Fodselsnummer (Pasient): ");
        String fodselsnr = tastatur.nextLine();
        pasienter.leggTil(new Pasient(pasientNavn, fodselsnr));
        System.out.println("Pasient " + pasientNavn + " er lagt til.");
    }

    public static void leggTilResept(){
        // Oppretter scanner-objekt
        Scanner tastatur = new Scanner(System.in);
        Lege legeResept = null;
        Pasient pasientResept = null;
        Legemiddel legemiddelResept = null;

        // Meny for "type" resept
        for (int i = 0; i < 30 ; i ++){
            System.out.println();
        }

        System.out.println("========Resept========");
        System.out.println("|1.| Hvit resept       |");
        System.out.println("|2.| Militaerresept    |");
        System.out.println("|3.| P-resept          |");
        System.out.println("|4.| Blå resept        |");
        System.out.println("========================");

        int valg = taInput(1,4);

        // Henter informasjon om lege
        while (legeResept == null){
            System.out.print("Navn (Lege): ");
            String legeNavn = tastatur.nextLine();
            for (Lege lege : leger){
                if (lege.hentNavn().toLowerCase().equals( legeNavn.toLowerCase())){
                    legeResept = lege;
                }
            }
        }

        // Henter informasjon om pasient
        while (pasientResept == null){
            System.out.print("Navn (Pasient): ");
            String pasientNavn = tastatur.nextLine();
            for (Pasient pasient : pasienter){
                if (pasient.hentNavn().toLowerCase().equals( pasientNavn.toLowerCase())){
                    pasientResept = pasient;
                }
            }
        }

        // Henter informasjon om legemiddel
        while (legemiddelResept == null){
            System.out.print("Navn (Legemiddel): ");
            String legemiddelNavn = tastatur.nextLine();
            for (Legemiddel legemiddel : legemidler){
                if (legemiddel.hentNavn().toLowerCase().equals( legemiddelNavn.toLowerCase())){
                    legemiddelResept = legemiddel;
                }
            }
        }


        if (valg == 1){
            System.out.print("Reit: ");
            int reit = tastatur.nextInt();
            try {
                Resept resept = legeResept.skrivHvitResept(legemiddelResept, pasientResept, reit);
                resepter.leggTil(resept);
            } catch (UlovligUtskrift u){
                System.out.println(u);
            }

        }

        //Oppretter og legger til nye elementer i systemet
        else if (valg == 2){
            System.out.print("Reit: ");
            int reit = tastatur.nextInt();
            try {
                Resept resept = legeResept.skrivMillitaerResept(legemiddelResept, pasientResept, reit);
                resepter.leggTil(resept);
            } catch (UlovligUtskrift u){
                System.out.println(u);
            }
        }

        //bruker en gitt resept fra listen til en pasient
        else if (valg == 3){
            try {
                Resept resept = legeResept.skrivPResept(legemiddelResept, pasientResept);
                resepter.leggTil(resept);
            } catch (UlovligUtskrift u){
                System.out.println(u);
            }
        }

        //undermeny for aa skrive ut forskjellig statestikk
        else if (valg == 4){
            System.out.print("Reit: ");
            int reit = tastatur.nextInt();
            try {
                Resept resept = legeResept.skrivBlaaResept(legemiddelResept, pasientResept, reit);
                resepter.leggTil(resept);
            } catch (UlovligUtskrift u){
                System.out.println(u);
            }
        }

        System.out.println("Resept fra " + legeResept.hentNavn() + " er gitt til " + pasientResept.hentNavn() + " for legemiddelet " + legemiddelResept.hentNavn() + ".");
    }

    public static void leggTilLegemiddel(){
        // Oppretter scanner-objekt
        Scanner tastatur = new Scanner(System.in);

        // Meny for "type" resept
        for (int i = 0; i < 30 ; i ++){
            System.out.println();
        }

        System.out.println("========Legemiddel========");
        System.out.println("|1.| Vanlig              |");
        System.out.println("|2.| Narkotisk           |");
        System.out.println("|3.| Vanedannende        |");
        System.out.println("==========================");

        int valg = taInput(1,3);

        // Henter informasjon fra bruker og legger til

        System.out.print("Navn (Legemiddel): ");
        String legemiddelNavn = tastatur.nextLine();
        System.out.print("Pris: ");
        int pris = tastatur.nextInt();
        System.out.print("Virkestoff (skriv inn double): ");
        while (!tastatur.hasNextDouble()){
            System.out.println("Ugyldig input - må være double :");
            tastatur.next();
        }
        double virkestoff = tastatur.nextDouble();

        if (valg == 1){
            legemidler.leggTil(new Vanlig(legemiddelNavn, pris, virkestoff));
        }

        //Oppretter og legger til nye elementer i systemet
        else if (valg == 2){
            System.out.print("Styrke: ");
            int styrke = tastatur.nextInt();
            legemidler.leggTil(new Narkotisk(legemiddelNavn, pris, virkestoff, styrke));
        }

        //bruker en gitt resept fra listen til en pasient
        else if (valg == 3){
            System.out.print("Styrke (int): ");
            int styrke = tastatur.nextInt();
            legemidler.leggTil(new Vanedannende(legemiddelNavn, pris, virkestoff, styrke));
        }

        System.out.println("Legemiddelet " + legemiddelNavn + " er lagt til.");
    }


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
    public static int taInput(int start, int slutt){
        System.out.print("Tast inn valg:");
        try{
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            //if testen sjekkar at tallet er innafor menyvalga
            if (i < start || i > slutt){
                System.out.println("Velg tall mellom " +start+ " og " +slutt);
                i = taInput(start,slutt);
            }
            return i;
        //catch blokka fanger opp om brukeren skriver inn tegn som ikkje er en int   
        }catch(InputMismatchException e){
            System.out.println("Ugyldig inntasting! Kun heltall!");
            return taInput(start,slutt);
        }
        
    }

    //ventar til brukeren trykker paa enter for aa gaa tilbake til hovedmenyen
    public static void gaaTilbake(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Trykk 'enter' for aa gaa tilbake >");
        sc.nextLine();
    }
}
