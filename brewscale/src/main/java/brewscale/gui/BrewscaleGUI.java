/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.brewscale.Brewscale;
import brewscale.brewscale.FileHandler;
import brewscale.resepti.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class BrewscaleGUI implements Runnable {

    private JFrame frame;
    private Brewscale brewscale;
    private final String[] tilavuusLista = new String[]{"l", "gal"},
            painoLista = new String[]{"g", "oz", "lbs"};
    private ArrayList<JTextField>[] nimiListat, maaraListat;
    private ArrayList<JComboBox>[] yksikkoListat;
    private ArrayList<JTextField> alphaMaaraLista;
    private JTextField nimiField, tilavuusField, uusiTilavuusField;
    private JTextArea ohjeetArea, muistiinpanotArea;
    private JComboBox tilavuusCombo, uusiTilavuusCombo;
    private FocusListener[] vikanRivinKuuntelijat;
    private JPanel maltaatPanel, humalatPanel, muutAineetPanel, konversioPanel, panel;
    private JButton uusiBtn, avaaBtn, tallennaBtn, muutaGrammoiksiBtn, skaalaaBtn;

    public BrewscaleGUI(Brewscale brewscale) {
        this.brewscale = brewscale;
        alustaMuuttujat();
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

        alustaMuuttujat();

        JPanel toimintoPanel = new JPanel();
        toimintoPanel.setLayout(new BoxLayout(toimintoPanel, BoxLayout.PAGE_AXIS));

        toimintoPanel.add(new TiedostoToiminnotPanel(uusiBtn, avaaBtn, tallennaBtn));
        toimintoPanel.add(new ReseptiToiminnotPanel(muutaGrammoiksiBtn, uusiTilavuusField, uusiTilavuusCombo, skaalaaBtn));

        lisaaActionListenerNappuloihin();

        JScrollPane maltaatPanel = maltaatPanel();
        JScrollPane humalatPanel = humalatPanel();
        JScrollPane muutAineksetPanel = muutAineksetPanel();

//        JPanel uusiResepti = new JPanel();
//        JLabel uusiReseptiLabel = new JLabel("uusi resepti");
//        uusiResepti.add(uusiReseptiLabel);
        JPanel reseptiPanel = new JPanel();
        reseptiPanel.setLayout(new BoxLayout(reseptiPanel, BoxLayout.LINE_AXIS));
        JPanel reseptiAineksetPanel = new JPanel();
        reseptiAineksetPanel.setLayout(new BoxLayout(reseptiAineksetPanel, BoxLayout.PAGE_AXIS));
        JPanel reseptiMuutPanel = new JPanel();
        reseptiMuutPanel.setLayout(new BoxLayout(reseptiMuutPanel, BoxLayout.PAGE_AXIS));
        reseptiPanel.add(reseptiAineksetPanel);
        reseptiPanel.add(reseptiMuutPanel);

//        reseptiMuutPanel.add(reseptiNimiPanel());
//        reseptiMuutPanel.add(reseptinKokoPanel());
        reseptiMuutPanel.add(new ReseptinPerustiedotPanel(brewscale.getResepti(), nimiField, tilavuusField, tilavuusCombo));
        reseptiMuutPanel.add(ohjeetArea());
        muistiinpanotArea = new JTextArea(brewscale.getResepti().getMuistiinpanot());
        reseptiMuutPanel.add(muistiinpanotArea());
        reseptiAineksetPanel.add(maltaatPanel);
        reseptiAineksetPanel.add(humalatPanel);
        reseptiAineksetPanel.add(muutAineksetPanel);

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
     * Tyhjentää kaikki oliomuuttujat.
     */
    private void alustaMuuttujat() {
        nimiListat = new ArrayList[3];
        maaraListat = new ArrayList[3];
        yksikkoListat = new ArrayList[3];
        alphaMaaraLista = new ArrayList<JTextField>();
        vikanRivinKuuntelijat = new FocusListener[3];
        maltaatPanel = new JPanel();
        humalatPanel = new JPanel();
        muutAineetPanel = new JPanel();
        konversioPanel = new JPanel();

        nimiField = new JTextField();
        tilavuusField = new JTextField();
        tilavuusCombo = new JComboBox();
        uusiTilavuusField = new JTextField();
        uusiTilavuusCombo = new JComboBox();

        uusiBtn = new JButton();
        avaaBtn = new JButton();
        tallennaBtn = new JButton();
        muutaGrammoiksiBtn = new JButton();
        skaalaaBtn = new JButton();
    }

    /**
     * Päivittää näkymän muutosten jälkeen.
     */
    private void uudistaNakyma() {
        alustaMuuttujat();
        frame.remove(panel);
        initGUI(frame.getContentPane());
        frame.revalidate();
        frame.repaint();
    }
    
        /**
     * Lisää ActionListenerin kaikkiin JButtoneihin.
     */
    private void lisaaActionListenerNappuloihin() {
        uusiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiReseptiPainettu();
            }
        });

        avaaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaaReseptiPainettu();
            }
        });

        tallennaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallennaReseptiPainettu();
            }
        });

        muutaGrammoiksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muutaGrammoiksiPainettu();
            }
        });

        skaalaaBtn.addActionListener(new java.awt.event.ActionListener() {
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
            double uusiTilavuus = Double.parseDouble(uusiTilavuusField.getText());
            String uusiYksikko = uusiTilavuusCombo.getSelectedItem().toString();
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

    public JScrollPane muutAineksetPanel() {

        nimiListat[2] = new ArrayList<JTextField>();
        maaraListat[2] = new ArrayList<JTextField>();
        yksikkoListat[2] = new ArrayList<JComboBox>();

        JPanel panel = new JPanel();
        panel = muutAineetPanel;
        ArrayList<Aines> muutAineksetLista = brewscale.getResepti().getMuutAinekset();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(new JLabel("Muut ainekset"));

        if (muutAineksetLista.size() == 0) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi(2));
            }
        } else {
            for (int i = 0; i < muutAineksetLista.size(); i++) {
                panel.add(luoUusiRivi(2));
                nimiListat[2].get(0).setText(muutAineksetLista.get(i).getNimi());
                maaraListat[2].get(0).setText(muutAineksetLista.get(i).getMaara() + "");
                yksikkoListat[2].get(0).setSelectedItem(muutAineksetLista.get(i).getYksikko());
            }
            panel.add(luoUusiRivi(2));
        }

        lisaaVikanRivinKuuntelijat(2);

        JScrollPane pane = new JScrollPane(panel);
        pane.setBorder(BorderFactory.createTitledBorder("Muut ainekset"));
        pane.setPreferredSize(new Dimension(400, 200));
        return pane;
    }

    public JScrollPane maltaatPanel() {

        nimiListat[0] = new ArrayList<JTextField>();
        maaraListat[0] = new ArrayList<JTextField>();
        yksikkoListat[0] = new ArrayList<JComboBox>();

        JPanel panel = new JPanel();
        panel = maltaatPanel;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(new JLabel("Maltaat"));

        ArrayList<Mallas> mallasLista = brewscale.getResepti().getMaltaat();

        if (mallasLista.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi(0));
            }
        } else {
            for (int i = 0; i < mallasLista.size(); i++) {
                panel.add(luoUusiRivi(0));
                nimiListat[0].get(0).setText(mallasLista.get(i).getNimi());
                maaraListat[0].get(0).setText(mallasLista.get(i).getMaara() + "");
                yksikkoListat[0].get(0).setSelectedItem(mallasLista.get(i).getYksikko());
            }
            panel.add(luoUusiRivi(0));
        }

        lisaaVikanRivinKuuntelijat(0);

        JScrollPane pane = new JScrollPane(panel);
        pane.setBorder(BorderFactory.createTitledBorder("Maltaat"));
        pane.setPreferredSize(new Dimension(400, 200));
        return pane;
    }

    public JScrollPane humalatPanel() {

        nimiListat[1] = new ArrayList<JTextField>();
        maaraListat[1] = new ArrayList<JTextField>();
        yksikkoListat[1] = new ArrayList<JComboBox>();

        JPanel panel = new JPanel();
        panel = humalatPanel;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(new JLabel("Humalat"));

        ArrayList<Humala> humalaLista = brewscale.getResepti().getHumalat();

        if (humalaLista.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                panel.add(luoUusiRivi(1));
            }
        } else {
            for (int i = 0; i < humalaLista.size(); i++) {
                panel.add(luoUusiRivi(1));
                nimiListat[1].get(0).setText(humalaLista.get(i).getNimi());
                maaraListat[1].get(0).setText(humalaLista.get(i).getMaara() + "");
                yksikkoListat[1].get(0).setSelectedItem(humalaLista.get(i).getYksikko());
                alphaMaaraLista.get(0).setText(humalaLista.get(i).getAlphaAcid() + "");
            }
            panel.add(luoUusiRivi(1));
        }

        lisaaVikanRivinKuuntelijat(1);

        JScrollPane pane = new JScrollPane(panel);
        pane.setBorder(BorderFactory.createTitledBorder("Humalat"));
        pane.setPreferredSize(new Dimension(450, 200));
        return pane;
    }

    private void lisaaVikanRivinKuuntelijat(int k) {
        vikanRivinKuuntelijat[k] = vikanRivinKuuntelija(k);
        nimiListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);
        maaraListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);
        yksikkoListat[k].get(0).addFocusListener(vikanRivinKuuntelijat[k]);
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

        if (i == 1) {
            alphaMaaraLista.add(0, new JTextField(4));
            uusiPaneeli.add(alphaMaaraLista.get(0));
            uusiPaneeli.add(new JLabel("%AA"));
        }

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

    /**
     * Lukee gui:sta uuden reseptin ja päivittää sen aktiiviseksi reseptiksi.
     */
    private void paivitaResepti() {

        double tilavuus = 0;
        try {
            tilavuus = Double.parseDouble(tilavuusField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Satsin koko ei kelpaa");
            return;
        }
        Resepti resepti = brewscale.getResepti();
        resepti.setNimi(nimiField.getText());
        resepti.setKoko(tilavuus);
        resepti.setKokoYksikko(tilavuusCombo.getSelectedItem().toString());

        resepti.tyhjennaAinekset();
        for (int i = 0; i < 3; i++) {
            for (int j = nimiListat[i].size() - 1; j > 0; j--) {
                String nimi = nimiListat[i].get(j).getText();
                String maara = maaraListat[i].get(j).getText();
                String yksikko = yksikkoListat[i].get(j).getSelectedItem().toString();
                if (!maara.isEmpty()) {
                    if (i == 0) {
                        resepti.lisaaMallas(nimi, maara, yksikko);
                    }
                    if (i == 1) {
                        String alpha = alphaMaaraLista.get(j).getText();
                        resepti.lisaaHumala(nimi, maara, yksikko, alpha);
                    }
                    if (i == 2) {
                        resepti.lisaaAines(nimi, maara, yksikko);
                    }
                }
            }
        }
        resepti.setOhje(ohjeetArea.getText());
        resepti.setMuistiinpanot(muistiinpanotArea.getText());
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
                for (int j = maaraListat[i].size() - 1; j > 0; j--) {
                    Double.parseDouble(maaraListat[i].get(j).getText());
                    if (i == 1) {
                        Double.parseDouble(alphaMaaraLista.get(j).getText());
                    }
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public JScrollPane ohjeetArea() {
        ohjeetArea = new JTextArea(brewscale.getResepti().getOhje());
        JScrollPane pane = new JScrollPane(ohjeetArea);
        pane.setBorder(BorderFactory.createTitledBorder("Ohjeet"));
        pane.setPreferredSize(new Dimension(450, 300));
        return pane;
    }

    public JScrollPane muistiinpanotArea() {
        muistiinpanotArea = new JTextArea(brewscale.getResepti().getMuistiinpanot());
        JScrollPane pane = new JScrollPane(muistiinpanotArea);
        pane.setBorder(BorderFactory.createTitledBorder("Muistiinpanoja"));
        pane.setPreferredSize(new Dimension(450, 200));
        return pane;
    }

}
