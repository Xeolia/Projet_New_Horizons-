package Vue;

import javax.swing.*;
import java.awt.*;

public class PanelError extends JPanel {
    JLabel labelError = new JLabel("Erreur, les mots de passe ne correspondent pas");
    public PanelError(){
        this.setLayout(new BorderLayout());
        labelError.setForeground(Color.red);
        this.add(labelError, BorderLayout.CENTER);
    }

    public JLabel getLabelError() {
        return labelError;
    }
}
