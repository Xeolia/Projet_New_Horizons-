package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class MaFenetre extends JFrame {

    public MaFenetre() {
        setTitle("Chat Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(new Dimension(500, 300));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        this.getContentPane().add(Singletons.getPanelFond(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Singletons.getMaFenetre();
        Singletons.getControleur();
    }
}
