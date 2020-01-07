/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ana
 */
public abstract class KomponentaRasporedaApstr {

    List<KomponentaRasporeda> children = new ArrayList<>(){};
    
    public void add(KomponentaRasporeda komponenta) {
       children.add(komponenta);
    }

    public void remove(KomponentaRasporeda komponenta) {
        children.remove(komponenta);
    }
    
    public void zamijeni(KomponentaRasporeda stara, KomponentaRasporeda nova){
        Collections.replaceAll(children, stara, nova);
    }

    public List<KomponentaRasporeda> getChildren() {
        return children;
    }
    
    public KomponentaRasporeda get(int index){
        return children.get(index);
    }

}
