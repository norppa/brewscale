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

    /**
     * Ohjelman käyttämät tilavuudet ja painot.
     */
    public final String[] tilavuusLista = new String[]{"l", "gal"},
            painoLista = new String[]{"g", "oz", "lbs"};
    /**
     * Aktiivinen resepti.
     */
    public Resepti resepti;
    /**
     * Ohjelmassa esillä olevat nappulat.
     */
    public JButton uusiBtn, avaaBtn, tallennaBtn, muutaGrammoiksiBtn, skaalaaBtn;
    /**
     * Ohjelmassa näkyvät tekstikentätä reseptin nimelle, tilavuudelle ja
     * skaalattaessa tarvittavalle uudelle tilavuudelle.
     */
    public JTextField nimiField, tilavuusField, uusiTilavuusField;
    /**
     * Ohjelmassa näkyvät pudotusvalikot tilavuuden ja uuden tilavuuden yksiköille.
     */
    public JComboBox tilavuusCombo, uusiTilavuusCombo;
    /**
     * TextAreat, joihin kirjoitetaan reseptin ohje ja muistiinpanot.
     */
    public JTextArea ohjeetArea, muistiinpanotArea;
    /**
     * Kolmipaikkainen (maltaat, humalat ja muut) array, joka pitää sisällään 
     * ArrayList-olioita, joihin puolestaan on tallennettu ohjelmassa näkyvät 
     * tekstikentät raaka-aineiden nimille ja määrille.
     */
    public ArrayList<JTextField>[] nimiListat, maaraListat;
    /**
     * Lista tekstikentistä, joihin syötetään humalien alphahappopitoisuudet.
     */
    public ArrayList<JTextField> alphaMaaraLista;
    /**
     * Kolmipaikkainen (maltaat, humalat, muut) array, joka pitää sisällään
     * ArrayList-olioita, joihin puolestaan on tallennettu ohjelmassa näkyvät
     * tiputusvalikot raaka-aineiden yksiköille.
     */
    public ArrayList<JComboBox>[] yksikkoListat;
    /**
     * Raaka-ainelistojen viimeisillä riveillä olevat kuuntelijat, jotka pitävät
     * huolen siitä, että uusi rivi luodaan tarvittaessa.
     */
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
