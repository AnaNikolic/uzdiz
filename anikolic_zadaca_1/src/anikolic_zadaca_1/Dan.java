/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anikolic_zadaca_1;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ana
 */
public class Dan{
    
    //emisija, vrime pocetka
    private TreeMap<LocalTime, Emisija> emisije;
    
    public Dan() {
        emisije = new TreeMap<>();
    }

    public Dan(TreeMap<LocalTime, Emisija> emisije) {
        this.emisije = emisije;
    }

    public Map<LocalTime, Emisija> getEmisije() {
        return emisije;
    }

    public void setEmisije(TreeMap<LocalTime, Emisija> emisije) {
        this.emisije = emisije;
    }
    
}
