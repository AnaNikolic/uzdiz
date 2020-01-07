package anikolic_zadaca_2;


import anikolic_zadaca_2.OsobaUloga;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana
 */
public class PrikazEmisije {
    private Integer dan;
    private LocalTime pocetak;

    public PrikazEmisije() {
    }

    public PrikazEmisije(Integer dan, LocalTime pocetak) {
        this.dan = dan;
        this.pocetak = pocetak;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public Integer getDan() {
        return dan;
    }

    public void setDan(Integer dan) {
        this.dan = dan;
    }

}
