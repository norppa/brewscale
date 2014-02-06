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
    private ArrayList<JTextField>[] nimiListat, maaraListat;
    private ArrayList<JComboBox>[] yksikkoListat;
    private FocusListener[] vikanRivinKuuntelijat;
    private final JPanel maltaatPanel, humalatPanel, muutAineetPanel;

    public Elements() {
        tilavuusLista = new String[]{"l", "gal"};
        painoLista = new String[]{"g", "oz", "lbs"};
        nimiListat = new ArrayList[3];
        maaraListat = new ArrayList[3];
        yksikkoListat = new ArrayList[3];
        vikanRivinKuuntelijat = new FocusListener[3];
        maltaatPanel = new JPanel();
        humalatPanel = new JPanel();
        muutAineetPanel = new JPanel();
    }

    public JPanel reseptinKokoPanel() {
        JPanel reseptinKokoPanel = new JPanel();
        reseptinKokoPanel.add(new JLabel("Satsin koko:"));
        reseptinKokoPanel.add(new JTextField("", 5) );
        reseptinKokoPanel.add(new JComboBox(tilavuusLista));

        reseptinKokoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return reseptinKokoPanel;
    }
    


    public JScrollPane ainesPanel(int k) {  // 0 = maltaat, 1 = humalat, 2 = muut

        nimiListat[k] = new ArrayList<JTextField>();
        maaraListat[k] = new ArrayList<JTextField>();
        yksikkoListat[k] = new ArrayList<JComboBox>();

        JPanel panel = new JPanel();
        String otsikko = "";
        
        if (k == 0) {
            panel = maltaatPanel;
            otsikko = "Maltaat";
        }
        if (k == 1) {
            panel = humalatPanel;
            otsikko = "Humalat";
        }
        if (k == 2) {
            panel = muutAineetPanel;
            otsikko = "Muut ainekset";
        }
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(otsikko));
        
        for (int i = 0; i < 3; i++) {
            panel.add(luoUusiRivi(k));
        }

        vikanRivinKuuntelijat[k] = vikanRivinKuuntelija(k);

        nimiListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);
        maaraListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);
        yksikkoListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);

        JScrollPane pane = new JScrollPane(panel);
        pane.setPreferredSize(new Dimension(400, 200));
        return pane;
    }

    private FocusListener vikanRivinKuuntelija(int k) {
        if (k == 0) {
            return new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    maltaatPanel.add(luoUusiRivi(0));
                    paivitaKuuntelijat(0);
                    maltaatPanel.revalidate();
                }

                public void focusLost(FocusEvent evt) {
                }
            };
        }

        if (k == 1) {
            return new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    humalatPanel.add(luoUusiRivi(1));
                    paivitaKuuntelijat(1);
                    humalatPanel.revalidate();
                }

                public void focusLost(FocusEvent evt) {
                }
            };
        }

        if (k == 2) {
            return new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    muutAineetPanel.add(luoUusiRivi(2));
                    paivitaKuuntelijat(2);
                    muutAineetPanel.revalidate();
                }

                public void focusLost(FocusEvent evt) {
                }
            };
        }
        return null;
    }

//    public JScrollPane maltaatPanel() {
//
//        nimiListat[0] = new ArrayList<JTextField>();
//        maaraListat[0] = new ArrayList<JTextField>();
//        yksikkoListat[0] = new ArrayList<JComboBox>();
//
//        maltaatPanel = new JPanel();
//        maltaatPanel.setLayout(new BoxLayout(maltaatPanel, BoxLayout.Y_AXIS));
//
//        for (int i = 0; i < 3; i++) {
//            maltaatPanel.add(luoUusiRivi("maltaat"));
//        }
//
//        vikanRivinKuuntelijat[0] = new FocusListener() {
//            public void focusGained(FocusEvent evt) {
//                maltaatPanel.add(luoUusiRivi("maltaat"));
//                paivitaKuuntelijat("maltaat");
//                maltaatPanel.revalidate();
//            }
//
//            public void focusLost(FocusEvent evt) {
//            }
//        };
//
//        nimiListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);
//        maaraListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);
//        yksikkoListat[0].get(0).addFocusListener(vikanRivinKuuntelijat[0]);
//
//        JScrollPane pane = new JScrollPane(maltaatPanel);
//        pane.setPreferredSize(new Dimension(400, 200));
//
//        return pane;
//    }
//
//    public JScrollPane humalatPanel() {
//
//        nimiListat[1] = new ArrayList<JTextField>();
//        maaraListat[1] = new ArrayList<JTextField>();
//        yksikkoListat[1] = new ArrayList<JComboBox>();
//
//        humalatPanel = new JPanel();
//        humalatPanel.setLayout(new BoxLayout(humalatPanel, BoxLayout.Y_AXIS));
//
//        for (int i = 0; i < 3; i++) {
//            humalatPanel.add(luoUusiRivi("humalat"));
//        }
//
//        vikanRivinKuuntelijat[1] = new FocusListener() {
//            public void focusGained(FocusEvent evt) {
//                humalatPanel.add(luoUusiRivi("humalat"));
//                paivitaKuuntelijat("humalat");
//                humalatPanel.revalidate();
//            }
//
//            public void focusLost(FocusEvent evt) {
//            }
//        };
//
//        nimiListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);
//        maaraListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);
//        yksikkoListat[1].get(0).addFocusListener(vikanRivinKuuntelijat[1]);
//
//        JScrollPane pane = new JScrollPane(humalatPanel);
//        pane.setPreferredSize(new Dimension(400, 200));
//
//        return pane;
//    }
    private JPanel luoUusiRivi(int i) {
        if (i < 0 || i > 2) {
            return null;
        }

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

    private void paivitaKuuntelijat(int i) {
        if (i < 0 || i > 2) {
            return;
        }
        nimiListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        maaraListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        yksikkoListat[i].get(1).removeFocusListener(vikanRivinKuuntelijat[i]);
        nimiListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
        maaraListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
        yksikkoListat[i].get(0).addFocusListener(vikanRivinKuuntelijat[i]);
    }

}
