/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import anikolic_zadaca_2.PrikazEmisije;
import prototype.Emisija;
import composite.RasporedPrograma;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public interface DodavanjeEmisijaSucelje {
    
   RasporedPrograma gradiRaspored(Map<Emisija, List<PrikazEmisije>> podaciEmisija, RasporedPrograma raspored);
    
    
}
