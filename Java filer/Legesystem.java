import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class Legesystem {

    public static void main(String[] args) {

        int valg = 0;
        Lenkeliste<Pasienter> pasienter = new Lenkeliste<Pasienter>();
        Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
        Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
        SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    
        //initialisering av program
        skrivFraFil("plassholder");
        // Kommandoloekke
        while (valg != 6){
            skrivMeny();
            valg = taInput();

            // skriver ut fullstendig oversikt over pasienter, leger, legemidler og reseptar
            if (valg == 1){
                System.out.println("meny 1");
                skrivUtOversikt();
                gaaTilbake();
            }
            

            //Oppretter og legger til nye elementer i systemet
            else if (valg == 2){
                System.out.println("meny 2");
                leggTilElementer();
                gaaTilbake();
            }


            //bruker en gitt resept fra listen til en pasient
            else if (valg == 3){
                System.out.println("meny 3");
                brukResept();
                gaaTilbake();
            }


            //undermeny for aa skrive ut forskjellig statestikk
            else if (valg == 4){
                System.out.println("meny 4");
                skrivUtStatestikk();
                gaaTilbake();
            }


            //skriver all data til fil
            else if(valg == 5){
                System.out.println("meny 5");
                skrivAlleDataTilFil();
                gaaTilbake();
            }

        }
        //kommandoloekka er ferdig
        System.out.println("avslutter");
    }

    

    /*
    Metodar som blir brukt i oppgave E

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
                    String navn = biter[0];
                    String foedselsNr = biter[1];
                    pasienter.leggTil(new Pasient(navn,foedselsNr));  
                }

                //Legger til legemidler
                else if (i == 2){
                    String navn = biter[0];
                    String type = biter[1];
                    int pris = Integer.parseInt(biter[2]);
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
                }

                //legger til leger
                else if (i == 3){
                    String navn = biter[0];
                    String kontrollID = biter[1];
                    if (kontrollID.equals("0")){
                        leger.leggTil(new Lege(navn));
                    }else{
                        leger.leggTil(new Spesialist(navn, kontrollID));
                    }
                }

                //legger til resepter
                else if (i == 4){
                    int legemiddelnummer = Integer.parseInt(biter[0]);
                    String legeNavn = biter[1];
                    int pasientID = Integer.parseInt(biter[2]);
                    String type = biter[3];
                    int reit;

                    Legemiddel legemiddel;
                    Pasient pasient;
                    Lege lege;
                    Pasient pasient;

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

                }



            }
        }catch (FileNotFoundException e){
            System.out.println("Ugyldig filnavn! Skriv inn riktig filnavn >");
            Scanner inp = new Scanner(System.in);
            skrivFraFil(inp.nextLine());
        }
        
    }

    public static void skrivUtOversikt(){}

    public static void leggTilElementer(){}

    public static void brukResept(){}

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
