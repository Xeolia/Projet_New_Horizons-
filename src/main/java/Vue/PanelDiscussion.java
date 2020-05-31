package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

/**
 * La classe PanelDiscussion permet de définir le panel discussion de l'application
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class PanelDiscussion extends JPanel {

    /**
     * Le panel contenant la liste de message
     * @see JPanel
     */
    private JPanel listContainer;

    /**
     * Panel se trouvant en haut
     * @see PanelNord
     */
    JPanel panelNord;

    /**
     * Panel se trouvant au centre
     * @see PanelCentre
     */
    JPanel panelCentre;


    /**
     * Constructeur de la classe JPanel
     */
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
