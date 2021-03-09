/*
SorterLenkeliste er en subklasse av Lenkeliste og har T som klasseparameter, der
T er en subklasse av Comparable
*/

public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
    
    //metoden legg til legger til dataen i sortert rekkefolge fra minst til storst.
    @Override
    public void leggTil(T x){
        if (start == null){
            start = new Node(x);
        }else{
            Node gjeldende = start;
            int index = 0;
            while(gjeldende != null && gjeldende.hentData().compareTo(x) < 0){
                gjeldende = gjeldende.hentNeste();
                index ++;
            }
            super.leggTil(index, x);

        }
    }

    
    //fjerner den stoerste noden i lenkelista og returnerer dataen, dette er den siste
    //noden siden lista er sortert fra minst til storst.
    @Override
    public T fjern()throws UgyldigListeIndeks{
        return fjern(stoerrelse() - 1);
    }
   
    //thrower exception siden disse metodene ikke skal kunne brukes i en sortert lenkeliste
    @Override
    public void sett(int pos, T x)throws UnsupportedOperationException{
        throw new UnsupportedOperationException(" Ikke gyldig metode for sortert lenkeliste");
    }


    //thrower exception siden disse metodene ikke skal kunne brukes i en sortert lenkeliste
    @Override
    public void leggTil(int pos, T x)throws UnsupportedOperationException{
        throw new UnsupportedOperationException(" Ikke gyldig metode for sortert lenkeliste");
    }
 
    
}
        

        

    