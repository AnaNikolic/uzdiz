/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class Anikolic_zadaca_1 {

    private static TvKuca tvKuca;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!provjeriArgumente(args)){
            System.out.println("Pogrešno uneseni argumenti! Završavam program.");
            return;
        }
        tvKuca = TvKuca.vratiInstancu();
        if (!tvKuca.ucitajPodatke(args)){
            return;
        }
        tvKuca.kreirajRasporedTvKuce();
        tvKuca.ponudiIspis();
    }

    private static boolean provjeriArgumente(String[] args) {
        return (args[0].equals("-t") 
                && args[2].equals("-e") 
                && args[4].equals("-o") 
                && args[6].equals("-u")
                && args[1].endsWith(".txt")
                && args[3].endsWith(".txt")
                && args[5].endsWith(".txt")
                && args[7].endsWith(".txt"));
    }

    
}
