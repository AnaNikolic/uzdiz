/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import anikolic_zadaca_2.OsobaUloga;
import anikolic_zadaca_2.Vrsta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public abstract class EmisijaPrototip {

    private int id;
    private String naziv;
    private Vrsta vrsta;
    private int trajanje;
    private List<OsobaUloga> osobaUloga;

    public EmisijaPrototip() {
        osobaUloga = new ArrayList<>();
    }

    public EmisijaPrototip(int id, String naziv, Vrsta vrsta, int trajanje, List<OsobaUloga> osobaUloga) {
        this.id = id;
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.trajanje = trajanje;
        this.osobaUloga = osobaUloga;
    }

    protected abstract EmisijaPrototip kloniraj();

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

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public List<OsobaUloga> getOsobaUloga() {
        return osobaUloga;
    }

    public void setOsobaUloga(List<OsobaUloga> osobaUloga) {
        this.osobaUloga = osobaUloga;
    }


}
