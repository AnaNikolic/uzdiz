/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import decorator.Decorator;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Ana
 */
public class RasporedPrograma extends KomponentaRasporedaApstr implements KomponentaRasporeda, Decorator {

    private RasporedTvKuce parent;
    private int id;
    private String naziv;
    private LocalTime pocetak;
    private LocalTime kraj;

    public RasporedPrograma(RasporedTvKuce parent) {
        for (int i = 0; i < 7; i++) {
            children.add(new RasporedDana(i + 1, this));
        }
    }

    public RasporedPrograma(int id, String naziv, LocalTime pocetak, LocalTime kraj) {
        this.id = id;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    @Override
    public void ispisiVremenskiPlan(int program, int dan) {
        if (program == 0 || program == this.id) {
            System.out.println("_____________________________\nProgram: " + id
                    + " " + naziv + "\n" + pocetak + " " + kraj);
            for (KomponentaRasporeda child : children) {
                child.ispisiVremenskiPlan(program, dan);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }

    public RasporedTvKuce getParent() {
        return parent;
    }

    @Override
    public void dekorirajIspis() {
        String line = "__________________________________________________________"
                + "________________________________________________________________________";
        System.out.println(line);
        System.out.println("    PROGRAM: " + id + " " + naziv);
        System.out.println(line);
    }
}

