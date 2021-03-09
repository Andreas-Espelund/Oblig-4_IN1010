/*
Stabel har T som klasseparameter og er en subklasse av Lenkeliste

Stabelen benytter seg av First in,last out prinsippet
*/

class Stabel<T> extends Lenkeliste<T>{

    //legger til x i en node paa slutten av stabelen
    public void leggPaa(T x){
        leggTil(x);
    }

    //taAv fjerner den siste noden i stabelen og returnerer nodens data
    public T taAv(){
        int pos = stoerrelse() - 1;
        T data = fjern(pos);
        return data;

    }
}