/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.brewscale.Brewscale;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class BrewscaleGUI implements Runnable {

    private JFrame frame;

    public BrewscaleGUI(Brewscale brewscale) {
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

        Elements e = new Elements();

        ImageIcon otsikko = null;
        try {
            otsikko = new ImageIcon(getClass().getResource("./brewscale_logo.png"));
        } catch (NullPointerException exc) {
            System.out.println("Otsikkokuvaa ei löydy");
        }
        JLabel otsikkoLabel = new JLabel(otsikko);

        JPanel nappulat = new JPanel();
        JButton avaaReseptiBtn = new JButton("Avaa resepti");
        JButton uusiReseptiBtn = new JButton("Uusi resepti");
        nappulat.add(avaaReseptiBtn);
        nappulat.add(uusiReseptiBtn);

        JScrollPane maltaat = e.maltaatPanel();
        JScrollPane humalat = e.humalatPanel();
        
        JPanel koko = e.reseptinKokoPanel();

        JPanel konversio = new JPanel();
        JLabel label5 = new JLabel("Konversio");
        konversio.add(label5);

        JPanel uusiResepti = new JPanel();
        JLabel uusiReseptiLabel = new JLabel("uusi resepti");
        uusiResepti.add(uusiReseptiLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        this.getFrame().add(panel);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(otsikkoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(nappulat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(koko, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(maltaat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(humalat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(konversio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(uusiResepti, gbc);

    }

    public JFrame getFrame() {
        return frame;
    }
}
