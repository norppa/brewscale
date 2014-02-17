/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brewscale.gui;

import brewscale.resepti.Resepti;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**MuistiinpanotPanel on JScrollPane-elementti, joka pitää sisällään reseptin muistiinpanot.
 *
 * @author Jari Haavisto
 */
public class MuistiinpanotPanel extends JScrollPane {
    
      public MuistiinpanotPanel(Komponentit k) {
        k.muistiinpanotArea.setText(k.resepti.getMuistiinpanot());
        this.setViewportView(k.muistiinpanotArea);
        this.setBorder(BorderFactory.createTitledBorder("Muistiinpanoja"));
        this.setPreferredSize(new Dimension(450, 200));
      }
    
}
