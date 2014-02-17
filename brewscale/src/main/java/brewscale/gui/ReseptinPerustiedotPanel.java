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

/**ReseptinPerustiedotPanel on JPanel-elementti, joka pitää sisällään reseptin nimen ja koon.
 *
 * @author Jari Haavisto
 */
public class ReseptinPerustiedotPanel extends JPanel {

    public ReseptinPerustiedotPanel(Komponentit k) {
        JPanel nimiPanel = new JPanel();
        k.nimiField.setText(k.resepti.getNimi());
        k.nimiField.setColumns(20);
        nimiPanel.add(new JLabel("Reseptin nimi: "));
        nimiPanel.add(k.nimiField);

        JPanel kokoPanel = new JPanel();
        kokoPanel.add(new JLabel("Satsin koko:"));
        k.tilavuusField.setText(k.resepti.getKoko() + "");
        k.tilavuusField.setColumns(5);
        k.tilavuusCombo.removeAllItems();
        k.tilavuusCombo.addItem("l");
        k.tilavuusCombo.addItem("gal");
        k.tilavuusCombo.setSelectedItem(k.resepti.getKokoYksikko());
        kokoPanel.add(k.tilavuusField);
        kokoPanel.add(k.tilavuusCombo);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(nimiPanel);
        this.add(kokoPanel);
    }
}
