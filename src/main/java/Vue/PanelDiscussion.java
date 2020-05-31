package Vue;

import Controleur.Controleur;
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
     * Panel se trouvant en haut
     * @see PanelNord
     */
    PanelNord panelNord;

    /**
     * La liste des discussion de l'utilisateur
     * @see ListeDiscussion
     */
    ListeDiscussion ListeDiscussions;

    /**
     * taille de bordure
     */
    protected int strokeSize = 1;

    /**
     * Couleur de l'ombre
     * @see Color
     */
    protected Color shadowColor = Color.black;

    /**
     * Initialisation de la variable shady à true
     */
    protected boolean shady = true;

    /**
     * Initialisation de la variable highQuality à true
     */
    protected boolean highQuality = true;

    /**
     * Initialisation de la dimension arcs a 20,20
     */
    protected Dimension arcs = new Dimension(20, 20);

    /**
     * Initialisation de l'écart d'ombre à 5
     */
    protected int shadowGap = 5;

    /**
     * Initialisation du décallage de l'ombre à 4
     */
    protected int shadowOffset = 4;

    /**
     * Initialisation de la variable alpha de l'ombre a 150
     */
    protected int shadowAlpha = 150;
    JButton deconnexionButton;

    /**
     * Constructeur de la classe JPaPanelDiscussionnel
     */
    public PanelDiscussion() {

        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(600,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);
        panelNord = Singletons.getPanelNord();
        ListeDiscussions = Singletons.getListeDiscussion();
        deconnexionButton = new JButton("Deconnexion");
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        add(ListeDiscussions, BorderLayout.CENTER);
        add(panelNord, BorderLayout.NORTH);
        add(deconnexionButton, BorderLayout.SOUTH);
        setVisible(true);

        add(Singletons.getListeDiscussion());
    }

    /**
     * Cette méthode permet de mettre d'attribuer un ActionListener au bouton
     * @param controleur le controleur qui appliquera des réactions après les actions sur le bouton
     */
    public void enregistreEcouteur(Controleur controleur) {
        deconnexionButton.addActionListener(controleur);
        deconnexionButton.setActionCommand("deconnexion");
    }

    /**
     * Cette méthode permet de changer l'aspect de l'interface
     * @param g type de graphique a utiliser
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        graphics.setStroke(new BasicStroke());
    }
}
