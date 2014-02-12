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

    private void initGUI(Container c) {

        JPanel toimintoPanel = new JPanel();
        toimintoPanel.setLayout(new BoxLayout(toimintoPanel, BoxLayout.PAGE_AXIS));

        toimintoPanel.add(tiedostoToiminnotPanel());
        toimintoPanel.add(reseptiToiminnotPanel());

        JScrollPane maltaatPanel = maltaatPanel();
        JScrollPane humalatPanel = humalatPanel();
        JScrollPane muutAineksetPanel = muutAineksetPanel();

        JPanel uusiResepti = new JPanel();
        JLabel uusiReseptiLabel = new JLabel("uusi resepti");
        uusiResepti.add(uusiReseptiLabel);

        JPanel reseptiPanel = new JPanel();
        reseptiPanel.setLayout(new BoxLayout(reseptiPanel, BoxLayout.LINE_AXIS));
        JPanel reseptiAineksetPanel = new JPanel();
        reseptiAineksetPanel.setLayout(new BoxLayout(reseptiAineksetPanel, BoxLayout.PAGE_AXIS));
        JPanel reseptiMuutPanel = new JPanel();
        reseptiMuutPanel.setLayout(new BoxLayout(reseptiMuutPanel, BoxLayout.PAGE_AXIS));
        reseptiPanel.add(reseptiAineksetPanel);
        reseptiPanel.add(reseptiMuutPanel);

        reseptiMuutPanel.add(reseptiNimiPanel());
        reseptiMuutPanel.add(reseptinKokoPanel());
        reseptiMuutPanel.add(ohjeetArea());
        reseptiMuutPanel.add(muistiinpanotArea());
        reseptiAineksetPanel.add(maltaatPanel);
        reseptiAineksetPanel.add(humalatPanel);
        reseptiAineksetPanel.add(muutAineksetPanel);

        panel = new JPanel();
        this.getFrame().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(otsikkoLabel());
        panel.add(toimintoPanel);
        panel.add(reseptiPanel);
        panel.setPreferredSize(new Dimension(1000, 850));

    }

    public JFrame getFrame() {
        return frame;
    }

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
    }

    private void uudistaNakyma() {
        alustaMuuttujat();
        frame.remove(panel);
        initGUI(frame.getContentPane());
        frame.revalidate();
        frame.repaint();
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
        paivitaResepti();
        uudistaNakyma();
        brewscale.tallenna();
    }

    private void skaalaaResepti() {
        double uusiTilavuus = Double.parseDouble(uusiTilavuusField.getText());
        String uusiYksikko = uusiTilavuusCombo.getSelectedItem().toString();
        brewscale.skaalaa(uusiTilavuus, uusiYksikko);

        uudistaNakyma();
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
        resepti.setKoko(tilavuus);
        resepti.setKokoYksikko(tilavuusCombo.getSelectedItem().toString());

        resepti.tyhjennaAinekset();
        for (int i = 0; i < 3; i++) {
            for (int j = nimiListat[i].size() - 1; j > 0; j--) {
                String nimi = nimiListat[i].get(j).getText();
                String maara = maaraListat[i].get(j).getText();
                if (maara.isEmpty()) {
                    maara = "0";
                }
                String yksikko = yksikkoListat[i].get(j).getSelectedItem().toString();
                if (!nimi.equals("") || !maara.equals("0")) {
                    if (i == 0) {
                        resepti.lisaaMallas(nimi, maara, yksikko);
                    }
                    if (i == 1) {
                        String alpha = alphaMaaraLista.get(j).getText();
                        System.out.println(" alpha on " + alpha);
                        resepti.lisaaHumala(nimi, maara, yksikko, alpha);
                    }
                    if (i == 2) {
                        resepti.lisaaAines(nimi, maara, yksikko);
                    }
                }
            }
        }
        resepti.setOhje(ohjeetArea.getText());
    }

    private void muotoileTyhjatKentat(int k, String nimi, String maara) {
        if (maara.isEmpty()) {
            maara = "0";
        }

        if (nimi.isEmpty()) {
            if (k == 0) {
                maara = "nimetön mallas";
            }
            if (k == 1) {
                maara = "nimetön humala";
            }
            if (k == 2) {
                maara = "nimetön aines";
            }
        }
    }

    public JLabel otsikkoLabel() {
        ImageIcon otsikkoImageIcon = null;
        try {
            otsikkoImageIcon = new ImageIcon(getClass().getResource("brewscale_logo.png"));

        } catch (NullPointerException exc) {
            System.out.println("Otsikkokuvaa ei löydy");
        }
        JLabel otsikkoLabel = new JLabel(otsikkoImageIcon, JLabel.CENTER);
        return otsikkoLabel;
    }

    public JPanel tiedostoToiminnotPanel() {
        JPanel tiedostoNappulat = new JPanel();

        JButton uusiReseptiBtn = new JButton("Uusi resepti");
        uusiReseptiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiReseptiPainettu();
            }
        });

        JButton avaaReseptiBtn = new JButton("Avaa resepti");
        avaaReseptiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaaReseptiPainettu();
            }
        });

        JButton tallennaReseptiBtn = new JButton("Tallenna resepti");
        tallennaReseptiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallennaReseptiPainettu();
            }
        });

        tiedostoNappulat.add(uusiReseptiBtn);
        tiedostoNappulat.add(avaaReseptiBtn);
        tiedostoNappulat.add(tallennaReseptiBtn);

        return tiedostoNappulat;
    }

    private JPanel reseptiToiminnotPanel() {
        JPanel reseptiToiminnot = new JPanel();

        JButton muutaGrammoiksiBtn = new JButton("Muuta resepti grammoiksi");
        muutaGrammoiksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muutaGrammoiksiPainettu();
            }
        });

        reseptiToiminnot.add(muutaGrammoiksiBtn);
        reseptiToiminnot.add(konversioPanel());
        return reseptiToiminnot;
    }

    public JPanel reseptiNimiPanel() {
        nimiField = new JTextField(brewscale.getResepti().getNimi(), 20);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Reseptin nimi: "));
        panel.add(nimiField);
        return panel;
    }

    public JPanel reseptinKokoPanel() {
        JPanel reseptinKokoPanel = new JPanel();
        reseptinKokoPanel.add(new JLabel("Satsin koko:"));
        tilavuusField = new JTextField(brewscale.getResepti().getKoko() + "", 5);
        tilavuusCombo = new JComboBox(tilavuusLista);
        reseptinKokoPanel.add(tilavuusField);
        reseptinKokoPanel.add(tilavuusCombo);

//        reseptinKokoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return reseptinKokoPanel;
    }

    public JPanel konversioPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Skaalaa resepti kokoon"));
        uusiTilavuusField = new JTextField(" ", 5);
        panel.add(uusiTilavuusField);
        uusiTilavuusCombo = new JComboBox(tilavuusLista);
        panel.add(uusiTilavuusCombo);
        JButton skaalaaBtn = new JButton("Skaalaa!");
        skaalaaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Skaalaa-nappia painettu");
                skaalaaResepti();
            }
        });

        panel.add(skaalaaBtn);

//        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return panel;
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
