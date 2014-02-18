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
                    String numero = k.maaraListat[i].get(j).getText();
                    if (!numero.equals("")) {
                        Double.parseDouble(numero);
                    }
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
