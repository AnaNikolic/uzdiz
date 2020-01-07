/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import composite.RasporedDana;
import composite.RasporedElement;
import prototype.Emisija;
import composite.KomponentaRasporeda;
import java.time.LocalTime;

/**
 *
 * @author Ana
 */
public abstract class DodavanjeEmisija {

    protected boolean provjeriVremenskaPreklapanja(LocalTime pocetakPrograma,
            LocalTime krajPrograma, LocalTime pocetakEmisije,
            Integer trajanjeMinute, RasporedDana dan) {
        LocalTime krajEmisije = pocetakEmisije.plusMinutes(trajanjeMinute);
        if (pocetakEmisije.compareTo(pocetakPrograma) < 0
                || krajEmisije.compareTo(krajPrograma) > 0) {
            return true;
        }
        for (KomponentaRasporeda k : dan.getChildren()) {
            RasporedElement element = (RasporedElement) k;
            Emisija e = element.getEmisija();
            LocalTime pocetak = element.getPocetak();
            Integer trajanje = e.getTrajanje();
            LocalTime kraj = pocetak.plusMinutes(trajanje);
            if (provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetak, kraj)) {
                return true;
            }
        }
        if (pocetakEmisije.plusMinutes(trajanjeMinute).compareTo(pocetakPrograma) <= 0) {
            return true;
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
            LocalTime krajPrograma, RasporedDana sveEmisijeDana, Integer trajanjeMinute) {
        LocalTime vrati = null;
        if (sveEmisijeDana.getChildren().isEmpty()) {
            vrati = pocetakPrograma;
        } else {
            LocalTime pocetakEmisije = pocetakPrograma;
            int index = 0;
            for (KomponentaRasporeda komponenta : sveEmisijeDana.getChildren()) {
                LocalTime krajEmisije = pocetakEmisije.plusMinutes(trajanjeMinute);
                RasporedElement prikaz = (RasporedElement) komponenta;
                Emisija trenutnaEmisija = prikaz.getEmisija();
                LocalTime pocetakTrenutne = prikaz.getPocetak();
                LocalTime krajTrenutne = pocetakTrenutne.plusMinutes(trenutnaEmisija.getTrajanje());
                if (!provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetakTrenutne, krajTrenutne)) {
                    KomponentaRasporeda iduciZapis = sveEmisijeDana.getChildren().get(index + 1);
                    RasporedElement iduci = (RasporedElement) iduciZapis;
                    Emisija iducaEmisija = iduci.getEmisija();
                    LocalTime pocetakIduce = iduci.getPocetak();
                    LocalTime krajIduce = pocetakIduce.plusMinutes(iducaEmisija.getTrajanje());
                      if (!provjeriPreklapanje(pocetakEmisije, krajEmisije, pocetakIduce, krajIduce)) {
                        vrati = pocetakEmisije;
                        break;
                    }
                }
                pocetakEmisije = krajTrenutne;
                if (pocetakEmisije.plusMinutes(trajanjeMinute).compareTo(pocetakPrograma) <= 0
                        || pocetakEmisije.plusMinutes(trajanjeMinute).compareTo(krajPrograma) > 0) {
                    System.out.println("Prelazi ponoc");
                    vrati = null;
                    break;
                }
                index++;
            }
        }
        return vrati;
    }
}
