/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brewscale.gui;

import brewscale.resepti.Resepti;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Komponentit pitää sisällään kaikki käyttöliittymässä olevat kentät.
 *
 * @author Jari Haavisto
 */
public class Komponentit {

    public final String[] tilavuusLista = new String[]{"l", "gal"},
            painoLista = new String[]{"g", "oz", "lbs"};
    public Resepti resepti;
    public JButton uusiBtn, avaaBtn, tallennaBtn, muutaGrammoiksiBtn, skaalaaBtn;
    public JTextField nimiField, tilavuusField, uusiTilavuusField;
    public JComboBox tilavuusCombo, uusiTilavuusCombo;
    public JTextArea ohjeetArea, muistiinpanotArea;
    public ArrayList<JTextField>[] nimiListat, maaraListat;
    public ArrayList<JTextField> alphaMaaraLista;
    public ArrayList<JComboBox>[] yksikkoListat;
    public FocusListener[] vikanRivinKuuntelijat;

    public Komponentit(Resepti resepti) {
        this.resepti = resepti;

        uusiBtn = new JButton();
        avaaBtn = new JButton();
        tallennaBtn = new JButton();
        muutaGrammoiksiBtn = new JButton();
        skaalaaBtn = new JButton();

        nimiField = new JTextField();
        tilavuusField = new JTextField();
        tilavuusCombo = new JComboBox();
        uusiTilavuusField = new JTextField();
        uusiTilavuusCombo = new JComboBox();

        ohjeetArea = new JTextArea();
        muistiinpanotArea = new JTextArea();

        nimiListat = new ArrayList[3];
        maaraListat = new ArrayList[3];
        yksikkoListat = new ArrayList[3];
        vikanRivinKuuntelijat = new FocusListener[3];
        
        alphaMaaraLista = new ArrayList<JTextField>();
    }
}
