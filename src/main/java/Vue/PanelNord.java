package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelNord extends JPanel {
    JLabel labelDiscussion = new JLabel("Discussion");
    JButton button;


    public PanelNord() {
        labelDiscussion.setFont(new Font(" ", Font.ITALIC, 15));
        setLayout(new GridLayout(1, 2));
        button = new JButton("Ajouter");
        add(labelDiscussion, BorderLayout.NORTH);
        add(button,BorderLayout.NORTH);

    }
    public void enregistreEcouteur(Controleur parControleur){
        button.addActionListener(parControleur);
        button.setActionCommand("Discussion");
    }

}
