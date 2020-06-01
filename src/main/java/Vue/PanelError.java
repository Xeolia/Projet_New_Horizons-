package Vue;

import javax.swing.*;
import java.awt.*;


/**
 * La classe PanelError permet de définir le panel erreur de l'applicationi
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class PanelError extends JPanel {
    /**
     * Label de l'erreur
     */
    JLabel labelError = new JLabel("Erreur, les mots de passe ne correspondent pas");

    /**
     * Constructeur de la classe PanelError
     */
    public PanelError(){
        this.setLayout(new BorderLayout());
        labelError.setForeground(Color.red);
        this.add(labelError, BorderLayout.CENTER);
    }

    /**
     * Cette méthode permet de récupérer le label de l'erreur
     * @return l'objet labelError
     */
    public JLabel getLabelError() {
        return labelError;
    }
}
