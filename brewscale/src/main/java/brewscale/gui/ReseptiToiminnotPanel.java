/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jtthaavi@cs
 */
public class ReseptiToiminnotPanel extends JPanel {

    public ReseptiToiminnotPanel(JButton muutaGrammoiksiBtn, JTextField uusiTilavuusField, 
            JComboBox uusiTilavuusCombo, JButton skaalaaBtn) {
        muutaGrammoiksiBtn.setText("Muuta resepti grammoiksi");
        uusiTilavuusField.setText(" ");
        uusiTilavuusField.setColumns(5);
        uusiTilavuusCombo.removeAllItems();
        uusiTilavuusCombo.addItem("l");
        uusiTilavuusCombo.addItem("gal");
        skaalaaBtn.setText("Skaalaa");
        
        this.add(muutaGrammoiksiBtn);
        this.add(new JLabel("Skaala resepti kokoon"));
        this.add(uusiTilavuusField);
        this.add(uusiTilavuusCombo);
        this.add(skaalaaBtn);
    }

//    public JPanel konversioPanel() {
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Skaalaa resepti kokoon"));
//        uusiTilavuusField = new JTextField(" ", 5);
//        panel.add(uusiTilavuusField);
//        uusiTilavuusCombo = new JComboBox(tilavuusLista);
//        panel.add(uusiTilavuusCombo);
//        JButton skaalaaBtn = new JButton("Skaalaa!");
//        skaalaaBtn.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                skaalaaResepti();
//            }
//        });
//
//        panel.add(skaalaaBtn);
//
////        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
//        return panel;
//    }
//    
//        private JPanel reseptiToiminnotPanel() {
//        JPanel reseptiToiminnot = new JPanel();
//
//        JButton muutaGrammoiksiBtn = new JButton("Muuta resepti grammoiksi");
//        muutaGrammoiksiBtn.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                muutaGrammoiksiPainettu();
//            }
//        });
//
//        reseptiToiminnot.add(muutaGrammoiksiBtn);
//        reseptiToiminnot.add(konversioPanel());
//        return reseptiToiminnot;
//    }
}
