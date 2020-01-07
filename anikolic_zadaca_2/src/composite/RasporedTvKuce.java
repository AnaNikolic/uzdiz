/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import anikolic_zadaca_2.Uloga;
import decorator.Decorator;
import iterator.Iterator;
import iterator.KolekcijaRaspored;
import observer.Subject;

/**
 *
 * @author Ana
 */
public class RasporedTvKuce extends KomponentaRasporedaApstr implements KomponentaRasporeda, Subject, Decorator{

    public RasporedTvKuce() {       
    }
    
    @Override
    public void ispisiVremenskiPlan(int program, int dan) {
        for(KomponentaRasporeda child : children){
            child.ispisiVremenskiPlan(program, dan);
        }
    }

    @Override
    public void notifySubscriber(int osoba, int staraUloga, Uloga novaUloga) {
        KolekcijaRaspored kolekcija = new KolekcijaRaspored(this);
        Iterator iteratorSvi = kolekcija.stvoriIteratorSvi();
        while (iteratorSvi.hasNext()) {
            RasporedElement trenutni = iteratorSvi.next();
            trenutni.update(osoba, staraUloga, novaUloga);
        }
    }

    @Override
    public void dekorirajIspis() {
        System.out.println("Ispisujem raspored tvKuće!");
    }

    

}
