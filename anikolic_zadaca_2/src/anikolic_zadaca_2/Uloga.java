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
public class Uloga {
    
    private int id;
    private String naziv;

    public Uloga(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Uloga() {
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
    
    
    
}
