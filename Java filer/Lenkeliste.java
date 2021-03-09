/*
Klassen Lenkeliste har T som klasseparameter og implementerer grensesnittet Liste
Lenkelista er en enkeltlenka liste med en startnode peker.
Klassen inneholder dei indre klassene Node og LenkelisteIterator

Metoder:
    * public void sett(int pos, T x)
    * public void leggTil(T x)
    * public void leggTil(int pos,T x)
    * public T fjern()
    * public T fjern(int pos)
    * public T hent(int pos)
    * public int stoerrelse()
    * public Iterator<T> iterator()

Variabler:
    * protected Node start


*/

import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>{
    
    /*
    _____________________________________________________________________________________________________
        Klassen Noden har peker til neste node og peker til data av typen T
    */
    public class Node{
        private Node neste = null;
        private T data;

        public Node(T x){
            data = x;
        }
        public void settNeste(Node node){
            neste = node;
        }
        public Node hentNeste(){
            return neste;
        }
        public void skrivData(T x){
            data = x;
        }
        public T hentData(){
            return data;
        }

    }


    /* 
    _______________________________________________________________________________________________________
        Indre klasse for LenkelisteIterator
            * Metodane hasNext og next lar oss bruke en for each lokke i 
              Lenkeliste, Stabel og SortertLenkeliste
    */

    public class LenkelisteIterator implements Iterator<T>{
        
        protected int pos = 0;
        protected Lenkeliste<T> lenkeliste;

        public LenkelisteIterator (Lenkeliste<T> lenkeliste){
            this.lenkeliste = lenkeliste;
        }

        @Override
        public boolean hasNext(){
            return (pos < lenkeliste.stoerrelse());
        }

        @Override
        public T next(){
            return (lenkeliste.hent(pos++));
        }
    }


    //startnoden til lenkelista
    protected Node start = null;
    

    //setter inn overskriver dataen til noden paa indeks pos med dataen x
    @Override
    public void sett(int pos, T x)throws UgyldigListeIndeks{
            if (pos < 0 || pos >= stoerrelse()){
                throw new UgyldigListeIndeks(pos);
            }
            Node gjeldende = start;
            for (int i = 0; i < pos; i++){
                gjeldende = gjeldende.hentNeste();
            }
            gjeldende.skrivData(x);
            
        }
        
    //legger til en node med data x sist i lenkelista
    @Override
    public void leggTil(T x){
        if (stoerrelse() == 0){
            start = new Node(x);
        }else{
            Node gjeldende = start;
            Node forrige = null;
            while(gjeldende !=null){
                forrige = gjeldende;
                gjeldende = gjeldende.hentNeste();
            }
            forrige.settNeste(new Node(x));
        }

    }

    //fjerner den siste noden i lista og returnerer nodens data
    @Override
    public T fjern() throws UgyldigListeIndeks{
       
        if (stoerrelse() == 0){
            throw new UgyldigListeIndeks(0);
        }else{
            T data = start.hentData();
            start = start.hentNeste();
            return data;
        }
        
    }

    //legger til en node med data x paa indeks pos
    @Override
    public void leggTil(int pos,T x)throws UgyldigListeIndeks{
        if (pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeIndeks(pos);
        }
        if (stoerrelse() == 0){
            start = new Node(x);
            
        }else{
            Node gjeldande = start;
            Node forrige = null;
            for (int i = 0; i < pos; i++){
                forrige = gjeldande;
                gjeldande = gjeldande.hentNeste();
            }
            Node n1 = new Node(x);
            
            n1.settNeste(gjeldande);
            if (forrige == null){
                start = n1;
            }else{
                forrige.settNeste(n1);
            }
            
           
        }
        
    }

    //fjerner noden som er paa index pos og returnerer nodens data
    @Override
    public T fjern(int pos)throws UgyldigListeIndeks{
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeIndeks(pos);
        }
        Node gjeldende = start;
        Node forrige = null;
        if (stoerrelse() == 1){
            T data = gjeldende.hentData();
            start = null;
            return data;
        }
        for (int i = 0; i < pos;i++){
            forrige = gjeldende;
            gjeldende = gjeldende.hentNeste();
        } 
        T data = gjeldende.hentData();
        forrige.settNeste(gjeldende.hentNeste());
        return data;
    }

    //returnerer dataen til en node paa den gitte indeksen pos
    @Override
    public T hent(int pos)throws UgyldigListeIndeks{
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeIndeks(pos);
        }
        Node gjeldende = start;
        for (int i = 0; i < pos; i++){
            gjeldende = gjeldende.hentNeste();
        }
        return gjeldende.hentData();
    }

    //returnerer antall noder i lenkelista
    @Override
    public int stoerrelse(){
        Node gjeldende = start;
        int teller = 0;
        while(gjeldende != null){
            gjeldende = gjeldende.hentNeste();
            teller ++;
        }
        return teller;
    }
    
    //Skriver ut dataen til alle nodene i lenkelista separert med "|" 
    public String toString(){
        String returstreng = "| ";
        Node gjeldende = start;
        while(gjeldende != null){
            returstreng = returstreng+gjeldende.hentData() + " | ";
            gjeldende = gjeldende.hentNeste();
        }
        return returstreng;
    }

    //Metoden lager et nytt Iterator objekt, som  lar oss bruke en for each lokke
    @Override
    public Iterator<T> iterator(){
        return new LenkelisteIterator(this);
    }
    

}

