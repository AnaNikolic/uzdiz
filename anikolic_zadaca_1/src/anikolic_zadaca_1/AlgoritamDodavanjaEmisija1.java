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
public class AlgoritamDodavanjaEmisija1 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public Raspored gradiRaspored(Program program, Raspored raspored) {
        Map<Integer, Dan> daniRasporeda = raspored.getDani();
        Map<Integer, PrikazEmisije> prikazi = program.getPrikaz();
        List<Emisija> sveEmisijePrograma = program.getEmisijePrograma();
        for (Map.Entry prikaz : prikazi.entrySet()) {
            PrikazEmisije prikazEmisije = (PrikazEmisije) prikaz.getValue();
            if (prikazEmisije.getPocetak() != null) {
                List<Integer> daniPrikaza = prikazEmisije.getDanTjedna();
                for (Integer kojiDan : daniPrikaza) {
                    Dan d = daniRasporeda.get(kojiDan);
                    TreeMap<LocalTime, Emisija> emisijeDana = new TreeMap<>();
                    if (!d.getEmisije().isEmpty()) {
                        emisijeDana = (TreeMap<LocalTime, Emisija>) d.getEmisije();
                    }
                    Emisija e = dajEmisiju((Integer) prikaz.getKey(), sveEmisijePrograma);
                    PrikazEmisije pr = (PrikazEmisije) prikaz.getValue();
                    LocalTime vrijemePrikaza = pr.getPocetak();

                    if (!provjeriVremenskaPreklapanja(raspored.getPocetakPrograma(),
                            raspored.getKrajPrograma(), vrijemePrikaza, e.getTrajanje(), emisijeDana)) {
                        emisijeDana.put(vrijemePrikaza, e);
                    } else {
                        System.out.println("Emisija " + e.getNaziv() + "se vremenski preklapa!");
                    }
                    d.setEmisije(emisijeDana);
                    daniRasporeda.put(kojiDan, d);
                }
            }
        }
        raspored.setDani(daniRasporeda);
        return raspored;
    }

}
