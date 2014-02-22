/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**OtsikkoPanel on JPanel-elementti, joka pitää sisällään otsikkokuvan. 
 *
 * @author Jari Haavisto
 */
public class OtsikkoPanel extends JPanel {

    /**
     * Luo JPanel-olion joka pitää sisällään otsikkokuvan
     */
    public OtsikkoPanel() {
        ImageIcon otsikkoImageIcon = null;
        try {
            otsikkoImageIcon = new ImageIcon("kuvat/brewscale_logo.png");

        } catch (NullPointerException exc) {
            System.out.println("Otsikkokuvaa ei löydy");
        }
        JLabel otsikkoLabel = new JLabel(otsikkoImageIcon, JLabel.CENTER);
        this.add(otsikkoLabel);

    }
}
