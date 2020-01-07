/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

import composite.RasporedDana;
import composite.RasporedElement;
import composite.RasporedPrograma;
import composite.RasporedTvKuce;
import iterator.Iterator;
import iterator.KolekcijaRaspored;

/**
 *
 * @author Ana
 */
public class DecoratorPoVrsti extends DecoratorBase {

    private int vrsta;

    public DecoratorPoVrsti(Decorator wrappee) {
        super(wrappee);
    }

    @Override
    public void dekorirajIspis() {
        super.dekorirajIspis();
        String line = "______________________________________________________"
                + "______________________________________________";

        System.out.println(line);
        System.out.println(String.format("%6s %6s %-38s %-18s %-8s %-24s", "POCETAK",
                "KRAJ", "NAZIV", "VRSTA", "REKLAME", "SUDIONICI"));
        System.out.println(line);
        RasporedTvKuce tvKuca = (RasporedTvKuce) super.getWrappee();
        KolekcijaRaspored kol = new KolekcijaRaspored(tvKuca);
        Iterator poVrsti = kol.stvoriIteratorPoVrsti(vrsta);
        RasporedElement trenutni = poVrsti.first();
        RasporedDana dan = trenutni.getParent();
        RasporedPrograma program = dan.getParent();
        program.dekorirajIspis();
        dan.dekorirajIspis();
        while (poVrsti.hasNext()) {
            trenutni = poVrsti.next();
            if (trenutni.getParent() != dan) {
                if (trenutni.getParent().getParent() != program) {
                    program = trenutni.getParent().getParent();
                    program.dekorirajIspis();
                }
                dan = trenutni.getParent();
                dan.dekorirajIspis();
            }
            trenutni.dekorirajIspis();
        }
        System.out.println(line);
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }
}
