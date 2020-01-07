/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public abstract class DodavanjeEmisija {
    
    protected Emisija dajEmisiju(Integer id, List<Emisija> emisije) {
        for (Emisija e : emisije) {
            if (e.getId() == id) {
                return (Emisija) e.kloniraj();
            }
        }
        return null;
    }

    protected boolean provjeriVremenskaPreklapanja(LocalTime pocetakPrograma,
            LocalTime krajPrograma, LocalTime pocetakEmisije,
            Integer trajanjeMinute, Map<LocalTime, Emisija> sveEmisijeDana) {
        LocalTime krajEmisije = pocetakEmisije.plusMinutes(trajanjeMinute);
        if (pocetakEmisije.compareTo(pocetakPrograma) < 0
                || krajEmisije.compareTo(krajPrograma) > 0) {
            return true;
        }

        for (Map.Entry prikazJedne : sveEmisijeDana.entrySet()) {
            Emisija e = (Emisija) prikazJedne.getValue();
            LocalTime pocetak = (LocalTime) prikazJedne.getKey();
            Integer trajanje = e.getTrajanje();
            LocalTime kraj = pocetak.plusMinutes(trajanje);
            if (provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetak, kraj)) {
                return true;
            }
        }
        return false;
    }

    protected boolean provjeriPreklapanje(LocalTime pocetakEmisije, LocalTime krajEmisije,
            LocalTime pocetak, LocalTime kraj) {

        if (pocetakEmisije.compareTo(pocetak) >= 0 && pocetakEmisije.compareTo(kraj) < 0) {
            return true;
        }
        if (krajEmisije.compareTo(pocetak) > 0 && krajEmisije.compareTo(kraj) < 0) {
            return true;
        }
        return false;
    }

    protected LocalTime nadiVrijemeZaEmisiju(LocalTime pocetakPrograma,
            LocalTime krajPrograma, Map<LocalTime, Emisija> sveEmisijeDana, Integer trajanjeMinute) {
        if (sveEmisijeDana.isEmpty()) {
            return pocetakPrograma;
        }
        LocalTime vrati = null;
        LocalTime pocetakEmisije = pocetakPrograma;
        for (Map.Entry prikazJedne : sveEmisijeDana.entrySet()) {
            LocalTime krajEmisije = pocetakEmisije.plusMinutes(trajanjeMinute);
            Emisija trenutnaEmisija = (Emisija) prikazJedne.getValue();
            LocalTime pocetakTrenutne = (LocalTime) prikazJedne.getKey();
            LocalTime krajTrenutne = pocetakTrenutne.plusMinutes(trenutnaEmisija.getTrajanje());
            if (!provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetakTrenutne, krajTrenutne)) {
                Map.Entry iduciZapis = sveEmisijeDana.entrySet().iterator().next();
                Emisija iducaEmisija = (Emisija) iduciZapis.getValue();
                LocalTime pocetakIduce = (LocalTime) iduciZapis.getKey();
                LocalTime krajIduce = pocetakIduce.plusMinutes(iducaEmisija.getTrajanje());
                if (!provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetakIduce, krajIduce)) {
                    vrati = pocetakEmisije;
                }
            }
            pocetakEmisije = krajTrenutne;
        }
        return vrati;
    }

    
}
