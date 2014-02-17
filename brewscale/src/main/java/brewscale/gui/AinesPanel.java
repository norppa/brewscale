/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * AinesPanel on abstrakti yläluokka, joka pitää sisällään raaka-ainepaneelien
 * perustoiminnallisuuden.
 *
 * @author Jari Haavisto
 */
public abstract class AinesPanel extends JScrollPane {

    public Komponentit k;
    public int ainesNro;
    public JPanel panel;

    public AinesPanel(Komponentit k, int ainesNro) {
        this.k = k;
        this.ainesNro = ainesNro;
        k.nimiListat[ainesNro] = new ArrayList<JTextField>();
        k.maaraListat[ainesNro] = new ArrayList<JTextField>();
        k.yksikkoListat[ainesNro] = new ArrayList<JComboBox>();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    /**
     * LuoKentät luo rivejä, joissa ovat ainestiedot (nimi, määrä, yksikkö).
     * Ulkoasu riippuu aineksesta.
     */
    abstract void luoKentat();

    /**
     * Metodi viimeistele() asettaa sisällön näkyviin, luo kehyksen ja asettaa
     * elementin koon.
     *
     * @param title Kehyksessä näkyvä nimi.
     */
    public void viimeistele(String title) {
        this.setViewportView(panel);
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setPreferredSize(new Dimension(400, 200));
    }

    /**
     * Luo uuden rivin. Tarkoitettu yksinomaan luoKentat()-metodin
     * käytettäväksi.
     *
     * @return JPanel-elementti joka pitää sisällään nimi-, määrä- ja
     * yksikkökentät.
     */
    public JPanel luoUusiRivi() {

        JPanel uusiPaneeli = new JPanel();
        k.nimiListat[ainesNro].add(0, new JTextField(20));
        k.maaraListat[ainesNro].add(0, new JTextField(6));
        k.yksikkoListat[ainesNro].add(0, new JComboBox(k.painoLista));

        uusiPaneeli.add(k.nimiListat[ainesNro].get(0));
        uusiPaneeli.add(k.maaraListat[ainesNro].get(0));
        uusiPaneeli.add(k.yksikkoListat[ainesNro].get(0));

        uusiPaneeli.setMaximumSize(new Dimension(1000, 30));
        return uusiPaneeli;
    }

    /**
     * Päivittää FocusListener-oliot siten, että ne ovat kiinnitettynä
     * viimeiseen olemassaolevaan riviin.
     */
    public void paivitaKuuntelijat() {
        k.nimiListat[ainesNro].get(1).removeFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.maaraListat[ainesNro].get(1).removeFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.yksikkoListat[ainesNro].get(1).removeFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.nimiListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.maaraListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.yksikkoListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
    }

    /**
     * Lisää FocusListener-olion viimeisen rivin kenttiin.
     */
    public void lisaaVikanRivinKuuntelijat() {
        k.vikanRivinKuuntelijat[ainesNro] = new VikanRivinKuuntelija();
        k.nimiListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.maaraListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
        k.yksikkoListat[ainesNro].get(0).addFocusListener(k.vikanRivinKuuntelijat[ainesNro]);
    }

    /**
     * FocusListener-luokka joka lisää uuden riivn, päivittää kuuntelijat
     * kyseiselle riville ja päivittää paneelin näkymän.
     */
    public class VikanRivinKuuntelija implements FocusListener {

        @Override
        public void focusGained(FocusEvent evt) {
            panel.add(luoUusiRivi());
            paivitaKuuntelijat();
            panel.revalidate();
        }

        @Override
        public void focusLost(FocusEvent evt) {
        }
    }

    /**
     * Apumetodi muotoilee doublesta kaksidesimaalisen String-esityksen
     * 
     * @param dbl Numero, joka muotoillaan
     * @return  String-muotoinen esitys numerosta
     */
    public String muotoileNumero(double dbl) {
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        return decimalFormat.format(dbl);
    }
}
