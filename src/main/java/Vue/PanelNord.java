package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

/**
 * La classe PanelNord permet de définir le panel ce trouvant en haut de l'écran du panel de discussion
 *
 * @author Alexia Serrier, Tanguy Bénard
 * @version 1.0
 */

public class PanelNord extends JPanel {

    /**
     * Label de discussion
     */
    JLabel labelDiscussion = new JLabel("Discussion");

    /**
     * Bouton
     */
    JButton button;

    public JButton getButton() {
        return button;
    }

    /**
     * Constructeur de la classe PanelNord
     */
    public PanelNord() {
        labelDiscussion.setFont(new Font(" ", Font.ITALIC, 15));
        setLayout(new GridLayout(1, 2));
        button = new JButton("Ajouter");
        add(labelDiscussion, BorderLayout.NORTH);
        add(button,BorderLayout.NORTH);

    }

    /**
     * Cette méthode permet de lié un ActionListener avec un controleur
     * @param parControleur le controleur qui gère les actions
     */
    public void enregistreEcouteur(Controleur parControleur){
        button.addActionListener(parControleur);
        button.setActionCommand("discussion");
    }
}
