package Vue;

import javax.swing.*;
import java.awt.*;

public class PanelDiscussion extends JPanel {

    JPanel panelNord;
    JPanel panelCentre;

    public PanelDiscussion() {
        panelNord = new PanelNord();
        panelCentre = new PanelCentre();
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;
        this.setLayout(new BorderLayout());
        add(panelNord, BorderLayout.NORTH);
       // add(panelCentre, BorderLayout.CENTER);
        setVisible(true);


    }
    public JPanel getPanelNord() {
        return panelNord;
    }
    public JPanel getPanelCentre() {
        return panelCentre;
    }

}
