package Vue;

import Controleur.Controleur;
import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

/**
 * La classe FenetreDiscussion permet de définir la JFrame de discussion de l'application
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class FenetreDiscussion extends JFrame {

    /**
     * Panel de discussion
     * @see PanelDiscussion
     */
    PanelDiscussion panelDiscussion;
    //JScrollPane scrollPane = new JScrollPane(panelCentre);

    /**
     * Constructeur de la classe FenetreDiscussion
     */
    public FenetreDiscussion() {
        panelDiscussion = Singletons.getPanelDiscussion();
        this.setTitle("hello");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panelDiscussion);
        this.repaint();
        this.revalidate();
    }

    /**
     * Cette méthode permet lancer lancer l'application sur la fenêtre discussion
     * @param args
     */
    public static void main(String[] args) {
        Singletons.getFenetreDiscussion();
        Singletons.getControleur();
    }

}
