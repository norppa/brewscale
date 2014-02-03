/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author riha
 */
public class Elements {

    private String[] tilavuusLista, painoLista;
    private JButton laske, tyhjenna;
    private JTextArea tulosArea;
    private JTextField alkuTilavuus, loppuTilavuus;
    private JComboBox alkuTilavuusYksikko, loppuTilavuusYksikko;
    private ArrayList<JTextField> humalaNimiLista, mallasMaaraLista, humalaMaaraLista;
    private ArrayList<JComboBox> mallasYksikkoLista, humalaYksikkoLista;
//    private FocusListener vikanRivinKuuntelija;
    private JPanel raakaAineetPanel, maltaatPanel, humalatPanel;
    private double alkuTilavuusDbl, loppuTilavuusDbl;
    private ArrayList<JTextField>[] nimiListat, maaraListat;
    private ArrayList<JComboBox>[] yksikkoListat;
    private FocusListener[] vikanRivinKuuntelijat;

    public Elements() {
        tilavuusLista = new String[]{"l", "gal"};
        painoLista = new String[]{"g", "oz", "lbs"};
        nimiListat = new ArrayList[3];
        maaraListat = new ArrayList[3];
        yksikkoListat = new ArrayList[3];
        vikanRivinKuuntelijat = new FocusListener[3];
    }

    public JPanel reseptinKokoPanel() {
        JPanel reseptinKokoPanel = new JPanel();
        reseptinKokoPanel.add(new JLabel("Satsin koko:"));
        reseptinKokoPanel.add(new JTextField("20"));
        reseptinKokoPanel.add(new JComboBox(tilavuusLista));

        reseptinKokoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return reseptinKokoPanel;
    }

    public JScrollPane maltaatPanel() {

        nimiListat[0] = new ArrayList<JTextField>();
        maaraListat[0] = new ArrayList<JTextField>();
        yksikkoListat[0] = new ArrayList<JComboBox>();

        maltaatPanel = new JPanel();
        maltaatPanel.setLayout(new BoxLayout(maltaatPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 3; i++) {
            maltaatPanel.add(luoUusiRivi("maltaat"));
        }

        vikanRivinKuuntelijat[0] = new FocusListener() {
            public void focusGained(FocusEvent evt) {
                maltaatPanel.add(luoUusiRivi("maltaat"));
                paivitaKuuntelijat("maltaat");
                maltaatPanel.revalidate();
            }

            public void focusLost(FocusEvent evt) {
            }
        };

        nimiListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);
        maaraListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);
        yksikkoListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);

        JScrollPane pane = new JScrollPane(maltaatPanel);
        pane.setPreferredSize(new Dimension(400, 200));

        return pane;
    }

    public JScrollPane humalatPanel() {

        nimiListat[1] = new ArrayList<JTextField>();
        maaraListat[1] = new ArrayList<JTextField>();
        yksikkoListat[1] = new ArrayList<JComboBox>();

        humalatPanel = new JPanel();
        humalatPanel.setLayout(new BoxLayout(humalatPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 3; i++) {
            humalatPanel.add(luoUusiRivi("humalat"));
        }

        vikanRivinKuuntelijat[1] = new FocusListener() {
            public void focusGained(FocusEvent evt) {
                humalatPanel.add(luoUusiRivi("humalat"));
                paivitaKuuntelijat("humalat");
                humalatPanel.revalidate();
            }

            public void focusLost(FocusEvent evt) {
            }
        };

        nimiListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);
        maaraListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);
        yksikkoListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);

        JScrollPane pane = new JScrollPane(humalatPanel);
        pane.setPreferredSize(new Dimension(400, 200));

        return pane;
    }

    private JPanel luoUusiRivi(String kategoria) {
        int i = kategoriaNumero(kategoria);

        JPanel uusiPaneeli = new JPanel();
        nimiListat[i].add(0, new JTextField(20));
        maaraListat[i].add(0, new JTextField(5));
        yksikkoListat[i].add(0, new JComboBox(painoLista));

        uusiPaneeli.add(nimiListat[i].get(0));
        uusiPaneeli.add(maaraListat[i].get(0));
        uusiPaneeli.add(yksikkoListat[i].get(0));
        uusiPaneeli.setMaximumSize(new Dimension(1000, 30));

        return uusiPaneeli;
    }

    private void paivitaKuuntelijat(String kategoria) {
        int i = kategoriaNumero(kategoria);
        nimiListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        maaraListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        yksikkoListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        nimiListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
        maaraListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
        yksikkoListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
    }

    private int kategoriaNumero(String kategoria) {
        int i = -1;
        if (kategoria.equals("maltaat")) {
            i = 0;
        } else if (kategoria.equals("humalat")) {
            i = 1;
        } else if (kategoria.equals("muut")) {
            i = 2;
        }
        return i;
    }

}
