/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class DirektorGraditeljaRasporeda {
    
    private GradiRaspored gradiRaspored;

    public DirektorGraditeljaRasporeda() {
    }
    
    public List<Raspored> izgradiRaspored(List<Program> programi){
        if (gradiRaspored == null){
            gradiRaspored = new GradiRaspored();
        }
        
        List<DodavanjeEmisijaSucelje> redosljedDodavanja = new ArrayList<>();
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija1());
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija2());
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija3());
        
        List<Raspored> rasporediPrograma = new ArrayList<>();
        for (Program p: programi){
            Raspored r = gradiRaspored
                    .dodajProgram(p)
                    .postaviRaspored()
                    .postaviAlgoritme(redosljedDodavanja)
                    .zavrsiRaspored();
            rasporediPrograma.add(r);
        }
        return rasporediPrograma;
    }
    
}
