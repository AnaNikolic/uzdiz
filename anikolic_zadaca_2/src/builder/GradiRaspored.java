/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import anikolic_zadaca_2.PrikazEmisije;
import bridge.DodavanjeEmisijaSucelje;
import prototype.Emisija;
import anikolic_zadaca_2.Osoba;
import anikolic_zadaca_2.OsobaUloga;
import composite.RasporedPrograma;
import composite.RasporedTvKuce;
import anikolic_zadaca_2.Uloga;
import anikolic_zadaca_2.Vrsta;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ana
 */
public class GradiRaspored implements GraditeljRasporeda {

    private Map<Integer, Osoba> osobe;
    private Map<Integer, Uloga> uloge;
    private Map<Integer, Vrsta> vrste;
    private Map<Integer, Emisija> sveEmisije;
    private List<List<String>> podaciPrograma;

    List<DodavanjeEmisijaSucelje> listaAlgoritama = new ArrayList<>();

    String emisijaProgramaPattern = "^[0-9]+; ?([1-7]-[1-7])?([1-7][,]?)*;?([0-2]?[0-9]:[0-5][0-9](:[0-5][0-9])?)?;?; ?([0-9]+-[0-9])?";

    @Override
    public GraditeljRasporeda dodajPodatkeTvKuce(Map<Integer, Osoba> osobe, Map<Integer, Uloga> uloge,
            Map<Integer, Vrsta> vrste, Map<Integer, Emisija> emisije) {
        this.osobe = osobe;
        this.uloge = uloge;
        this.vrste = vrste;
        this.sveEmisije = emisije;
        return this;
    }

    @Override
    public GraditeljRasporeda postaviPodatkePrograma(List<List<String>> podaciPrograma) {
        this.podaciPrograma = podaciPrograma;
        return this;
    }

    @Override
    public GraditeljRasporeda postaviAlgoritme(List<DodavanjeEmisijaSucelje> algoritmi) {
        this.listaAlgoritama = algoritmi;
        return this;
    }

    @Override
    public RasporedTvKuce build() {
        RasporedTvKuce rasporedTvKuce = new RasporedTvKuce();
        List<Integer> sviIdPrograma = new ArrayList();
        for (List<String> zapisJednogPrograma : podaciPrograma) {
            boolean prvaLinija = true;
            RasporedPrograma raspored = new RasporedPrograma(rasporedTvKuce);
            Map<Emisija, List<PrikazEmisije>> emisijePrograma = new HashMap<>();
            for (String red : zapisJednogPrograma) {
                String[] r = red.split(";");
                if (prvaLinija) {
                    prvaLinija = false;
                    if (sviIdPrograma.contains(Integer.parseInt(r[0].strip()))) {
                        System.out.println("Progran sa id: " + r[0] + " vec postoji!");
                        break;
                    }
                    sviIdPrograma.add(Integer.parseInt(r[0]));
                    raspored = dodajPodatkeProgramaRasporeda(raspored, r);
                    continue;
                }
                if (!provjeriSintaksu(red, emisijaProgramaPattern)) {
                    System.out.println("Zapis emisije: " + red + " ima grešku!");
                    continue;
                }
                if (sveEmisije.containsKey(Integer.parseInt(r[0]))) {
                    emisijePrograma = azurirajEmisijePrograma(r, emisijePrograma, raspored);
                } else {
                    System.out.println("Ne postoji emisija" + r[0]);
                }
            }
            if (sviIdPrograma.contains(raspored.getId())) {
                for (DodavanjeEmisijaSucelje algoritam : listaAlgoritama) {
                    algoritam.gradiRaspored(emisijePrograma, raspored);
                }
                rasporedTvKuce.add(raspored);
            }
        }
        return rasporedTvKuce;
    }

    private Map<Emisija, List<PrikazEmisije>> azurirajEmisijePrograma(String[] r,
            Map<Emisija, List<PrikazEmisije>> emisijePrograma,
            RasporedPrograma raspored) throws NumberFormatException {
        Emisija emisija = dajEmisijuPrograma(Integer.parseInt(r[0]), emisijePrograma);
        List<PrikazEmisije> prikaziEmisije = new ArrayList<>();
        if (emisijePrograma.containsKey(emisija)) {
            prikaziEmisije = emisijePrograma.get(emisija);
        }
        LocalTime pocetak = null;
        if (r.length > 2) {
            pocetak = dajVrijeme(r[2]);
        }
        if (r.length > 1 && !r[1].strip().equals("")) {
            prikaziEmisije.addAll(dodajPrikazeEmisije(r[1], pocetak));
        } else {
            prikaziEmisije.add(new PrikazEmisije(0, pocetak));
        }
        if (r.length > 3) {
            List<OsobaUloga> osobeUloge = emisija.getOsobaUloga();
            osobeUloge = dodajUlogeOsobaEmisije(r[3].strip(), osobeUloge);
            emisija.setOsobaUloga(osobeUloge);
        }
        System.out.println("Dodana " + emisija.getId() + " " + emisija.getNaziv() + " u " + raspored.getNaziv());
        emisijePrograma.put(emisija, prikaziEmisije);
        return emisijePrograma;
    }

    private RasporedPrograma dodajPodatkeProgramaRasporeda(RasporedPrograma raspored, String[] r) throws NumberFormatException {
        raspored.setId(Integer.parseInt(r[0].replace(" ", "")));
        raspored.setNaziv(r[1].trim());
        LocalTime vrijemePocetkaPrograma = dajVrijeme(r[2]);
        if (vrijemePocetkaPrograma == null) {
            vrijemePocetkaPrograma = LocalTime.MIN;
        }
        raspored.setPocetak(vrijemePocetkaPrograma);
        LocalTime vrijemeKrajaPrograma = dajVrijeme(r[3]);
        if (vrijemeKrajaPrograma == null) {
            vrijemeKrajaPrograma = LocalTime.MAX;
        }
        raspored.setKraj(vrijemeKrajaPrograma);
        return raspored;
    }

    private List<OsobaUloga> dodajUlogeOsobaEmisije(String ulogeOsobaZapis,
            List<OsobaUloga> osobaUloga) {
        String[] sveOsobeUloge = ulogeOsobaZapis.replace(" ", "").split(",");
        for (String osobaUlogaZapis : sveOsobeUloge) {
            String[] par = osobaUlogaZapis.split("-");
            Osoba o = osobe.get(Integer.parseInt(par[0]));
            Uloga u = uloge.get(Integer.parseInt(par[1]));
            if (o != null && u != null) {
                OsobaUloga ou = new OsobaUloga(o, u);
                boolean postoji = false;
                for (OsobaUloga dosad : osobaUloga) {
                    if (dosad == ou) {
                        postoji = true;
                        System.out.println("Osoba " + o.getImePrezime() + "već ima istu ulogu u emisiji!");
                    }
                }
                if (!postoji) {
                    osobaUloga.add(ou);
                }
            }
        }
        return osobaUloga;
    }

    private List<PrikazEmisije> dodajPrikazeEmisije(String zapisPrikazi,
            LocalTime vrijemePrikaza) throws NumberFormatException {
        List<PrikazEmisije> prikaziEmisije = new ArrayList<>();
        if (zapisPrikazi.contains("-")) {
            String[] dani = zapisPrikazi.split("-");
            Integer pocetak = Integer.parseInt(dani[0].replace(" ", ""));
            Integer kraj = Integer.parseInt(dani[1]);
            for (; pocetak <= kraj; pocetak++) {
                prikaziEmisije.add(new PrikazEmisije(pocetak, vrijemePrikaza));
            }
        } else if (zapisPrikazi.contains(",")) {
            String[] dani = zapisPrikazi.split(",");
            for (String dan : dani) {
                prikaziEmisije.add(new PrikazEmisije(Integer.parseInt(dan.strip()), vrijemePrikaza));
            }
        } else {
            Integer d = Integer.parseInt(zapisPrikazi.strip());
            prikaziEmisije.add(new PrikazEmisije(d, vrijemePrikaza));
        }
        return prikaziEmisije;
    }

    private Emisija dajEmisijuPrograma(int id, Map<Emisija, List<PrikazEmisije>> emisijePrograma) {
        for (Map.Entry e : emisijePrograma.entrySet()) {
            Emisija emisija = (Emisija) e.getKey();
            if (emisija.getId() == id) {
                return emisija;
            }
        }
        return (Emisija) sveEmisije.get(id).kloniraj();
    }

    private LocalTime dajVrijeme(String vrijeme) {
        if (vrijeme.strip().equals("")) {
            return null;
        }
        String[] vri = vrijeme.replace(" ", "").split(":");
        if (vri.length == 2) {
            return LocalTime.of(Integer.parseInt(vri[0]), (Integer.parseInt(vri[1])));
        }
        if (vri.length == 3) {
            return LocalTime.of(Integer.parseInt(vri[0]), (Integer.parseInt(vri[1])), (Integer.parseInt(vri[2])));
        }
        return null;
    }

    private boolean provjeriSintaksu(String parametri, String sintaksa) {
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(parametri);
        return m.matches();
    }
}
