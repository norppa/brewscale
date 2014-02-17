/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.resepti.Humala;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * HumalatPanel on JScrollPane-elementti joka pitää sisällään listan reseptin humalista.
 *
 * @author Jari Haavisto
 */
public class HumalatPanel extends AinesPanel {

    public HumalatPanel(Komponentit k) {
        super(k, 1);
        luoKentat();
        lisaaVikanRivinKuuntelijat();
        viimeistele("Humalat");
    }

    /**
     * Mikäli reseptissä on humalia, metodi luo jokaiselle oman rivinsä ja
     * mikäli humalia ei ole, luodaan kolme tyhjää riviä.
     */
    @Override
    public void luoKentat() {
        ArrayList<Humala> humalaLista = k.resepti.getHumalat();
        if (humalaLista.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi());
            }
        } else {
            for (int i = 0; i < humalaLista.size(); i++) {
                panel.add(luoUusiRivi());
                k.nimiListat[1].get(0).setText(humalaLista.get(i).getNimi());
                k.maaraListat[1].get(0).setText(muotoileNumero(humalaLista.get(i).getMaara()));
                k.yksikkoListat[1].get(0).setSelectedItem(humalaLista.get(i).getYksikko());
                k.alphaMaaraLista.get(0).setText(humalaLista.get(i).getAlphaAcid() + "");
            }
            panel.add(luoUusiRivi());
        }
    }

    /**
     * Luo uuden rivin, jossa on kentät humalan nimelle, määrälle, yksikölle,
     * alpha-happomäärälle sekä alpha-happopitoisuudelle.
     * 
     * @return JPanel-elementti
     */
    @Override
    public JPanel luoUusiRivi() {
        JPanel uusiPaneeli = new JPanel();
        k.nimiListat[1].add(0, new JTextField(18));
        k.maaraListat[1].add(0, new JTextField(6));
        k.yksikkoListat[1].add(0, new JComboBox(k.painoLista));

        uusiPaneeli.add(k.nimiListat[1].get(0));
        uusiPaneeli.add(k.maaraListat[1].get(0));
        uusiPaneeli.add(k.yksikkoListat[1].get(0));
        k.alphaMaaraLista.add(0, new JTextField(4));
        uusiPaneeli.add(k.alphaMaaraLista.get(0));
        uusiPaneeli.add(new JLabel("%AA"));

        uusiPaneeli.setMaximumSize(new Dimension(1000, 30));

        return uusiPaneeli;
    }

}
