package Vue;

import Modele.Singletons;

import javax.swing.*;

/**
 * La classe PanelOnglet permet de définir le panel d'onglet
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class PanelOnglet extends JTabbedPane {

    /**
     * Constructeur de la classe PanelOnglet
     */
    public PanelOnglet(){
        this.addTab("Connexion", Singletons.getConnexionPanel());
        this.addTab("Inscription", Singletons.getInscriptionPanel());
        this.setVisible(true);
    }
}
