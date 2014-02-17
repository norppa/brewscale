/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.resepti.Mallas;
import java.util.ArrayList;

/**
 *
 * @author riha
 */
public class MaltaatPanel extends AinesPanel {

    public MaltaatPanel(Komponentit k) {
        super(k, 0); // mallas
        luoKentat();
        lisaaVikanRivinKuuntelijat();
        viimeistele("Maltaat");
    }

    @Override
    public void luoKentat() {
        ArrayList<Mallas> mallasLista = k.resepti.getMaltaat();
        if (mallasLista.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi());
            }
        } else {
            for (int i = 0; i < mallasLista.size(); i++) {
                panel.add(luoUusiRivi());
                k.nimiListat[0].get(0).setText(mallasLista.get(i).getNimi());
                k.maaraListat[0].get(0).setText(muotoileNumero(mallasLista.get(i).getMaara()));
                k.yksikkoListat[0].get(0).setSelectedItem(mallasLista.get(i).getYksikko());
            }
            panel.add(luoUusiRivi());
        }
    }
    


}
