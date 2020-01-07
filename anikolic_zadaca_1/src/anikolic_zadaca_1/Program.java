/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class Program {
    private int id;
    private String naziv;
    private List<Emisija> emisijePrograma;
    //private Map<Integer, List<PrikazEmisije>> prikaz;
        private Map<Integer, PrikazEmisije> prikaz;
    private LocalTime pocetakPrograma;
    private LocalTime krajPrograma;

    public Program() {
        emisijePrograma = new ArrayList<>();
        prikaz = new HashMap<>();
    }

    public Program(int id, String naziv, List<Emisija> emisijePrograma, Map<Integer, PrikazEmisije> prikaz, LocalTime pocetakPrograma, LocalTime krajPrograma) {
        this.id = id;
        this.naziv = naziv;
        this.emisijePrograma = emisijePrograma;
        this.prikaz = prikaz;
        this.pocetakPrograma = pocetakPrograma;
        this.krajPrograma = krajPrograma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Emisija> getEmisijePrograma() {
        return emisijePrograma;
    }

    public void setEmisijePrograma(List<Emisija> emisijePrograma) {
        this.emisijePrograma = emisijePrograma;
    }

    
    public LocalTime getPocetakPrograma() {
        return pocetakPrograma;
    }

    public void setPocetakPrograma(LocalTime pocetakPrograma) {
        this.pocetakPrograma = pocetakPrograma;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalTime getKrajPrograma() {
        return krajPrograma;
    }

    public void setKrajPrograma(LocalTime krajPrograma) {
        this.krajPrograma = krajPrograma;
    }

    public Map<Integer, PrikazEmisije> getPrikaz() {
        return prikaz;
    }

    public void setPrikaz(Map<Integer, PrikazEmisije> prikaz) {
        this.prikaz = prikaz;
    }
    
    
    
    
    
}
