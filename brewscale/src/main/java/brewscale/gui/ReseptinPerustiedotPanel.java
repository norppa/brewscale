/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.resepti.Resepti;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jtthaavi@cs
 */
public class ReseptinPerustiedotPanel extends JPanel {

    public ReseptinPerustiedotPanel(Resepti resepti, JTextField nimiField, JTextField tilavuusField, JComboBox tilavuusCombo) {
        JPanel nimiPanel = new JPanel();
        nimiField.setText(resepti.getNimi());
        nimiField.setColumns(20);
        nimiPanel.add(new JLabel("Reseptin nimi: "));
        nimiPanel.add(nimiField);

        JPanel kokoPanel = new JPanel();
        kokoPanel.add(new JLabel("Satsin koko:"));
        tilavuusField.setText(resepti.getKoko() + "");
        tilavuusField.setColumns(5);
        tilavuusCombo.removeAllItems();
        tilavuusCombo.addItem("l");
        tilavuusCombo.addItem("gal");
        kokoPanel.add(tilavuusField);
        kokoPanel.add(tilavuusCombo);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(nimiPanel);
        this.add(kokoPanel);
    }
}
