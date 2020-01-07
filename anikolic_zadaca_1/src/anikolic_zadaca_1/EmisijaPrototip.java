/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

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
    private int trajanje;
    private Map<Integer, List<Integer>> osobaUloga;
    
    public EmisijaPrototip() {
        osobaUloga = new HashMap<>();
    }

    public EmisijaPrototip(int id, String naziv, int trajanje, Map<Integer, List<Integer>> osobaUloga) {
        this.id = id;
        this.naziv = naziv;
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

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Map<Integer, List<Integer>> getOsobaUloga() {
        return osobaUloga;
    }

    public void setOsobaUloga(Map<Integer, List<Integer>> osobaUloga) {
        this.osobaUloga = osobaUloga;
    }

    
    
}
