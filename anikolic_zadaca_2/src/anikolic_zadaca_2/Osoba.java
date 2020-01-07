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
public class Osoba {

    private int id;
    private String imePrezime;

    public Osoba() {
    }

    public Osoba(int id, String imePrezime) {
        this.id = id;
        this.imePrezime = imePrezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

}
