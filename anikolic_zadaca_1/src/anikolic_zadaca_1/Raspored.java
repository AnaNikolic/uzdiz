/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ana
 */
public class Raspored{
    
    private Integer id;
    private String naziv;
    private LocalTime pocetakPrograma;
    private LocalTime krajPrograma;
    private Map<Integer, Dan> tjedan;

    public Raspored() {
        tjedan = new HashMap<>();
        for (int i = 1; i <= 7; i++){
            tjedan.put(i, new Dan());
        }
    }

    public Raspored(Integer id, String naziv, LocalTime pocetakPrograma, LocalTime krajPrograma, Map<Integer, Dan> dani) {
        this.id = id;
        this.naziv = naziv;
        this.pocetakPrograma = pocetakPrograma;
        this.krajPrograma = krajPrograma;
        this.tjedan = dani;
    }

    public Map<Integer, Dan> getDani() {
        return tjedan;
    }

    public void setDani(Map<Integer, Dan> dani) {
        this.tjedan = dani;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalTime getPocetakPrograma() {
        return pocetakPrograma;
    }

    public void setPocetakPrograma(LocalTime pocetakPrograma) {
        this.pocetakPrograma = pocetakPrograma;
    }

    public LocalTime getKrajPrograma() {
        return krajPrograma;
    }

    public void setKrajPrograma(LocalTime krajPrograma) {
        this.krajPrograma = krajPrograma;
    }

    
    
}
