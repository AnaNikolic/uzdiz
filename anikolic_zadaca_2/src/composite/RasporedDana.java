/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import decorator.Decorator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Ana
 */
public class RasporedDana extends KomponentaRasporedaApstr implements KomponentaRasporeda, Decorator {

    private RasporedPrograma parent;
    private int danTjedna;

    public RasporedDana(RasporedPrograma parent) {
        children = new ArrayList<>();
        this.parent = parent;
    }

    public RasporedDana(int danTjedna, RasporedPrograma parent) {
        this.danTjedna = danTjedna;
        children = new ArrayList<>();
        this.parent = parent;
    }

    public void sortirajPoVremenu() {
        class SortER implements Comparator<KomponentaRasporeda> {

            @Override
            public int compare(KomponentaRasporeda k1, KomponentaRasporeda k2) {
                RasporedElement re1 = (RasporedElement) k1;
                RasporedElement re2 = (RasporedElement) k2;
                return re1.getPocetak().compareTo(re2.getPocetak());
            }
        }
        Collections.sort(children, new SortER());
    }

    public int getDanTjedna() {
        return danTjedna;
    }

    public RasporedPrograma getParent() {
        return parent;
    }

    @Override
    public void ispisiVremenskiPlan(int program, int dan) {
        if (dan == 0 || dan == this.danTjedna) {
            System.out.println("Dan tjedna: " + danTjedna);
            if (children.isEmpty()) {
                System.out.println("Nema emisija.");
            }
            for (KomponentaRasporeda child : children) {
                child.ispisiVremenskiPlan(program, dan);
            }
            System.out.println("--------------");
        }
    }

    @Override
    public void dekorirajIspis() {
        String line = "__________________________________________________________"
                + "________________________________________________________________________";
        System.out.println(line);
        System.out.println("    DAN: " + danTjedna);
        System.out.println(line);
    }

}
