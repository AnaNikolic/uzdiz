/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import anikolic_zadaca_2.PrikazEmisije;
import composite.KomponentaRasporeda;
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
public class AlgoritamDodavanjaEmisija3 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public RasporedPrograma gradiRaspored(Map<Emisija, List<PrikazEmisije>> podaciEmisija, RasporedPrograma raspored) {
        for (Map.Entry emisijaRasporeda : podaciEmisija.entrySet()) {
            List<PrikazEmisije> prikaziEmisije = (List<PrikazEmisije>) emisijaRasporeda.getValue();
            for (PrikazEmisije prikaz : prikaziEmisije) {
                if (prikaz.getDan() == 0 || prikaz.getDan() == null) {
                    Emisija e = (Emisija) emisijaRasporeda.getKey();
                    for (KomponentaRasporeda danKomponenta : raspored.getChildren()) {
                        LocalTime vrijemePocetka = null;
                        RasporedDana dan = (RasporedDana) danKomponenta;
                        RasporedDana r = (RasporedDana) danKomponenta;
                        vrijemePocetka = nadiVrijemeZaEmisiju(raspored.getPocetak(),
                                raspored.getKraj(), dan, e.getTrajanje());
                        if (vrijemePocetka != null) {
                            System.out.println("Dan: " + dan.getDanTjedna() + " Emisija " + e.getId() 
                                    + " Našla: " + vrijemePocetka + " " + vrijemePocetka.plusMinutes(e.getTrajanje()));
                            RasporedElement elementRasporeda = new RasporedElement(dan);
                            elementRasporeda.setEmisija(e);
                            elementRasporeda.setPocetak(vrijemePocetka);
                            dan.add(elementRasporeda);
                            dan.sortirajPoVremenu();
                            raspored.zamijeni(r, dan);
                            break;
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
