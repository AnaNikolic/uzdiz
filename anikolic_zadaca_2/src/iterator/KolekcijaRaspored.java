/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import composite.KomponentaRasporeda;
import composite.RasporedDana;
import composite.RasporedElement;
import composite.RasporedPrograma;
import composite.RasporedTvKuce;
import java.util.List;

/**
 *
 * @author Ana
 */
public class KolekcijaRaspored implements Kolekcija {

    private RasporedTvKuce tvKuca;

    public KolekcijaRaspored(RasporedTvKuce tvKuca) {
        this.tvKuca = tvKuca;
    }

    @Override
    public Iterator stvoriIteratorSvi() {
        return new IteratorRasporedaSvi();

    }

    @Override
    public Iterator stvoriIteratorPoVrsti(int vrsta) {
        return new IteratorRasporedaPoVrsti(vrsta);
    }

    public class IteratorRasporedaSvi implements Iterator {

        private int trenutniProgram;
        private int trenutniDan;
        private int trenutniElement;

        public IteratorRasporedaSvi() {
            trenutniProgram = 0;
            trenutniDan = 0;
            trenutniElement = 0;
        }

        @Override
        public RasporedElement first() {
            for (KomponentaRasporeda program : tvKuca.getChildren()) {
                RasporedPrograma p = (RasporedPrograma) program;
                for (KomponentaRasporeda dan : p.getChildren()) {
                    RasporedDana d = (RasporedDana) dan;
                    if (d.get(0) != null) {
                        return (RasporedElement) d.get(0);
                    }
                }
            }
            return null;
        }

        @Override
        public RasporedElement next() {
            for (; trenutniProgram < tvKuca.getChildren().size(); trenutniProgram++) {
                RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
                for (; trenutniDan < 7; trenutniDan++) {
                    RasporedDana d = (RasporedDana) p.get(trenutniDan);
                    for (; trenutniElement < d.getChildren().size(); trenutniElement++) {
                        if (trenutniElement < d.getChildren().size()) {
                            return (RasporedElement) d.get(trenutniElement++);
                        }
                    }
                    trenutniElement = 0;
                }
                trenutniElement = 0;
                trenutniDan = 0;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            for (; trenutniProgram < tvKuca.getChildren().size(); trenutniProgram++) {
                RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
                for (; trenutniDan < 7; trenutniDan++) {
                    RasporedDana d = (RasporedDana) p.get(trenutniDan);
                    for (; trenutniElement < d.getChildren().size(); trenutniElement++) {
                        if (trenutniElement + 1 < d.getChildren().size()) {
                            return true;
                        }
                    }
                    trenutniElement = 0;
                }
                trenutniElement = 0;
                trenutniDan = 0;
            }
            return false;
        }

        @Override
        public RasporedElement currentItem() {
            RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
            RasporedDana d = (RasporedDana) p.get(trenutniDan);
            return (RasporedElement) d.get(trenutniElement);
        }

        @Override
        public void resetIterator() {
            trenutniProgram = 0;
            trenutniDan = 0;
            trenutniElement = 0;
        }

    }

    public class IteratorRasporedaPoVrsti implements Iterator {

        int idVrsta;

        private int trenutniProgram;
        private int trenutniDan;
        private int trenutniElement;

        public IteratorRasporedaPoVrsti(int vrsta) {
            idVrsta = vrsta;
        }

        @Override
        public RasporedElement first() {
            for (KomponentaRasporeda program : tvKuca.getChildren()) {
                RasporedPrograma p = (RasporedPrograma) program;
                for (KomponentaRasporeda dan : p.getChildren()) {
                    RasporedDana d = (RasporedDana) dan;
                    if (d.get(0) != null) {
                        return (RasporedElement) d.get(0);
                    }
                }
            }
            return null;
        }

        @Override
        public RasporedElement next() {
            for (; trenutniProgram < tvKuca.getChildren().size(); trenutniProgram++) {
                RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
                for (; trenutniDan < 7; trenutniDan++) {
                    RasporedDana d = (RasporedDana) p.get(trenutniDan);
                    for (; trenutniElement < d.getChildren().size(); trenutniElement++) {
                        if (trenutniElement < d.getChildren().size()) {
                            RasporedElement el = (RasporedElement) d.get(trenutniElement);
                            if (el.getEmisija().getVrsta().getId() == idVrsta) {
                                trenutniElement++;
                                return el;
                            }
                        }
                    }
                    trenutniElement = 0;
                }
                trenutniElement = 0;
                trenutniDan = 0;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            for (; trenutniProgram < tvKuca.getChildren().size(); trenutniProgram++) {
                RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
                for (; trenutniDan < 7; trenutniDan++) {
                    RasporedDana d = (RasporedDana) p.get(trenutniDan);
                    for (; trenutniElement < d.getChildren().size(); trenutniElement++) {
                        if (trenutniElement + 1 < d.getChildren().size()) {
                            RasporedElement el = (RasporedElement) d.get(trenutniElement + 1);
                            if (el.getEmisija().getVrsta().getId() == idVrsta) {
                                return true;
                            }
                        }
                    }
                    trenutniElement = 0;
                }
                trenutniElement = 0;
                trenutniDan = 0;
            }
            return false;
        }

        @Override
        public RasporedElement currentItem() {
            RasporedPrograma p = (RasporedPrograma) tvKuca.get(trenutniProgram);
            RasporedDana d = (RasporedDana) p.get(trenutniDan);
            return (RasporedElement) d.get(trenutniElement);
        }

        @Override
        public void resetIterator() {
            trenutniProgram = 0;
            trenutniDan = 0;
            trenutniElement = 0;
        }
    }

}
