/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.util.List;

/**
 *
 * @author Ana
 */
public interface GraditeljRasporeda {
    
    Raspored zavrsiRaspored();
    
    GraditeljRasporeda dodajProgram(Program program);
    
    GraditeljRasporeda postaviRaspored();
    
    GraditeljRasporeda postaviAlgoritme(List<DodavanjeEmisijaSucelje> listaAlgoritama);
    
    
    
    
}
