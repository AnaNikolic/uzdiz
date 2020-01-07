/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_2;

import builder.DirektorGraditeljaRasporeda;
import composite.RasporedDana;
import composite.RasporedElement;
import composite.RasporedTvKuce;
import decorator.Decorator;
import decorator.DecoratorDan;
import decorator.DecoratorPoVrsti;
import iterator.Iterator;
import iterator.KolekcijaRaspored;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import singleton.UcitavanjePodataka;

/**
 *
 * @author Ana
 */
public class Anikolic_zadaca_2 {

    static String datOsobe;
    static String datUloge;
    static String datVrste;
    static String datEmisije;
    static String datTvKuca;
    private static List<List<String>> podaciPrograma;
    private static Map<Integer, Uloga> uloge;
    static RasporedTvKuce tvKuca;
    static DirektorGraditeljaRasporeda direktorGraditeljaRasporeda;

    public static void main(String[] args) {
        if (!provjeriArgumente(args)) {
            System.out.println("Pogrešno uneseni argumenti! Završavam program.");
            return;
        }
        UcitavanjePodataka ucitavanjePodataka = UcitavanjePodataka.vratiInstancu();
        if (ucitavanjePodataka.ucitajPodatke(datOsobe, datUloge, datVrste, datEmisije, datTvKuca)) {
        } else {
            System.out.println("greska");
            return;
        }
        podaciPrograma = ucitavanjePodataka.getProgrami();
        uloge = ucitavanjePodataka.getUloge();
        if (direktorGraditeljaRasporeda == null) {
            direktorGraditeljaRasporeda = new DirektorGraditeljaRasporeda(ucitavanjePodataka.getOsobe(),
                    ucitavanjePodataka.getUloge(), ucitavanjePodataka.getVrste(), ucitavanjePodataka.getSveEmisije(),
                    ucitavanjePodataka.getPodaciPrograma());
        }
        tvKuca = direktorGraditeljaRasporeda.izgradiRaspored();
        ponudiIzbornik(tvKuca);

    }

    private static boolean provjeriArgumente(String[] args) {
        if (args.length == 10) {
            for (int i = 0; i < 5; i++) {
                if (!args[2 * i + 1].endsWith(".txt")) {
                    return false;
                }
                switch (args[2 * i]) {
                    case "-t":
                        datTvKuca = args[2 * i + 1];
                        break;
                    case "-e":
                        datEmisije = args[2 * i + 1];
                        break;
                    case "-v":
                        datVrste = args[2 * i + 1];
                        break;
                    case "-o":
                        datOsobe = args[2 * i + 1];
                        break;
                    case "-u":
                        datUloge = args[2 * i + 1];
                        break;
                    default:
                        return false;
                }
            }
            if (datTvKuca != null && datEmisije != null && datVrste != null
                    && datOsobe != null && datUloge != null) {
                return true;
            }
        }
        return false;
    }

    private static void ponudiIzbornik(RasporedTvKuce tvKuca) {
        Scanner unos = new Scanner(System.in);
        try {
            String nastaviti = "da";
            do {
                System.out.println("Odaberi ispis: "
                        + "\n 1 - Ispis vremenskog plana "
                        + "\n 2 - Ispis potencijalnih prihoda od reklama (u min)"
                        + "\n 3 - Ispis tjednog plana za vrstu emisije"
                        + "\n 4 - Zamjena uloge osobe u emisijama");
                int izbor = unos.nextInt();
                switch (izbor) {
                    case 1:
                        opcija1(tvKuca);
                        break;
                    case 2:
                        opcija2(tvKuca);
                        break;
                    case 3:
                        opcija3(tvKuca);
                        break;
                    case 4:
                        opcija4(tvKuca);
                        break;
                    default:
                        System.out.println("Neispravan odabir!");
                }
                System.out.println("Nastaviti dalje? (DA/NE)");
                nastaviti = unos.next();
            } while ("da".equals(nastaviti));
        } catch (Exception e) {
            System.out.println("Greška odabira!");
        }
    }

    private static void opcija4(RasporedTvKuce tvKuca1) {
        Scanner unos = new Scanner(System.in);
        System.out.println("4 - Zamjena uloge osobe u emisijama");
        System.out.println("Id osobe:");
        int osoba = unos.nextInt();
        System.out.println("Id stare uloge:");
        int staraUloga = unos.nextInt();
        System.out.println("Id nove uloge:");
        int novaUloga = unos.nextInt();
        if (uloge.containsKey(novaUloga)) {
            tvKuca1.notifySubscriber(osoba, staraUloga, uloge.get(novaUloga));
            System.out.println("Zamijenjen!");
        } else {
            System.out.println("Ne postoji nova uloga!");
        }
    }

    private static void opcija3(RasporedTvKuce tvKuca1) {
        Scanner unos = new Scanner(System.in);
        System.out.println("3 - Ispis tjednog plana za vrstu emisije");
        System.out.println("Vrsta emisije: ");
        int vrsta = unos.nextInt();
        DecoratorPoVrsti poVrsti = new DecoratorPoVrsti(tvKuca);
        poVrsti.setVrsta(vrsta);
        poVrsti.dekorirajIspis();
    }

    private static void opcija2(RasporedTvKuce tvKuca1) {
        Scanner unos = new Scanner(System.in);
        System.out.println("2 - Ispis potencijalnih prihoda od reklama (u min):");
        System.out.println("Redni broj programa:");
        int program2 = unos.nextInt();
        System.out.println("Redni broj dana:");
        int dan2 = unos.nextInt();
        KolekcijaRaspored kolekcija = new KolekcijaRaspored(tvKuca1);
        Iterator iteratorSvi = kolekcija.stvoriIteratorSvi();
        int minute = 0;
        while (iteratorSvi.hasNext()) {
            RasporedElement trenutni = iteratorSvi.next();
            if (trenutni.getParent().getDanTjedna() == dan2
                    && trenutni.getParent().getParent().getId() == program2) {
                minute += trenutni.getEmisija().getVrsta().getTrajanjeReklama();
            }
        }
        System.out.println("Ukupno trajanje reklama za program: "
                + program2 + ", dan: " + dan2 + " je " + minute + " min.");
    }

    private static void opcija1(RasporedTvKuce tvKuca1) {
        Scanner unos = new Scanner(System.in);
        System.out.println("1 - Ispis vremenskog plana:");
        System.out.println("Redni broj programa:");
        int program = unos.nextInt();
        System.out.println("Redni broj dana:");
        int dan = unos.nextInt();
        System.out.println("Raspored za program " + program + " dan " + dan + " :");
        //   tvKuca1.ispisiVremenskiPlan(program, dan);
        KolekcijaRaspored kolekcija = new KolekcijaRaspored(tvKuca1);
        Iterator iteratorSvi = kolekcija.stvoriIteratorSvi();
        while (iteratorSvi.hasNext()) {
            RasporedElement trenutni = iteratorSvi.next();
            if (trenutni.getParent().getDanTjedna() == dan
                    && trenutni.getParent().getParent().getId() == program) {
                RasporedDana danRaspored = trenutni.getParent();
                DecoratorDan decDan = new DecoratorDan(danRaspored);
                decDan.dekorirajIspis();
                break;
            }
        }
    }

}
