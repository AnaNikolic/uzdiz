/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import bridge.AlgoritamDodavanjaEmisija1;
import bridge.AlgoritamDodavanjaEmisija2;
import bridge.AlgoritamDodavanjaEmisija3;
import bridge.DodavanjeEmisijaSucelje;
import prototype.Emisija;
import anikolic_zadaca_2.Osoba;
import composite.RasporedTvKuce;
import anikolic_zadaca_2.Uloga;
import anikolic_zadaca_2.Vrsta;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class DirektorGraditeljaRasporeda {
    
    private Map<Integer, Osoba> osobe;
    private Map<Integer, Uloga> uloge;
    private Map<Integer, Vrsta> vrste;
    private Map<Integer, Emisija> sveEmisije;
    private List<List<String>> podaciPrograma;
    
    private GradiRaspored gradiRaspored;
    

    public DirektorGraditeljaRasporeda() {
    }

    public DirektorGraditeljaRasporeda(Map<Integer, Osoba> osobe, Map<Integer, Uloga> uloge, 
            Map<Integer, Vrsta> vrste, Map<Integer, Emisija> sveEmisije, List<List<String>> podaciPrograma) {
        this.osobe = osobe;
        this.uloge = uloge;
        this.vrste = vrste;
        this.sveEmisije = sveEmisije;
        this.podaciPrograma = podaciPrograma;
    }
    
    public RasporedTvKuce izgradiRaspored(){
        if (gradiRaspored == null){
            gradiRaspored = new GradiRaspored();
        }
        
        List<DodavanjeEmisijaSucelje> redosljedDodavanja = new ArrayList<>();
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija1());
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija2());
        redosljedDodavanja.add(new AlgoritamDodavanjaEmisija3());
        
        RasporedTvKuce rasporedTvKuce = gradiRaspored
                    .dodajPodatkeTvKuce(osobe, uloge, vrste, sveEmisije)
                    .postaviPodatkePrograma(podaciPrograma)
                    .postaviAlgoritme(redosljedDodavanja)
                    .build();

        return rasporedTvKuce;
    }
}
