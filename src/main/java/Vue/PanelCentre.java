package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * La classe PanelCentre permet de définir le panel ce trouvant au centre du panel de discussion
 *
 * @author Alexia Serrier, Tanguy Bénard
 * @version 1.0
 */

public class PanelCentre extends JPanel {

    /**
     * Ce Panel correspond a la quatrième discussion
     */
    JPanel quatriemeDiscussion= new JPanel();

    /**
     * Ce Panel correspond a la première discussion
     */
    JPanel premiereDiscussion = new JPanel();

    /**
     * Ce Panel correspond a la deuxième discussion
     */
    JPanel deuxiemeDiscussion = new JPanel();

    /**
     * Ce Panel correspond a la troisième discussion
     */
    JPanel troisiemeDiscussion = new JPanel();

    /**
     * Cette méthode permet de récupérer le panel première discussion
     * @return le panel de la premiere discussion
     */
    public JPanel getPremiereDiscussion() {
        return premiereDiscussion;
    }

    /**
     * Cette méthode permet de récupérer le panel deuxième discussion
     * @return le panel de la deuxième discussion
     */
    public JPanel getDeuxiemeDiscussion() {
        return deuxiemeDiscussion;
    }

    /**
     * Cette méthode permet de récupérer le panel troisième discussion
     * @return le panel de la troisième discussion
     */
    public JPanel getTroisiemeDiscussion() {
        return troisiemeDiscussion;
    }

    /**
     * Constructeur de la classe PanelCentre
     */
    public PanelCentre() {
        premiereDiscussion.setBackground(Color.cyan);
        deuxiemeDiscussion.setBackground(Color.red);
        troisiemeDiscussion.setBackground(Color.gray);
        quatriemeDiscussion.setBackground(Color.blue);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(10, 1,30,50));
        this.add(premiereDiscussion);
        this.add(deuxiemeDiscussion);
        this.add(troisiemeDiscussion);
        this.add(quatriemeDiscussion);


    }

}
