/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_2;

/**
 *
 * @author Ana
 */
public class Vrsta {
    
    private Integer id;
    private String naziv;
    private Integer trajanjeReklama = 0;

    public Vrsta() {
    }

    public Vrsta(int id, String naziv, int trajanjeReklama) {
        this.id = id;
        this.naziv = naziv;
        this.trajanjeReklama = trajanjeReklama;
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

    public int getTrajanjeReklama() {
        return trajanjeReklama;
    }

    public void setTrajanjeReklama(int trajanjeReklama) {
        this.trajanjeReklama = trajanjeReklama;
    }
    
    
    
}
