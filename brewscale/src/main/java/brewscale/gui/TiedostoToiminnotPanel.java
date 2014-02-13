/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brewscale.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jtthaavi@cs
 */
public class TiedostoToiminnotPanel extends JPanel {
    
    public TiedostoToiminnotPanel(JButton uusi, JButton avaa, JButton tallenna) {
        uusi.setText("Uusi Resepti");
        avaa.setText("Avaa Resepti");
        tallenna.setText("Tallenna Resepti");
        this.add(uusi);
        this.add(avaa);
        this.add(tallenna);
    }
    
}
