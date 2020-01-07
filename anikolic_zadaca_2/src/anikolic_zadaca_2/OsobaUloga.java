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
public class OsobaUloga {
    
    private Osoba osoba;
    private Uloga uloga;

    public OsobaUloga() {
    }

    public OsobaUloga(Osoba osoba, Uloga uloga) {
        this.osoba = osoba;
        this.uloga = uloga;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
    
    
}
