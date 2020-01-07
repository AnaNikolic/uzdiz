/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import anikolic_zadaca_2.PrikazEmisije;
import composite.RasporedDana;
import composite.RasporedElement;
import prototype.Emisija;
import composite.RasporedPrograma;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class AlgoritamDodavanjaEmisija2 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public RasporedPrograma gradiRaspored(Map<Emisija, List<PrikazEmisije>> podaciEmisija, RasporedPrograma raspored) {
        for (Map.Entry emisijaRasporeda : podaciEmisija.entrySet()) {
            if (emisijaRasporeda.getValue() != null) {
                List<PrikazEmisije> prikaziEmisije = (List<PrikazEmisije>) emisijaRasporeda.getValue();
                for (PrikazEmisije prikaz : prikaziEmisije) {
                    if (prikaz.getPocetak() == null && prikaz.getDan() > 0) {
                        Emisija e = (Emisija) emisijaRasporeda.getKey();
                        System.out.println("Trazim: " + e.getId());
                        RasporedDana dan = (RasporedDana) raspored.get(prikaz.getDan() - 1);
                        RasporedDana r = (RasporedDana) raspored.get(prikaz.getDan() - 1);
                        LocalTime vrijemePocetka = nadiVrijemeZaEmisiju(raspored.getPocetak(),
                                raspored.getKraj(), dan, e.getTrajanje());
                        if (vrijemePocetka != null) {
                            RasporedElement elementRasporeda = new RasporedElement(dan);
                            elementRasporeda.setEmisija(e);
                            elementRasporeda.setPocetak(vrijemePocetka);
                            dan.add(elementRasporeda);
                            dan.sortirajPoVremenu();
                            raspored.zamijeni(r, dan);
                            System.out.println("DAN " + prikaz.getDan() + "Emisijja " + e.getId()
                                    + " Našla: " + vrijemePocetka + " - " + vrijemePocetka.plusMinutes(e.getTrajanje()));
                        } else {
                            System.out.println("Ne mogu pronaci vrijeme za " + e.getNaziv() + "!");
                        }
                    }
                }
            }
        }
        return raspored;
    }
}
