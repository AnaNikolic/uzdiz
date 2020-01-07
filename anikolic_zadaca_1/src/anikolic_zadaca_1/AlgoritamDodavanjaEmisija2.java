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
public class AlgoritamDodavanjaEmisija2 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public Raspored gradiRaspored(Program program, Raspored raspored) {
        Map<Integer, Dan> daniRasporeda = raspored.getDani();
        Map<Integer, PrikazEmisije> prikazi = program.getPrikaz();
        List<Emisija> sveEmisijePrograma = program.getEmisijePrograma();

        for (Map.Entry prikaz : prikazi.entrySet()) {                            //emisijw
            PrikazEmisije prikazEmisije = (PrikazEmisije) prikaz.getValue();
            if (prikazEmisije.getPocetak() == null) {
                List<Integer> daniPrikaza = prikazEmisije.getDanTjedna();
                for (Integer kojiDan : daniPrikaza) {                          //u dan
                    Dan d = daniRasporeda.get(kojiDan);
                    TreeMap<LocalTime, Emisija> emisijeDana = new TreeMap<>();
                    if (!d.getEmisije().isEmpty()) {
                        emisijeDana = (TreeMap<LocalTime, Emisija>) d.getEmisije();
                    }
                    Emisija e = dajEmisiju((Integer) prikaz.getKey(), sveEmisijePrograma);
                    LocalTime vrijemePocetka = nadiVrijemeZaEmisiju(raspored.getPocetakPrograma(),
                            raspored.getKrajPrograma(), emisijeDana, e.getTrajanje());
                    if (vrijemePocetka == null) {
                        System.out.println("Ne mogu ubaciti " + e.getNaziv() + " u dan " + kojiDan + ", postoji preklapanje!");
                    } else {
                        emisijeDana.put(vrijemePocetka, e);
                        d.setEmisije(emisijeDana);
                        daniRasporeda.put(kojiDan, d);
                    }
                }
            }
        }
        raspored.setDani(daniRasporeda);
        return raspored;
    }
    
}
