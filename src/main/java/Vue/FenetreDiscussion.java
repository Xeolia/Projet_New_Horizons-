package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class FenetreDiscussion extends JFrame {
    PanelDiscussion panelDiscussion;
    JPanel panelNord =new JPanel();;
    JPanel panelCentre = new JPanel();
    PanelDiscussion fenetreDiscussion = new PanelDiscussion();
    //JScrollPane scrollPane = new JScrollPane(panelCentre);

    FenetreDiscussion() {

        panelDiscussion = new PanelDiscussion();
        //panelNord = panelDiscussion.getPanelNord();
       // panelCentre = panelDiscussion.getPanelCentre();
        this.setTitle("hello");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
       // this.add(blackButton,BorderLayout.NORTH);
       // this.add(redButton,BorderLayout.SOUTH);
        this.add(panelDiscussion);

        //on cree le JPanel principal
        panelDiscussion.setLayout(new OverlayLayout(panelDiscussion));
        panelDiscussion.getPanelCentre();
        //on y ajoute les autres JPanel
       // pan.add(blackPan);
       // pan.add(redPan);
        //blackPan.setBackground(Color.black);
       // redPan.setBackground(Color.red);

        //fenetreDiscussion.panelCentre.add(panelNord, BorderLayout.CENTER);
        //fenetreDiscussion.getContentPane().add(panelCentre, BorderLayout.CENTER);
       // fenetreDiscussion.getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new FenetreDiscussion();
    }

}
