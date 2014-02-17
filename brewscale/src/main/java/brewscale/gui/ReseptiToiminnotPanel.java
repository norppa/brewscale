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

/**ReseptiToiminnotPanel on JPanel-elementti, joka pitää sisällään kontrollit reseptin
 * skaalaukseen ja grammoiksi muuttamiseen.
 *
 * @author Jari Haavisto
 */
public class ReseptiToiminnotPanel extends JPanel {

    public ReseptiToiminnotPanel(Komponentit k) {
        k.muutaGrammoiksiBtn.setText("Muuta resepti grammoiksi");
        k.uusiTilavuusField.setText(" ");
        k.uusiTilavuusField.setColumns(5);
        k.uusiTilavuusCombo.removeAllItems();
        k.uusiTilavuusCombo.addItem("l");
        k.uusiTilavuusCombo.addItem("gal");
        k.skaalaaBtn.setText("Skaalaa");
        
        this.add(k.muutaGrammoiksiBtn);
        this.add(new JLabel("Skaala resepti kokoon"));
        this.add(k.uusiTilavuusField);
        this.add(k.uusiTilavuusCombo);
        this.add(k.skaalaaBtn);
    }
}
