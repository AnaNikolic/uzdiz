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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class AlgoritamDodavanjaEmisija1 extends DodavanjeEmisija implements DodavanjeEmisijaSucelje {

    @Override
    public RasporedPrograma gradiRaspored(Map<Emisija, List<PrikazEmisije>> podaciEmisija, RasporedPrograma raspored) {
        for (Map.Entry emisijaRasporeda : podaciEmisija.entrySet()) {
            if (emisijaRasporeda.getValue() != null) {
                List<PrikazEmisije> prikaziEmisije = (List<PrikazEmisije>) emisijaRasporeda.getValue();
                for (PrikazEmisije prikaz : prikaziEmisije) {
                    if (prikaz.getPocetak() != null) {
                        Emisija e = (Emisija) emisijaRasporeda.getKey();
                        RasporedDana dan = (RasporedDana) raspored.get(prikaz.getDan() - 1);
                        if (!provjeriVremenskaPreklapanja(raspored.getPocetak(),
                                raspored.getKraj(), prikaz.getPocetak(), e.getTrajanje(), dan)) {
                            RasporedElement elementRasporeda = new RasporedElement(dan);
                            elementRasporeda.setEmisija(e);
                            elementRasporeda.setPocetak(prikaz.getPocetak());
                            dan.add(elementRasporeda);
                            dan.sortirajPoVremenu();
                            raspored.zamijeni(raspored.get(prikaz.getDan() - 1), dan);
                        } else {
                            System.out.println("Emisija " + e.getNaziv() + " se vremenski preklapa!");
                        }
                    }
                }
            }
        }
        return raspored;
    }
}
