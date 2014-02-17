/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.resepti.MuuAines;
import java.util.ArrayList;

/**
 * MuutAineksetPanelö on JScrollPane-elementti joka pitää sisällään listan
 * reseptin aineksista, jotka eivät ole maltaita tai humalia.
 *
 * @author Jari Haavisto
 */
public class MuutAineksetPanel extends AinesPanel {

    public MuutAineksetPanel(Komponentit k) {
        super(k, 2);
        luoKentat();
        lisaaVikanRivinKuuntelijat();
        viimeistele("Muut ainekset");
    }

    /**
     * Mikäli reseptissä on muita aineita, metodi luo jokaiselle oman rivinsä ja
     * mikäli muita aineita ei ole, luodaan kolme tyhjää riviä.
     */
    @Override
    public void luoKentat() {
        ArrayList<MuuAines> ainesLista = k.resepti.getMuutAinekset();
        if (ainesLista.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi());
            }
        } else {
            for (int i = 0; i < ainesLista.size(); i++) {
                panel.add(luoUusiRivi());
                k.nimiListat[2].get(0).setText(ainesLista.get(i).getNimi());
                k.maaraListat[2].get(0).setText(muotoileNumero(ainesLista.get(i).getMaara()));
                k.yksikkoListat[2].get(0).setSelectedItem(ainesLista.get(i).getYksikko());
            }
            panel.add(luoUusiRivi());
        }
    }

}
