package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class FrameError extends JFrame {
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
