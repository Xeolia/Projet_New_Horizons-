package Vue;

import Controleur.Controleur;
import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class FenetreDiscussion extends JFrame {
    PanelDiscussion panelDiscussion;
    //JScrollPane scrollPane = new JScrollPane(panelCentre);

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

    public static void main(String[] args) {
        Singletons.getFenetreDiscussion();
        Singletons.getControleur();

    }

}
