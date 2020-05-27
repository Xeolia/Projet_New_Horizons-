package Vue;

import Modele.Singletons;

import javax.swing.*;

public class PanelOnglet extends JTabbedPane {
    public PanelOnglet(){
        this.addTab("Connexion", Singletons.getConnexionPanel());
        this.addTab("Inscription", Singletons.getInscriptionPanel());
        this.setVisible(true);
    }
}
