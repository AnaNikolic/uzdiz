/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import composite.KomponentaRasporeda;
import composite.RasporedElement;

/**
 *
 * @author Ana
 */
public interface Iterator {
    
    public RasporedElement first();
    public RasporedElement next();
    public boolean hasNext();
    public RasporedElement currentItem();
    public void resetIterator();
    
    
}
