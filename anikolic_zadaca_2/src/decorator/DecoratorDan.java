package decorator;

import composite.KomponentaRasporeda;
import composite.RasporedDana;
import composite.RasporedElement;
import composite.RasporedTvKuce;
import java.util.List;

/**
 *
 * @author Ana
 */
public class DecoratorDan extends DecoratorBase {

    public DecoratorDan(Decorator wrappee) {
        super(wrappee);
    }

    @Override
    public void dekorirajIspis() {
        super.dekorirajIspis();
        String line = "__________________________________________________________"
                + "________________________________________________________________________";

        System.out.println(line);
        System.out.println(String.format("%6s %6s %-38s %-18s %-8s %-24s", "POCETAK",
                "KRAJ","NAZIV", "VRSTA", "REKLAME", "SUDIONICI"));
        System.out.println(line);
        RasporedDana d = (RasporedDana) super.getWrappee();
        for (KomponentaRasporeda e : d.getChildren()) {
            RasporedElement el = (RasporedElement) e;
            el.dekorirajIspis();
        }
        System.out.println(line);
    }

}
