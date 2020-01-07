/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ana
 */
public class AlgoritamDodavanjaEmisija3 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public Raspored gradiRaspored(Program program, Raspored raspored) {
        Map<Integer, Dan> daniRasporeda = raspored.getDani();
        Map<Integer, PrikazEmisije> prikazi = program.getPrikaz();
        List<Emisija> sveEmisijePrograma = program.getEmisijePrograma();
        for (Emisija trenutnaEmisija : sveEmisijePrograma) {
            if (prikazi.containsKey(trenutnaEmisija.getId())) {
                continue;
            }
            LocalTime vrijemePocetka = null;
            for (Integer kojiDan = 1; kojiDan <= 7; kojiDan++) {
                Dan d = daniRasporeda.get(kojiDan);
                TreeMap<LocalTime, Emisija> emisijeDana = (TreeMap<LocalTime, Emisija>) d.getEmisije();
                vrijemePocetka = nadiVrijemeZaEmisiju(raspored.getPocetakPrograma(),
                        raspored.getKrajPrograma(), emisijeDana, trenutnaEmisija.getTrajanje());
                if (vrijemePocetka != null) {
                    emisijeDana.put(vrijemePocetka, trenutnaEmisija);
                    d.setEmisije(emisijeDana);
                    daniRasporeda.put(kojiDan, d);
                    break;
                }
            }
            if (vrijemePocetka == null) {
                System.out.println("Ne mogu nigdje ubaciti emisiju " + trenutnaEmisija.getNaziv());
            }
        }
        raspored.setDani(daniRasporeda);
        return raspored;
    }

}
