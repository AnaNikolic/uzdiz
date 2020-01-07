/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import bridge.DodavanjeEmisijaSucelje;
import prototype.Emisija;
import anikolic_zadaca_2.Osoba;
import composite.RasporedTvKuce;
import anikolic_zadaca_2.Uloga;
import anikolic_zadaca_2.Vrsta;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public interface GraditeljRasporeda {

    GraditeljRasporeda dodajPodatkeTvKuce(Map<Integer, Osoba> osobe, Map<Integer, Uloga> uloge,
            Map<Integer, Vrsta> vrste, Map<Integer, Emisija> emisije);

    GraditeljRasporeda postaviPodatkePrograma(List<List<String>> podaciPrograma);

    GraditeljRasporeda postaviAlgoritme(List<DodavanjeEmisijaSucelje> listaAlgoritama);

    RasporedTvKuce build();

}
