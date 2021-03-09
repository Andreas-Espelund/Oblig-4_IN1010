import java.util.InputMismatchException;
import java.util.Scanner;


public class Legesystem {
    public static void main(String[] args) {
        int valg = 0;
        
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

    public static void skrivFraFil(String filnavn){}

    public static void skrivUtOversikt(){}

    public static void leggTilElementer(){}

    public static void brukResept(){}

    public static void skrivUtStatestikk(){}

    public static void skrivAlleDataTilFil(){}


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
