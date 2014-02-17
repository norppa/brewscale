/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.brewscale.Brewscale;
import brewscale.brewscale.FileHandler;
import brewscale.resepti.*;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BrewscaleGUI implements Runnable {
    
    private Komponentit k;
    private JFrame frame;
    private Brewscale brewscale;
    private JPanel panel;

    public BrewscaleGUI(Brewscale brewscale) {
        this.brewscale = brewscale;
    }

    @Override
    public void run() {
        frame = new JFrame("Brewscale");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGUI(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Muotoilee perusnäkymän.
     *
     * @param c
     */
    private void initGUI(Container c) {
        
        k = new Komponentit(brewscale.getResepti());

        JPanel toimintoPanel = new JPanel();
        toimintoPanel.setLayout(new BoxLayout(toimintoPanel, BoxLayout.PAGE_AXIS));

        toimintoPanel.add(new TiedostoToiminnotPanel(k));
        toimintoPanel.add(new ReseptiToiminnotPanel(k));

        lisaaActionListenerNappuloihin();
        
        JPanel reseptiPanel = new JPanel();
        reseptiPanel.setLayout(new BoxLayout(reseptiPanel, BoxLayout.LINE_AXIS));
        JPanel reseptiAineksetPanel = new JPanel();
        reseptiAineksetPanel.setLayout(new BoxLayout(reseptiAineksetPanel, BoxLayout.PAGE_AXIS));
        JPanel reseptiMuutPanel = new JPanel();
        reseptiMuutPanel.setLayout(new BoxLayout(reseptiMuutPanel, BoxLayout.PAGE_AXIS));
        reseptiPanel.add(reseptiAineksetPanel);
        reseptiPanel.add(reseptiMuutPanel);
        
        reseptiMuutPanel.add(new ReseptinPerustiedotPanel(k));
        reseptiMuutPanel.add(new OhjeetPanel(k));
        reseptiMuutPanel.add(new MuistiinpanotPanel(k));
        reseptiAineksetPanel.add(new MaltaatPanel(k));
        reseptiAineksetPanel.add(new HumalatPanel(k));
        reseptiAineksetPanel.add(new MuutAineksetPanel(k));

        panel = new JPanel();
        this.getFrame().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new OtsikkoPanel());
        panel.add(toimintoPanel);
        panel.add(reseptiPanel);
        panel.setPreferredSize(new Dimension(1000, 850));

    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Päivittää näkymän muutosten jälkeen.
     */
    private void uudistaNakyma() {
        frame.remove(panel);
        initGUI(frame.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Lisää ActionListenerin kaikkiin JButtoneihin.
     */
    private void lisaaActionListenerNappuloihin() {
        k.uusiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiReseptiPainettu();
            }
        });

        k.avaaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaaReseptiPainettu();
            }
        });

        k.tallennaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallennaReseptiPainettu();
            }
        });

        k.muutaGrammoiksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muutaGrammoiksiPainettu();
            }
        });

        k.skaalaaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skaalaaReseptiPainettu();
            }
        });

    }

    private void uusiReseptiPainettu() {
        brewscale.setResepti(new Resepti("", 0, "l"));
        uudistaNakyma();
    }

    private void avaaReseptiPainettu() {
        JFileChooser fc = new JFileChooser("./reseptit/");
        int rVal = fc.showOpenDialog(panel);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File avattavaTiedosto = fc.getSelectedFile();

            FileHandler fileHandler = new FileHandler();

            Resepti avattuResepti = fileHandler.lueResepti(avattavaTiedosto);
            if (avattuResepti == null) {
                return;
            }
            brewscale.setResepti(avattuResepti);
            uudistaNakyma();

            System.out.println(brewscale.getResepti().getMuistiinpanot());
        }
    }

    private void tallennaReseptiPainettu() {
        if (!tarkistaMaaraMuotoilut()) {
            JOptionPane.showMessageDialog(frame, "Tarkista annetut ainesmäärät", "varoitus", JOptionPane.WARNING_MESSAGE);
            return;
        }
        paivitaResepti();
        uudistaNakyma();
        brewscale.tallenna();
    }

    private void skaalaaReseptiPainettu() {
        try {
            double uusiTilavuus = Double.parseDouble(k.uusiTilavuusField.getText());
            String uusiYksikko = k.uusiTilavuusCombo.getSelectedItem().toString();
            brewscale.skaalaa(uusiTilavuus, uusiYksikko);
            uudistaNakyma();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Uusi tilavuus ei kelpaa", "varoitus", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void muutaGrammoiksiPainettu() {
        brewscale.muutaGrammoiksi();
        uudistaNakyma();
    }

//    public JScrollPane muutAineksetPanel() {
//
//        k.nimiListat[2] = new ArrayList<JTextField>();
//        k.maaraListat[2] = new ArrayList<JTextField>();
//        k.yksikkoListat[2] = new ArrayList<JComboBox>();
//
//        JPanel panel = new JPanel();
//        panel = muutAineetPanel;
//        ArrayList<Aines> muutAineksetLista = brewscale.getResepti().getMuutAinekset();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
////        panel.add(new JLabel("Muut ainekset"));
//
//        if (muutAineksetLista.size() == 0) {
//            for (int i = 0; i < 3; i++) {
//                panel.add(luoUusiRivi(2));
//            }
//        } else {
//            for (int i = 0; i < muutAineksetLista.size(); i++) {
//                panel.add(luoUusiRivi(2));
//                k.nimiListat[2].get(0).setText(muutAineksetLista.get(i).getNimi());
//                k.maaraListat[2].get(0).setText(muutAineksetLista.get(i).getMaara() + "");
//                k.yksikkoListat[2].get(0).setSelectedItem(muutAineksetLista.get(i).getYksikko());
//            }
//            panel.add(luoUusiRivi(2));
//        }
//
//        lisaaVikanRivinKuuntelijat(2);
//
//        JScrollPane pane = new JScrollPane(panel);
//        pane.setBorder(BorderFactory.createTitledBorder("Muut ainekset"));
//        pane.setPreferredSize(new Dimension(400, 200));
//        return pane;
//    }
//
//    public JScrollPane maltaatPanel() {
//
//        k.nimiListat[0] = new ArrayList<JTextField>();
//        k.maaraListat[0] = new ArrayList<JTextField>();
//        k.yksikkoListat[0] = new ArrayList<JComboBox>();
//
//        JPanel panel = new JPanel();
//        panel = maltaatPanel;
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        ArrayList<Mallas> mallasLista = brewscale.getResepti().getMaltaat();
//
//        if (mallasLista.isEmpty()) {
//            for (int i = 0; i < 3; i++) {
//                panel.add(luoUusiRivi(0));
//            }
//        } else {
//            for (int i = 0; i < mallasLista.size(); i++) {
//                panel.add(luoUusiRivi(0));
//                k.nimiListat[0].get(0).setText(mallasLista.get(i).getNimi());
//                k.maaraListat[0].get(0).setText(mallasLista.get(i).getMaara() + "");
//                k.yksikkoListat[0].get(0).setSelectedItem(mallasLista.get(i).getYksikko());
//            }
//            panel.add(luoUusiRivi(0));
//        }
//
//        lisaaVikanRivinKuuntelijat(0);
//
//        JScrollPane pane = new JScrollPane(panel);
//        pane.setBorder(BorderFactory.createTitledBorder("Maltaat"));
//        pane.setPreferredSize(new Dimension(400, 200));
//        return pane;
//    }
//
//    public JScrollPane humalatPanel() {
//
//        k.nimiListat[1] = new ArrayList<JTextField>();
//        k.maaraListat[1] = new ArrayList<JTextField>();
//        k.yksikkoListat[1] = new ArrayList<JComboBox>();
//
//        JPanel panel = new JPanel();
//        panel = humalatPanel;
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
////        panel.add(new JLabel("Humalat"));
//
//        ArrayList<Humala> humalaLista = brewscale.getResepti().getHumalat();
//        System.out.println("!!!! " + humalaLista.size());
//
//        if (humalaLista.isEmpty()) {
//            for (int i = 0; i < 3; i++) {
//                panel.add(luoUusiRivi(1));
//            }
//        } else {
//            for (int i = 0; i < humalaLista.size(); i++) {
//                panel.add(luoUusiRivi(1));
//                k.nimiListat[1].get(0).setText(humalaLista.get(i).getNimi());
//                k.maaraListat[1].get(0).setText(humalaLista.get(i).getMaara() + "");
//                k.yksikkoListat[1].get(0).setSelectedItem(humalaLista.get(i).getYksikko());
//                k.alphaMaaraLista.get(0).setText(humalaLista.get(i).getAlphaAcid() + "");
//            }
//            panel.add(luoUusiRivi(1));
//        }
//
//        lisaaVikanRivinKuuntelijat(1);
//
//        JScrollPane pane = new JScrollPane(panel);
//        pane.setBorder(BorderFactory.createTitledBorder("Humalat"));
//        pane.setPreferredSize(new Dimension(450, 200));
//        return pane;
//    }
//
//    private void lisaaVikanRivinKuuntelijat(int i) {
//        k.vikanRivinKuuntelijat[i] = vikanRivinKuuntelija(i);
//        k.nimiListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.maaraListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.yksikkoListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//    }
//
//    private FocusListener vikanRivinKuuntelija(int k) {
//        if (k == 0) {
//            return new FocusListener() {
//                public void focusGained(FocusEvent evt) {
//                    maltaatPanel.add(luoUusiRivi(0));
//                    paivitaKuuntelijat(0);
//                    maltaatPanel.revalidate();
//                }
//
//                public void focusLost(FocusEvent evt) {
//                }
//            };
//        }
//
//        if (k == 1) {
//            return new FocusListener() {
//                public void focusGained(FocusEvent evt) {
//                    humalatPanel.add(luoUusiRivi(1));
//                    paivitaKuuntelijat(1);
//                    humalatPanel.revalidate();
//                }
//
//                public void focusLost(FocusEvent evt) {
//                }
//            };
//        }
//
//        if (k == 2) {
//            return new FocusListener() {
//                public void focusGained(FocusEvent evt) {
//                    muutAineetPanel.add(luoUusiRivi(2));
//                    paivitaKuuntelijat(2);
//                    muutAineetPanel.revalidate();
//                }
//
//                public void focusLost(FocusEvent evt) {
//                }
//            };
//        }
//        return null;
//    }
//
//    private JPanel luoUusiRivi(int i) {
//        if (i < 0 || i > 2) {
//            return null;
//        }
//
//        JPanel uusiPaneeli = new JPanel();
//        k.nimiListat[i].add(0, new JTextField(20));
//        k.maaraListat[i].add(0, new JTextField(5));
//        k.yksikkoListat[i].add(0, new JComboBox(k.painoLista));
//
//        uusiPaneeli.add(k.nimiListat[i].get(0));
//        uusiPaneeli.add(k.maaraListat[i].get(0));
//        uusiPaneeli.add(k.yksikkoListat[i].get(0));
//
//        if (i == 1) {
//            k.alphaMaaraLista.add(0, new JTextField(4));
//            uusiPaneeli.add(k.alphaMaaraLista.get(0));
//            uusiPaneeli.add(new JLabel("%AA"));
//        }
//
//        uusiPaneeli.setMaximumSize(new Dimension(1000, 30));
//
//        return uusiPaneeli;
//    }
//
//    private void paivitaKuuntelijat(int i) {
//        if (i < 0 || i > 2) {
//            return;
//        }
//        k.nimiListat[i].get(1).removeFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.maaraListat[i].get(1).removeFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.yksikkoListat[i].get(1).removeFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.nimiListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.maaraListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//        k.yksikkoListat[i].get(0).addFocusListener(k.vikanRivinKuuntelijat[i]);
//    }

    /**
     * Lukee gui:sta uuden reseptin ja päivittää sen aktiiviseksi reseptiksi.
     */
    private void paivitaResepti() {

        double tilavuus = 0;
        try {
            tilavuus = Double.parseDouble(k.tilavuusField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Satsin koko ei kelpaa");
            return;
        }
        Resepti resepti = brewscale.getResepti();
        resepti.setNimi(k.nimiField.getText());
        resepti.setKoko(tilavuus);
        resepti.setKokoYksikko(k.tilavuusCombo.getSelectedItem().toString());

        resepti.tyhjennaAinekset();
        for (int i = 0; i < 3; i++) {
            for (int j = k.nimiListat[i].size() - 1; j > 0; j--) {
                String nimi = k.nimiListat[i].get(j).getText();
                String maara = k.maaraListat[i].get(j).getText();
                String yksikko = k.yksikkoListat[i].get(j).getSelectedItem().toString();
                if (!maara.isEmpty()) {
                    if (i == 0) {
                        resepti.lisaaMallas(nimi, maara, yksikko);
                    }
                    if (i == 1) {
                        String alpha = k.alphaMaaraLista.get(j).getText();
                        resepti.lisaaHumala(nimi, maara, yksikko, alpha);
                    }
                    if (i == 2) {
                        resepti.lisaaMuuAines(nimi, maara, yksikko);
                    }
                }
            }
        }
        resepti.setOhje(k.ohjeetArea.getText());
        resepti.setMuistiinpanot(k.muistiinpanotArea.getText());
    }

    /**
     * Tarkistaa, että annetut raaka-ainemäärät sekä alphahappopitoisuudet ovat
     * muutettavissa double-muotoisiksi
     *
     * @return false mikäli jokin ei ole muunnettavissa
     */
    private boolean tarkistaMaaraMuotoilut() {
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = k.maaraListat[i].size() - 1; j > 0; j--) {
                    Double.parseDouble(k.maaraListat[i].get(j).getText());
                    if (i == 1) {
                        Double.parseDouble(k.alphaMaaraLista.get(j).getText());
                    }
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
