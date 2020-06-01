package Vue;

import Controleur.Controleur;
import Modele.Serialisation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * La classe ComboChoix permet de définir le panel de choix de l'applicationi
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class PanelAjoutDiscussion extends JPanel {

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
     * @see Dimension
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
     * Bouton d'ajout
     * @see JButton
     */
    JButton boutonAjout = new JButton("Ajout");

    /**
     * Bouton d'ajout
     * @see JButton
     */
    JButton boutonRetour = new JButton("Retour");

    /**
     * Liste déroulante
     * @see JComboBox
     */
    JComboBox<String> combo;

    /**
     * Label pour le nom
     * @see JLabel
     */
    JLabel labelNom;

    /**
     * Ligne vide
     * @see JLabel
     */
    JLabel emptyLine;

    /**
     * Champs pour le nom
     * @see JTextField
     */
    JTextField textFieldNom;

    /**
     * Constructeur de la classe ComboChoix
     */
    public PanelAjoutDiscussion() {
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(600,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);
        combo = null;
        labelNom = new JLabel("Nom discussion :");
        textFieldNom = new JTextField();
        emptyLine = new JLabel("-------------------------");
        try {
            combo = new JComboBox<>(Serialisation.findListUsersInJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        FormBuilder.init(this)
                .add(labelNom, FormBuilder::spanX3)
                .newRow()
                .add(textFieldNom, FormBuilder::spanX3)
                .newRow()
                .add(emptyLine, FormBuilder::spanX3)
                .newRow()
                .add(combo)
                .add(boutonAjout)
                .newRow()
                .add(boutonRetour, FormBuilder::spanX3);
    }

    /**
     * Cette méthode permet de mettre d'attribuer un ActionListener au bouton
     * @param parControleur le controleur qui appliquera des réactions après les actions sur le bouton
     */
    public void enregistreEcouteur(Controleur parControleur){
        boutonAjout.addActionListener(parControleur);
        boutonAjout.setActionCommand("addDiscussion");
        boutonRetour.addActionListener(parControleur);
        boutonRetour.setActionCommand("retourDiscussion");
    }

    /**
     * Cette méthode permet de récupérer la liste déroulante
     * @return l'objet JComboBox
     */
    public JComboBox<String> getCombo() {
        return combo;
    }

    /**
     * Cette méthode permet de récupérer le label du nom
     * @return l'objet labelNom
     */
    public JLabel getLabelNom() {
        return labelNom;
    }

    /**
     * Cette méthode permet de récupérer le champs nom
     * @return l'objet textFieldNom
     */
    public JTextField getTextFieldNom() {
        return textFieldNom;
    }

    /**
     * Cette méthode permet de récupérer le bouton d'ajout
     * @return l'objet boutonAjout
     */
    public JButton getBoutonAjout() {
        return boutonAjout;
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