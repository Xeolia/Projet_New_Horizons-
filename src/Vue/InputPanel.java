package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    JTextField textField;
    JButton button;

    public InputPanel(){
        textField = new JTextField();
        button = new JButton("Envoyer");
        this.setLayout(new BorderLayout());
        add(textField, BorderLayout.CENTER) ;
        add(button, BorderLayout.EAST) ;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getButton() {
        return button;
    }
    public void enregistreEcouteur(Controleur parControleur){
        button.addActionListener(parControleur);
        button.setActionCommand("envoiMessage");
    }
}
