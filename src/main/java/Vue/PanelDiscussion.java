package Vue;

import javax.swing.*;
import java.awt.*;

public class PanelDiscussion extends JPanel {

    JPanel panelNord;
    JPanel panelCentre;


    public PanelDiscussion() {
        panelNord = new PanelNord();
        panelCentre = new PanelCentre();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        add(panelCentre, BorderLayout.CENTER);
        add(panelNord, BorderLayout.NORTH);
        setVisible(true);
        JScrollPane pane = new JScrollPane(panelCentre);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
        pane.setViewportView(panelCentre);
        pane.revalidate();

    }

    public JPanel getPanelNord() {
        return panelNord;
    }

    public JPanel getPanelCentre() {
        return panelCentre;
    }

}
