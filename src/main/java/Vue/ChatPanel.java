package Vue;

import Controleur.Controleur;
import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

/**
 * La classe ChatPanel permet de définir le panel du chat de l'application
 *
 * @author Tanguy Bénard, Alexia Serrier
 * @version 1.0
 */

public class ChatPanel extends JPanel {

    /**
     * Zone de texte
     * @see TextArea
     */
    TextArea textArea;

    /**
     * Bouton retour
     * @see JButton
     */
    JButton boutonRetour;

    /**
     * Label pour le destinataire
     * @see JLabel
     */
    JLabel labelDestinataire;

    /**
     * Label espace
     * @see JLabel
     */
    JLabel spaceLabel;

    /**
     * Panel se trouvant au nord
     * @see JPanel
     */
    JPanel panelNord;

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


    /**
     * Constructeur de la classe ChatPanel
     */
    public ChatPanel(){

        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(800,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);

        panelNord = new JPanel();
        panelNord.setLayout(new GridLayout(1,3));

        spaceLabel = new JLabel();

        textArea = new TextArea();
        textArea.setFont(new Font("Segoe UI", 0, 14));
        textArea.disable();

        boutonRetour = new JButton("Retour");

        labelDestinataire = new JLabel("Destinataire");
        labelDestinataire.setFont(new Font("Segoe UI", Font.ITALIC, 18));

        panelNord.add(labelDestinataire);
        panelNord.add(spaceLabel);
        panelNord.add(boutonRetour);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER) ;
        this.add(Singletons.getInputPanel(), BorderLayout.SOUTH) ;
        this.add(panelNord, BorderLayout.NORTH) ;
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

    public void enregistreEcouteur(Controleur controleur) {
        boutonRetour.addActionListener(controleur);
        boutonRetour.setActionCommand("quitChat");
    }

    /**
     * Cette méthode permet de récupérer la TextArea
     * @return la TextArea
     */
    public TextArea getTextArea() {
        return textArea;
    }
}
