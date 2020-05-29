package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class PanelDiscussion extends JPanel {
    private JPanel listContainer;
    JPanel panelNord;
    JPanel panelCentre;


    public PanelDiscussion() {

        panelNord = Singletons.getPanelNord();
        panelCentre = Singletons.getPanelCentre();
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


}
