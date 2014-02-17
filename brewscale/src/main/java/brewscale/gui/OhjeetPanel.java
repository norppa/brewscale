/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**OhjeetPanel on JScrollPane-elementti, joka pitää sisällään reseptin ohjeen.
 *
 * @author Jari Haavisto
 */
public class OhjeetPanel extends JScrollPane {

    public OhjeetPanel(Komponentit k) {
        k.ohjeetArea.setText(k.resepti.getOhje());
        this.setViewportView(k.ohjeetArea);
        this.setBorder(BorderFactory.createTitledBorder("Ohjeet"));
        this.setPreferredSize(new Dimension(450, 300));
    }

}
