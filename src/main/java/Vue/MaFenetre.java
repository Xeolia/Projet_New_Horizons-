package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class MaFenetre extends JFrame {

    public MaFenetre() {
        setTitle("New horizon app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 850));
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        this.getContentPane().add(Singletons.getPanelFond(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Singletons.getMaFenetre();
        Singletons.getControleur();
        Singletons.getMaFenetre().repaint();
        Singletons.getMaFenetre().revalidate();
    }
}
