package singleton;

import prototype.Emisija;
import anikolic_zadaca_2.Osoba;
import anikolic_zadaca_2.OsobaUloga;
import anikolic_zadaca_2.Uloga;
import anikolic_zadaca_2.Vrsta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ana
 */
public class UcitavanjePodataka {

    private static volatile UcitavanjePodataka INSTANCA = new UcitavanjePodataka();

    String ulogaPattern = "^[0-9]+;[, a-zA-ZÀ-ž0-9_-]+$";
    String osobaPattern = "^[0-9]+;[, a-zA-ZÀ-ž0-9_-]+$";
    String vrstePattern = "^[0-9]+;[, a-zA-ZÀ-ž0-9_-]+;[0-1];[0-9]+$";
    String emisijaPattern = "^[0-9]+;[!,: a-zA-ZÀ-ž0-9_-]+;[0-9]+;[0-9]+;([ 0-9]+-[0-9]+)*,*([ 0-9]+-[0-9]+)*$";
    String tvKucaPattern = "^[0-9]+;[,. a-zA-ZÀ-ž0-9_-]+; ?([1-2]?[1-9]:[0-5][0-9]"
            + ")?; ?([1-2]?[1-9]:[0-5][0-9])?;[a-zA-ZÀ-ž0-9_-]+.txt$";
    String emisijaProgramaPattern = "^[0-9]+; ?([1-7]-[1-7])?([1-7][,]?)*;?([0-2]?[0-9]:[0-5][0-9](:[0-5][0-9])?)?;?; ?([0-9]+-[0-9])?";

    private String putanja;
    private Map<Integer, Osoba> osobe;
    private Map<Integer, Uloga> uloge;
    private Map<Integer, Vrsta> vrste;
    private Map<Integer, Emisija> sveEmisije;
    private List<List<String>> podaciPrograma;
    
    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    private UcitavanjePodataka() {
    }

    public static UcitavanjePodataka vratiInstancu() {
        return INSTANCA;
    }

    public boolean ucitajPodatke(String datOsobe, String datUloge, String datVrste, String datEmisije, String datTvKuca) {
        osobe = ucitajMapu(datOsobe, "osobe", osobaPattern);
        uloge = ucitajMapu(datUloge, "uloge", ulogaPattern);
        vrste = ucitajMapu(datVrste, "vrste", vrstePattern);
        sveEmisije = ucitajEmisije(datEmisije);
        //  programi = ucitajPrograme(datTvKuca);
        podaciPrograma = ucitajZapisePrograme(datTvKuca);
        if (osobe != null && uloge != null && sveEmisije != null && podaciPrograma != null) {
            if (!sveEmisije.isEmpty() && !podaciPrograma.isEmpty()) {
                return true;
            }
        }
        return true;
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

    private boolean provjeriSintaksu(String parametri, String sintaksa) {
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(parametri);
        return m.matches();
    }

    public <E> Map<Integer, E> ucitajMapu(String dat, String vrstaObjekta, String sintaksa) {
        System.out.println("Ucitavam " + vrstaObjekta);
        Map<Integer, E> mapa = new HashMap<>();
        List<String> datZapis = procitajDatoteku(dat);
        if (datZapis != null) {
            for (String red : datZapis) {
                try {
                    if (!provjeriSintaksu(red, sintaksa)) {
                        System.out.println("Zapis " + vrstaObjekta + ": " + red + " ima grešku!");
                        continue;
                    }
                    String[] str = red.split(";");
                    if (mapa.containsKey(Integer.parseInt(str[0]))) {
                        System.out.println("Zapis " + vrstaObjekta + ": " + red + " već postoji!");
                        continue;
                    }
                    switch (vrstaObjekta) {
                        case "osobe":
                            mapa.put(Integer.parseInt(red.split(";")[0]),
                                    (E) new Osoba(Integer.parseInt(str[0]), str[1].trim()));
                            break;
                        case "uloge":
                            mapa.put(Integer.parseInt(red.split(";")[0]),
                                    (E) new Uloga(Integer.parseInt(str[0]), str[1].trim()));
                            break;
                        case "vrste":
                            mapa.put(Integer.parseInt(red.split(";")[0]),
                                    (E) new Vrsta(Integer.parseInt(str[0]), str[1].trim(), Integer.parseInt(str[3])));
                    }
                } catch (Exception ex) {
                    System.out.println("Greška! u zapisu: " + red);
                }
            }
        }
        System.out.println("Lista" + vrstaObjekta + " učitana!\n...........................");
        return mapa;
    }

    public Map<Integer, Emisija> ucitajEmisije(String dat) {
        System.out.println("Ucitavam emisije");
        Map<Integer, Emisija> emisije = new HashMap<>();
        List<String> datZapis = procitajDatoteku(dat);
        if (datZapis != null) {
            for (String red : datZapis) {
                try {
                    if (!provjeriSintaksu(red, emisijaPattern)) {
                        System.out.println("Zapis emisije: " + red + " ima grešku!");
                        continue;
                    }
                    String[] str = red.split(";");
                    if (emisije.containsKey(Integer.parseInt(str[0]))) {
                        System.out.println("Zapis emisije: " + red + " već postoji!");
                        continue;
                    }
                    List<OsobaUloga> osobeUloge = new ArrayList<>();
                    if (str.length > 4) {
                        osobeUloge = dodajUlogeOsobaEmisije(str[4], osobeUloge);

                    }
                    Vrsta vrsta = vrste.get(Integer.parseInt(str[2]));
                    if (vrsta != null) {
                        emisije.put(Integer.parseInt(red.split(";")[0]),
                                new Emisija(Integer.parseInt(str[0]), str[1].trim(), vrsta,
                                        Integer.parseInt(str[3]), osobeUloge));
                    }
                } catch (Exception ex) {
                    System.out.println("Greška! u zapisu: " + red);
                }
            }
        }
        System.out.println("Lista emisija učitana!\n..............................");
        return emisije;
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

    private String vratiPutanju(String punaPutanja) {
        String putanjaVrati = "";
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] punaPutanjaAtributi = punaPutanja.split(pattern);
        for (int i = 0; i < punaPutanjaAtributi.length - 1; i++) {
            putanjaVrati += punaPutanjaAtributi[i] + File.separator;
        }
        return putanjaVrati;
    }

    public List<List<String>> getProgrami() {
        return podaciPrograma;
    }

    private List<List<String>> ucitajZapisePrograme(String dat) {
        System.out.println("Ucitavam programe: ");
        List<List<String>> programiLista = new ArrayList<>();
        String putanja = vratiPutanju(dat);
        List<String> programiZapis = procitajDatoteku(dat);
        if (programiZapis != null && !programiZapis.isEmpty()) {
            for (String zapisTvKuce : programiZapis) {
                List<String> podaciJednogPrograma = new ArrayList<>();
                if (!provjeriSintaksu(zapisTvKuce, tvKucaPattern)) {
                    System.out.println("Zapis programa: " + zapisTvKuce + " ima grešku!");
                    continue;
                }
                String[] z = zapisTvKuce.split(";");
                String putanjaPrograma = putanja + z[4];
                
                podaciJednogPrograma.add(zapisTvKuce);
                podaciJednogPrograma.addAll(procitajDatoteku(putanjaPrograma));
                programiLista.add(podaciJednogPrograma);
            }
            
        }
        return programiLista;
    }

    public Map<Integer, Osoba> getOsobe() {
        return osobe;
    }

    public Map<Integer, Uloga> getUloge() {
        return uloge;
    }

    public Map<Integer, Vrsta> getVrste() {
        return vrste;
    }

    public Map<Integer, Emisija> getSveEmisije() {
        return sveEmisije;
    }

    public List<List<String>> getPodaciPrograma() {
        return podaciPrograma;
    }

    }
