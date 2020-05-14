package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class maFenetre extends JFrame {
    ChatPanel chatPanel;
    InputPanel inputPanel;
    Controleur controleur;

    public maFenetre() {
        chatPanel = new ChatPanel();
        inputPanel = chatPanel.getInputPanel();
        setTitle("Chat Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        this.getContentPane().add(chatPanel, BorderLayout.CENTER);
        controleur = new Controleur(chatPanel, inputPanel);
    }

    public static void main(String[] args) {
        new maFenetre();
    }

}
