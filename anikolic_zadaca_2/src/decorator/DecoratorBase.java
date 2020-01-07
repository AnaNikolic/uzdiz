/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author Ana
 */
public class DecoratorBase implements Decorator {

    private Decorator wrappee;

    DecoratorBase(Decorator wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void dekorirajIspis() {
       wrappee.dekorirajIspis();
    }

    public Decorator getWrappee() {
        return wrappee;
    }
    
}
