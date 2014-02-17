/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brewscale.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**TiedostoToiminnotPanel on JPanel-elementti, joka pitää sisällään "Uusi Resepti", 
 * "Avaa Resepti" ja "Tallenna Resepti" -nappulat. 
 * 
 *
 * @author Jari Haavisto
 */
public class TiedostoToiminnotPanel extends JPanel {
    
    public TiedostoToiminnotPanel(Komponentit k) {
        k.uusiBtn.setText("Uusi Resepti");
        k.avaaBtn.setText("Avaa Resepti");
        k.tallennaBtn.setText("Tallenna Resepti");
        this.add(k.uusiBtn);
        this.add(k.avaaBtn);
        this.add(k.tallennaBtn);
    }
    
}
