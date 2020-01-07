/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ana
 */
public class GradiRaspored implements GraditeljRasporeda {

    Program program;
    Raspored raspored;

    List<DodavanjeEmisijaSucelje> listaAlgoritama = new ArrayList<>();
    
    @Override
    public GraditeljRasporeda dodajProgram(Program program) {
        this.program = program;
        return this;
    }
    
    @Override
    public GraditeljRasporeda postaviRaspored() {
        raspored = new Raspored();
        raspored.setId(program.getId());
        raspored.setNaziv(program.getNaziv());
        raspored.setPocetakPrograma(program.getPocetakPrograma());
        raspored.setKrajPrograma(program.getKrajPrograma());
        return this;
    }


    @Override
    public Raspored zavrsiRaspored() {
        for (DodavanjeEmisijaSucelje algoritam : listaAlgoritama){
            raspored = algoritam.gradiRaspored(program, raspored);
        }
        return raspored;
    }

    @Override
    public GraditeljRasporeda postaviAlgoritme(List<DodavanjeEmisijaSucelje> listaAlgoritama) {
        this.listaAlgoritama = listaAlgoritama;
        return this;
    }

}
