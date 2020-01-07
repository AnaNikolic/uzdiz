/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import anikolic_zadaca_2.OsobaUloga;
import anikolic_zadaca_2.Uloga;
import decorator.Decorator;
import prototype.Emisija;
import java.time.LocalTime;
import java.util.List;
import observer.Observer;

/**
 *
 * @author Ana
 */
public class RasporedElement implements KomponentaRasporeda, Observer, Decorator {

    private RasporedDana parent;
    private Emisija emisija;
    private LocalTime pocetak;

    public RasporedElement(RasporedDana parent) {
        this.parent = parent;
    }

    public RasporedElement(Emisija emisija, LocalTime pocetak, RasporedDana parent) {
        this.emisija = emisija;
        this.pocetak = pocetak;
        this.parent = parent;
    }

    public Emisija getEmisija() {
        return emisija;
    }

    public void setEmisija(Emisija emisija) {
        this.emisija = emisija;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public RasporedDana getParent() {
        return parent;
    }

    @Override
    public void ispisiVremenskiPlan(int program, int dan) {
        System.out.println("Emisija: " + emisija.getId() + ": " + emisija.getNaziv()
                + "\nPrikaz: " + pocetak + " - " + pocetak.plusMinutes(emisija.getTrajanje()));
        System.out.println("Vrsta: " + emisija.getVrsta().getId() + " - " + emisija.getVrsta().getNaziv());
        for (OsobaUloga ou : emisija.getOsobaUloga()) {
            System.out.println(ou.getOsoba().getImePrezime() + " - " + ou.getUloga().getNaziv());
        }
        System.out.println("--------------");
    }

    @Override
    public void update(int osoba, int staraUloga, Uloga novaUloga) {
        List<OsobaUloga> lista = this.emisija.getOsobaUloga();
        for (OsobaUloga os : lista) {
            if (os.getOsoba().getId() == osoba && os.getUloga().getId() == staraUloga) {
                os.setUloga(novaUloga);
            }
        }
    }

    @Override
    public void dekorirajIspis() {
        String os = "";
        for (OsobaUloga ou : emisija.getOsobaUloga()) {
            os += ou.getOsoba().getImePrezime() + " - " + ou.getUloga().getNaziv() + "; ";
        }
        System.out.println(String.format("%6s %6s %s %-36s %s %-16s %s %6s %s %20s",
                pocetak, pocetak.plusMinutes(emisija.getTrajanje()), "|",
                emisija.getNaziv(), "|", emisija.getVrsta().getNaziv(), "|",
                emisija.getVrsta().getTrajanjeReklama(), "|", os));
    }

}
