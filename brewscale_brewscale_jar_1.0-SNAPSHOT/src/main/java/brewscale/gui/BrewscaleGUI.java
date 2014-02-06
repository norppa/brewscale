/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.brewscale.Brewscale;
import brewscale.resepti.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class BrewscaleGUI implements Runnable {

    private JFrame frame;
    private Brewscale brewscale;
    private String[] tilavuusLista, painoLista;
    private ArrayList<JTextField>[] nimiListat, maaraListat;
    private ArrayList<JComboBox>[] yksikkoListat;
    private FocusListener[] vikanRivinKuuntelijat;
    private JPanel panel, maltaatPanel, humalatPanel, muutAineetPanel, konversioPanel, kokoPanel;

    public BrewscaleGUI(Brewscale brewscale) {
        this.brewscale = brewscale;
        tilavuusLista = new String[]{"l", "gal"};
        painoLista = new String[]{"g", "oz", "lbs"};
        nimiListat = new ArrayList[3];
        maaraListat = new ArrayList[3];
        yksikkoListat = new ArrayList[3];
        vikanRivinKuuntelijat = new FocusListener[3];
        maltaatPanel = new JPanel();
        humalatPanel = new JPanel();
        muutAineetPanel = new JPanel();
        konversioPanel = new JPanel();
    }

    @Override
    public void run() {
        frame = new JFrame("Brewscale");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGUI(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void initGUI(Container c) {


        JPanel nappulat = new JPanel();
        JButton avaaReseptiBtn = new JButton("Avaa resepti");
        avaaReseptiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaaReseptiPainettu(evt);
            }
        });
        JButton uusiReseptiBtn = new JButton("Uusi resepti");
        uusiReseptiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiReseptiPainettu(evt);
            }
        });
        nappulat.add(avaaReseptiBtn);
        nappulat.add(uusiReseptiBtn);

        kokoPanel = reseptinKokoPanel();

        panel = new JPanel();
        this.getFrame().add(panel);
        panel.add(nappulat);
        
        panel.add(kokoPanel);

        panel.add(konversioPanel);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void avaaReseptiPainettu(java.awt.event.ActionEvent evt) {
        System.out.println("Nappia painettu");
    }

    private void uusiReseptiPainettu(java.awt.event.ActionEvent evt) {
        brewscale.setResepti(new Resepti("", 0, "l"));
//        panel.remove(kokoPanel);
        kokoPanel = reseptinKokoPanel();

        panel.revalidate();
        panel.repaint();
    }

    private JPanel reseptinKokoPanel() {
        JPanel reseptinKokoPanel = new JPanel();
        reseptinKokoPanel.add(new JLabel("Satsin koko:"));
        reseptinKokoPanel.add(new JTextField(brewscale.getResepti().getKoko() + "", 5));
        reseptinKokoPanel.add(new JComboBox(tilavuusLista));

        reseptinKokoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return reseptinKokoPanel;
    }

    public JScrollPane ainesPanel(int k) {  // 0 = maltaat, 1 = humalat, 2 = muut

        nimiListat[k] = new ArrayList<JTextField>();
        maaraListat[k] = new ArrayList<JTextField>();
        yksikkoListat[k] = new ArrayList<JComboBox>();
        ArrayList<RaakaAine> ainesLista = new ArrayList<RaakaAine>();

        JPanel panel = new JPanel();
        String otsikko = "";

        if (k == 0) {
            panel = maltaatPanel;
            otsikko = "Maltaat";
            ArrayList<Mallas> maltaat = brewscale.getResepti().getMaltaat();
            for (Mallas m : maltaat) {
                ainesLista.add(m);
            }
        }
        if (k == 1) {
            panel = humalatPanel;
            otsikko = "Humalat";
            ArrayList<Humala> humalat = brewscale.getResepti().getHumalat();
            for (Humala h : humalat) {
                ainesLista.add(h);
            }
        }
        if (k == 2) {
            panel = muutAineetPanel;
            otsikko = "Muut ainekset";
            ArrayList<Aines> muutAinekset = brewscale.getResepti().getMuutAinekset();
            for (Aines a : muutAinekset) {
                ainesLista.add(a);
            }
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(otsikko));

        if (ainesLista.size() == 0) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi(k));
            }
        } else {
            for (int i = 0; i < ainesLista.size(); i++) {
                panel.add(luoUusiRivi(k));
                nimiListat[k].get(0).setText(ainesLista.get(i).getNimi());
                maaraListat[k].get(0).setText(ainesLista.get(i).getMaara() + "");
                yksikkoListat[k].get(0).setSelectedItem(ainesLista.get(i).getYksikko());
            }
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
