package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class FenetreDiscussion extends JFrame {
    PanelDiscussion panelDiscussion;
    //JScrollPane scrollPane = new JScrollPane(panelCentre);

    FenetreDiscussion() {
        panelDiscussion = new PanelDiscussion();
        JPanel panelNord = panelDiscussion.getPanelNord();
        JPanel panelCentre = panelDiscussion.getPanelCentre();
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
        new FenetreDiscussion();

    }

}
