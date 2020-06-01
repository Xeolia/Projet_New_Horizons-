package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

/**
 * La classe MaFenetre permet de lancer l'interface de l'application
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class MaFenetre extends JFrame {

    /**
     * Constructeur de la classe MaFenetre
     */
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

    /**
     * Cette méthode permet de mettre en marche le serveur
     * @param args arguments de la méthode main
     */
    public static void main(String[] args) {
        Singletons.getMaFenetre();
        Singletons.getControleur();
        Singletons.getMaFenetre().repaint();
        Singletons.getMaFenetre().revalidate();
    }
}
