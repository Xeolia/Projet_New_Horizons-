package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

/**
 * La classe FrameError permet de définir la frame d'erreur de l'applicationi
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class FrameError extends JFrame {

    /**
     * Constructeur de la classe FrameError
     */
    public FrameError(){
        this.setSize(new Dimension(300, 150));
        this.setTitle("Error");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setVisible(true);
        this.add(Singletons.getPanelError());
    }
}
