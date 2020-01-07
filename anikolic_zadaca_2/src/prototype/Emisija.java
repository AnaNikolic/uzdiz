/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import anikolic_zadaca_2.OsobaUloga;
import anikolic_zadaca_2.Vrsta;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class Emisija extends EmisijaPrototip{

    public Emisija(int id, String naziv, Vrsta vrsta, int trajanje, List<OsobaUloga> osobaUloga) {
        super(id, naziv, vrsta, trajanje, osobaUloga);
    }

    @Override
    public EmisijaPrototip kloniraj() {
        return new Emisija(getId(), getNaziv(), getVrsta(), getTrajanje(), getOsobaUloga());
    }
    
}
