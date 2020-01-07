/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import anikolic_zadaca_2.Uloga;

/**
 *
 * @author Ana
 */
public interface Subject {
    
    void notifySubscriber(int osoba, int staraUloga, Uloga novaUloga);
    
}
