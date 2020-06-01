package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

/**
 * La classe InputPanel permet de définir le panel d'entrer de l'application
 *
 * @author Tanguy Bénard, Alexia Serrier
 * @version 1.0
 */
public class InputPanel extends JPanel {

    /**
     * Champs d'écriture
     * @see JTextField
     */
    JTextField textField;

    /**
     * Bouton envoyé
     * @see JButton
     */
    JButton button;

    /**
     * Constructeur de la classe InputPanel
     */
    public InputPanel(){
        textField = new JTextField();
        button = new JButton("Envoyer");
        this.setLayout(new BorderLayout());
        add(textField, BorderLayout.CENTER) ;
        add(button, BorderLayout.EAST) ;
    }

    /**
     * Permet de récupérer le champs
     * @return l'objet textField
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Permet de récupérer le bouton
     * @return l'objet button
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Permet de mettre d'attribuer un ActionListener au bouton
     * @param parControleur le controleur qui appliquera des réactions apres les actions sur le bouton
     */
    public void enregistreEcouteur(Controleur parControleur){
        button.addActionListener(parControleur);
        button.setActionCommand("envoiMessage");
    }
}
