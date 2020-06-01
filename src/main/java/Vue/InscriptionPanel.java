package Vue;


import Controleur.Controleur;
import Modele.Singletons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * La Classe InscriptionPanel définir le panel d'insciption de l'application
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class InscriptionPanel extends JPanel {

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
     * Bouton d'inscription
     * @see JButton
     */
    protected JButton inscriptionButton;

    /**
     * Label correspondant a l'inscription
     * @see JLabel
     */
    protected JLabel labelInscription;

    /**
     * Ligne vide
     * @see JLabel
     */
    protected JLabel emptyLine;

    /**
     * Champs pour le prénom
     * @see JTextField
     */
    protected JTextField fieldPrenom;

    /**
     * Label  prénom
     */
    protected JLabel labelPrenom;

    /**
     * Champs pour le nom
     * @see JTextField
     */
    protected JTextField fieldNom;

    /**
     * Label nom
     * @see JLabel
     */
    protected JLabel labelNom;

    /**
     * Champs correspondant au pseudo
     * @see JTextField
     */
    protected JTextField fieldPseudo;

    /**
     * Label pseudo
     * @see JLabel
     */
    protected JLabel labelPseudo;

    /**
     * Champs pour le mot de passe
     * @see JTextField
     */
    protected JTextField fieldMDP;

    /**
     * Label mot de passe
     */
    protected JLabel labelMDP;

    /**
     * Chamsp pour la vérification du mot de passe
     * @see JTextField
     */
    protected JTextField fieldMDPVerification;

    /**
     * label vérification du mot de passe
     */
    protected JLabel labelMDPVerification;

    /**
     * Constructeur de la classe InscriptionPanel
     */
    public InscriptionPanel(){
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(600,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);
        labelInscription = new JLabel("Inscription");
        emptyLine = new JLabel(" ");
        fieldPrenom = new JTextField();
        labelPrenom = new JLabel("Prenom");
        fieldNom = new JTextField();
        labelNom = new JLabel("Nom");
        fieldPseudo = new JTextField();
        labelPseudo = new JLabel("Pseudo");
        fieldMDP = new JPasswordField();
        labelMDP = new JLabel("Mot de passe");
        fieldMDPVerification = new JPasswordField();
        labelMDPVerification = new JLabel("Mot de passe (Vérification)");
        inscriptionButton = new JButton("Enregistrer");

        labelInscription.setFont(new Font("", Font.ITALIC, 20));

        fieldNom.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldNom.setForeground(new Color(102, 102, 102));
        fieldNom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldPrenom.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldPrenom.setForeground(new Color(102, 102, 102));
        fieldPrenom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldMDP.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldMDP.setForeground(new Color(102, 102, 102));
        fieldMDP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldPseudo.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldPseudo.setForeground(new Color(102, 102, 102));
        fieldPseudo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldMDP.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldMDP.setForeground(new Color(102, 102, 102));
        fieldMDP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldMDPVerification.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldMDPVerification.setForeground(new Color(102, 102, 102));
        fieldMDPVerification.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        labelNom.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelNom.setForeground(new Color(153, 153, 153));

        labelPrenom.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelPrenom.setForeground(new Color(153, 153, 153));

        labelPseudo.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelPseudo.setForeground(new Color(153, 153, 153));

        labelMDP.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelMDP.setForeground(new Color(153, 153, 153));

        labelMDPVerification.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelMDPVerification.setForeground(new Color(153, 153, 153));

        inscriptionButton.setBackground (new Color(12, 91, 160));
        inscriptionButton.setForeground (Color.white);
        inscriptionButton.setOpaque(true);
        inscriptionButton.setBorderPainted(false);


        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        FormBuilder.init(this)
                .add(labelInscription, FormBuilder::spanX3)
                .newRow()
                .add(emptyLine)
                .newRow()
                .add(labelNom).add(labelPrenom)
                .newRow()
                .add(fieldNom, FormBuilder::fillParentX).add(fieldPrenom, FormBuilder::fillParentX)
                .newRow()
                .add(labelPseudo)
                .newRow()
                .add(fieldPseudo, FormBuilder::spanX2)
                .newRow()
                .add(labelMDP)
                .newRow()
                .add(fieldMDP, FormBuilder::spanX2)
                .newRow()
                .add(labelMDPVerification)
                .newRow()
                .add(fieldMDPVerification, FormBuilder::spanX2)
                .newRow()
                .add("", inscriptionButton, FormBuilder::spanX2);
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

    /**
     * Méthode permettant de récupérer le bouton d'inscription
     * @return bouton d'inscription
     */
    public JButton getInscriptionButton() {
        return inscriptionButton;
    }

    /**
     * Méthode permettant de récupérer le champs prénom
     * @return champs prénom
     */
    public JTextField getFieldPrenom() {
        return fieldPrenom;
    }

    /**
     * Méthode permettant de récupérer le champs nom
     * @return champs nom
     */
    public JTextField getFieldNom() {
        return fieldNom;
    }

    /**
     * Méthode permettant de récupérer le champs pseudo
     * @return champs pseudo
     */
    public JTextField getFieldPseudo() {
        return fieldPseudo;
    }

    /**
     * Méthode permettant de récupérer le champs mot de passe
     * @return champs mot de passe
     */
    public JTextField getFieldMDP() {
        return fieldMDP;
    }

    /**
     * Méthode permettant de récupérer le champs de vérification de mot de passe
     * @return champs vérification de mot de passe
     */
    public JTextField getFieldMDPVerification() {
        return fieldMDPVerification;
    }

    /**
     * Méthode permettant d'appliquer un listener sur le bouton insciption
     * @param controleur le controleur qui appliquera des réactions apres les actions sur le bouton
     */
    public void enregistreEcouteur(Controleur controleur) {
        inscriptionButton.addMouseListener(controleur);
        inscriptionButton.addActionListener(controleur);
        inscriptionButton.setActionCommand("inscription");
    }
}
