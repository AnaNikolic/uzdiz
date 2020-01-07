/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 *
 * @author Ana
 */
public class TvKuca {

    private static volatile TvKuca INSTANCA = new TvKuca();

    private String putanja;
    private Map<Integer, String> osobe;
    private Map<Integer, String> uloge;
    private List<Program> programi;
    private List<Emisija> sveEmisije;
    private List<Raspored> rasporedTvKuce;
    private DirektorGraditeljaRasporeda direktorGraditeljaRasporeda;

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    private TvKuca() {
        rasporedTvKuce = new ArrayList<>();
    }

    public static TvKuca vratiInstancu() {
        return INSTANCA;
    }

    boolean ucitajPodatke(String[] args) {
        osobe = ucitajMapu(args[5], "osobe");
        uloge = ucitajMapu(args[7], "uloge");
        sveEmisije = ucitajEmisije(args[3]);
        programi = ucitajPrograme(args[1]);
        if (osobe != null && uloge != null && sveEmisije != null && programi != null) {
            if (!sveEmisije.isEmpty() && !programi.isEmpty()) return true;
        }
        return false;
    }

    public void kreirajRasporedTvKuce() {
        if (direktorGraditeljaRasporeda == null) {
            direktorGraditeljaRasporeda = new DirektorGraditeljaRasporeda();
        }
        rasporedTvKuce = direktorGraditeljaRasporeda.izgradiRaspored(programi);
    }

    void ponudiIspis() {
        Scanner unos = new Scanner(System.in);
        System.out.println("Odaberi ispis: \n 1 Raspored za dan \n 2 Ispis podataka i statistika programa");
        int odabir = unos.nextInt();
        switch (odabir) {
            case 1:
                System.out.println("Redni broj dana u tjednu?");
                odabir = unos.nextInt();
                Raspored raspored = null;
                for (Raspored r : rasporedTvKuce) {
                    if (r.getId() == odabir) {
                        raspored = r;
                        break;
                    }
                }
                if (raspored == null) {
                    System.out.println("Pogresan unos!");
                } else {
                    System.out.println("Unesi id programa za više informacija");
                    odabir = unos.nextInt();
                    if (odabir >= 1 && odabir <= 7) {
                        ispisiDan(odabir, raspored);
                    } else {
                        System.out.println("Pogresa unos!");
                    }
                }
                break;
            case 2:
                for (Raspored r : rasporedTvKuce) {
                    System.out.println("................\n program: " + r.getId());
                    ispisiStatistiku(r);
                }
                break;
            default:
                System.out.println("Pogresan unos");
        }
    }

    private void ispisiDan(int num, Raspored raspored) {
        Dan dan = raspored.getDani().get(num);
        System.out.println("-------------------------------");
        TreeMap<LocalTime, Emisija> emisijeDana = (TreeMap<LocalTime, Emisija>) dan.getEmisije(); //sortirat??
        for (Map.Entry<LocalTime, Emisija> zapisEmisije : emisijeDana.entrySet()) {
            Emisija e = zapisEmisije.getValue();
            LocalTime pocetakEmisije = zapisEmisije.getKey();
            System.out.println("ID:" + e.getId() + " " + e.getNaziv());
            System.out.println(pocetakEmisije + " - " + pocetakEmisije.plusMinutes(e.getTrajanje()));
            System.out.println("..............");
        }
    }

    private void ispisiStatistiku(Raspored raspored) {
        Map<Integer, Dan> daniTjedna = raspored.getDani();
        for (int brojDana = 1; brojDana <= 7; brojDana++) {
            System.out.println("-------------------------------");
            System.out.println("Dan: " + brojDana);
            Dan dan = daniTjedna.get(brojDana);
            TreeMap<LocalTime, Emisija> emisijeDana = (TreeMap<LocalTime, Emisija>) dan.getEmisije();
            ispisiPopunjenostDana(dan, raspored.getPocetakPrograma(), raspored.getKrajPrograma());
        }
    }

    private void ispisiPopunjenostDana(Dan dan, LocalTime pocetakPrograma, LocalTime krajPrograma) {
        Integer popunjeneMinute = 0;
        Integer brojEmisija = 0;
        Integer trajanjePrograma = (krajPrograma.getHour() * 60 + krajPrograma.getMinute())
                - (pocetakPrograma.getHour() * 60 + pocetakPrograma.getMinute());
        for (Map.Entry<LocalTime, Emisija> zapisEmisije : dan.getEmisije().entrySet()) {
            popunjeneMinute += zapisEmisije.getValue().getTrajanje();
            brojEmisija++;
        }
        Double popunjenost = popunjeneMinute / (double) trajanjePrograma * 100;
        System.out.println("Popunjenost programa je " + String.format("%.2f", popunjenost) + "%.");
        System.out.println("Broj emitiranih emisija je " + brojEmisija + ".");
        int sati = (int) Math.floor(trajanjePrograma / (float) 60);
        int min = trajanjePrograma - sati * 60;
        System.out.println("Emitiranje signala je trajalo " + sati + "h:" + min + "min.");
        sati = (int) Math.floor((trajanjePrograma - popunjeneMinute) / (float) 60);
        System.out.println("Slobodno vrijeme u emitiranju " + sati + "h.");
    }

    private List<String> procitajDatoteku(String putanjaDoDatoteke) {
        List<String> redci = new ArrayList<>();
        boolean prvaLinija = true;
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDoDatoteke))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                if (prvaLinija == true) {
                    prvaLinija = false;
                    continue;
                }
                System.out.println(linija);
                redci.add(linija);
            }
        } catch (IOException ex) {
            System.out.println("Datoteka " + putanjaDoDatoteke + " nije pronađena!");
            return null;
        }
        return redci;
    }

    public Map<Integer, String> ucitajMapu(String dat, String obj) {
        System.out.println("Ucitavam " + obj);
        Map<Integer, String> mapa = new HashMap<>();
        List<String> osobeZapis = procitajDatoteku(dat);
        if (osobeZapis != null) {
            for (String red : osobeZapis) {
                try {
                    String[] elementi = red.split(";");
                    if (mapa.containsKey(Integer.parseInt(elementi[0]))) {
                        System.out.println("Greška, zapis " + obj + "sa id " + elementi[0] + " već postoji!");
                    } else {
                        mapa.put(Integer.parseInt(elementi[0]), elementi[1]);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Greška! u zapisu: " + red);
                }
            }
        }
        System.out.println(obj + " učitano!");
        System.out.println("....................................................");
        return mapa;
    }

    private List<Emisija> ucitajEmisije(String dat) {
        System.out.println("Učitavam sve emisije:");
        List<Emisija> emisije = new ArrayList<>();
        List<String> emisijeZapis = procitajDatoteku(dat);
        if (emisijeZapis != null) {
            for (String red : emisijeZapis) {
                try {
                    String[] elementi = red.split(";");
                    int id = Integer.parseInt(elementi[0]);
                    if (!ProvjeriPostojiLiEmisija(emisije, id)) {
                        Map<Integer, List<Integer>> osobaUloga = new HashMap<>();
                        if (elementi.length > 3) {

                            osobaUloga = dodajUlogeOsobaEmisije(elementi[3].replace(" ", ""), osobaUloga);
                        }
                        emisije.add(new Emisija(id, elementi[1],
                                Integer.parseInt(elementi[2].replace(" ", "")), osobaUloga));
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Greška! u zapisu: " + red);
                }
            }
        }
        System.out.println("....................................................");
        return emisije;
    }

    private Map<Integer, List<Integer>> dodajUlogeOsobaEmisije(String ulogeOsobaZapis,
            Map<Integer, List<Integer>> osobaUloga) {
        String[] sveOsobeUloge = ulogeOsobaZapis.split(",");
        for (String ou : sveOsobeUloge) {
            List<Integer> popisUloga = new ArrayList<>();
            String[] u = ou.split("-");
            Integer osoba = (Integer.parseInt(u[0]));
            Integer uloga = (Integer.parseInt(u[1]));
            if (uloge.containsKey(osoba) && osobe.containsKey(uloga)) {
                if (osobaUloga.containsKey(Integer.parseInt(u[0]))) {
                    popisUloga = osobaUloga.get(osoba);
                    if (popisUloga.contains(Integer.parseInt(u[0]))) {
                        System.out.println("Osoba već ima istu ulogu u emisiji!");
                    } else {
                        popisUloga.add(Integer.parseInt(u[1]));
                        osobaUloga.replace(Integer.parseInt(u[0]), popisUloga);
                    }
                } else {
                    popisUloga.add(Integer.parseInt(u[1]));
                    osobaUloga.put(Integer.parseInt(u[0]), popisUloga);
                }
            }
        }
        return osobaUloga;
    }

    private Boolean ProvjeriPostojiLiEmisija(List<Emisija> emisije, int id) {
        Boolean postoji = false;
        for (Emisija e : emisije) {
            if (e.getId() == id) {
                postoji = true;
                break;
            }
        }
        return postoji;
    }

    private Boolean PostojiLiProgram(List<Program> programi, Integer id) {
        Boolean postoji = false;
        for (Program p : programi) {
            if (p.getId() == id) {
                postoji = true;
                break;
            }
        }
        return postoji;
    }

    private String vratiPutanju(String punaPutanja) {
        String putanjaVrati = "";
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] punaPutanjaAtributi = punaPutanja.split(pattern);
        for (int i = 0; i < punaPutanjaAtributi.length - 1; i++) {
            putanjaVrati += punaPutanjaAtributi[i] + File.separator;
        }
        return putanjaVrati;
    }

    private List<Program> ucitajPrograme(String dat) {
        System.out.println("Ucitavam programe: ");
        List<Program> programiLista = new ArrayList<>();
        String putanja = vratiPutanju(dat);
        List<String> programiZapis = procitajDatoteku(dat);
        if (programiZapis != null) {
            for (String red : programiZapis) {
                try {
                    String[] elementi = red.split(";");
                    Program p = new Program();
                    p.setId(Integer.parseInt(elementi[0].replace(" ", "")));
                    if (!PostojiLiProgram(programiLista, p.getId())) {
                        p.setNaziv(elementi[1]);
                        LocalTime vrijemePocetkaPrograma = dajVrijeme(elementi[2]);
                        if (vrijemePocetkaPrograma == null) {
                            vrijemePocetkaPrograma = LocalTime.MIN;
                        }
                        p.setPocetakPrograma(vrijemePocetkaPrograma);
                        LocalTime vrijemeKrajaPrograma = dajVrijeme(elementi[3]);
                        if (vrijemeKrajaPrograma == null) {
                            vrijemeKrajaPrograma = LocalTime.MAX;
                        }
                        p.setKrajPrograma(vrijemeKrajaPrograma);
                        p = ucitajEmisijePrograma(putanja + elementi[4], p);
                        programiLista.add(p);
                    }
                } catch (Exception ex) {
                    System.out.println("Greška u zapisu: " + red);
                }
            }
        }
        System.out.println("...................................................");
        return programiLista;
    }

    private LocalTime dajVrijeme(String vrijeme) {
        if (vrijeme.equals("")) {
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

    private Program ucitajEmisijePrograma(String putanja, Program p) {
        Map<Integer, PrikazEmisije> prikazi = p.getPrikaz();
        List<String> redovi = procitajDatoteku(putanja);
        List<Emisija> emisijePrograma = new ArrayList<>();
        for (String red : redovi) {
            String[] r = red.split(";");
            Integer idEmisija = Integer.parseInt(r[0]);
            if (ProvjeriPostojiLiEmisija(sveEmisije, idEmisija)) {
                for (Emisija e : sveEmisije) {
                    if (e.getId() == idEmisija) {
                        Emisija emisija = (Emisija) e.kloniraj();
                        if (r.length > 1) {
                            PrikazEmisije prikazEmisije = new PrikazEmisije();
                            LocalTime vrijemePrikaza = null;
                            if (r.length > 2) {
                                vrijemePrikaza = dajVrijeme(r[2]);
                            }
                            prikazEmisije = dodajPrikazeEmisije(r[1], vrijemePrikaza);
                            prikazi.put(idEmisija, prikazEmisije);
                        }
                        if (r.length > 3) {
                            Map<Integer, List<Integer>> osobaUloga = emisija.getOsobaUloga();
                            Map<Integer, List<Integer>> osobaUlogaNova = 
                                    dodajUlogeOsobaEmisije(r[3].replace(" ", ""), osobaUloga);
                            emisija.setOsobaUloga(osobaUlogaNova);

                        }
                        emisijePrograma.add(emisija);
                        break;
                    }
                }
            }
        }
        p.setEmisijePrograma(emisijePrograma);
        p.setPrikaz(prikazi);
        return p;
    }

    private PrikazEmisije dodajPrikazeEmisije(String zapisPrikazi,
            LocalTime vrijemePrikaza) throws NumberFormatException {
        PrikazEmisije prikazEmisije = new PrikazEmisije();
        List<Integer> daniLista = new ArrayList<>();
        if (zapisPrikazi.contains("-")) {
            String[] dani = zapisPrikazi.split("-");
            Integer pocetak = Integer.parseInt(dani[0].replace(" ", ""));
            Integer kraj = Integer.parseInt(dani[1]);
            for (; pocetak <= kraj; pocetak++) {
                daniLista.add(pocetak);
            }
        } else if (zapisPrikazi.contains(",")) {
            String[] dani = zapisPrikazi.split(",");
            for (String dan : dani) {
                daniLista.add(Integer.parseInt(dan.replace(" ", "")));
            }
        }
        prikazEmisije.setPocetak(vrijemePrikaza);
        prikazEmisije.setDanTjedna(daniLista);
        return prikazEmisije;
    }

}
