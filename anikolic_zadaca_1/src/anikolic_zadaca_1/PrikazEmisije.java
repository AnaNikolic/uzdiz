/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class PrikazEmisije {

    private List<Integer> danTjedna;
    private LocalTime pocetak;

    public PrikazEmisije() {
        danTjedna = new ArrayList<>();
        pocetak = null;
    }

    public PrikazEmisije(List<Integer> danTjedna, LocalTime pocetak) {
        this.danTjedna = danTjedna;
        this.pocetak = pocetak;
    }

    public List<Integer> getDanTjedna() {
        return danTjedna;
    }

    public void setDanTjedna(List<Integer> danTjedna) {
        this.danTjedna = danTjedna;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }
    
    
}
