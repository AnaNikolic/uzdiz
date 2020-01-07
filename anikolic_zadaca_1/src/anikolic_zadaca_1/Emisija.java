/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class Emisija extends EmisijaPrototip{

    public Emisija(int id, String naziv, int trajanje, Map<Integer, List<Integer>> osobaUloga) {
        super(id, naziv, trajanje, osobaUloga);
    }

    @Override
    protected EmisijaPrototip kloniraj() {
        return new Emisija(getId(), getNaziv(), getTrajanje(), getOsobaUloga());
    }
    
}
