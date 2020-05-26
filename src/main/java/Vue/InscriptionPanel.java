package Vue;


import Controleur.Controleur;
import Modele.Singletons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InscriptionPanel extends JPanel {

    protected int strokeSize = 1;
    protected Color shadowColor = Color.black;
    protected boolean shady = true;
    protected boolean highQuality = true;
    protected Dimension arcs = new Dimension(20, 20);
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;
    protected JButton inscriptionButton;
    protected JLabel labelInscription;
    protected JLabel emptyLine;
    protected JTextField fieldPrenom;
    protected JLabel labelPrenom;
    protected JTextField fieldNom;
    protected JLabel labelNom;
    protected JTextField fieldPseudo;
    protected JLabel labelPseudo;
    protected JTextField fieldMDP;
    protected JLabel labelMDP;
    protected JTextField fieldMDPVerification;
    protected JLabel labelMDPVerification;

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

    public JButton getInscriptionButton() {
        return inscriptionButton;
    }

    public void enregistreEcouteur(Controleur controleur) {
        inscriptionButton.addMouseListener(controleur);
        inscriptionButton.addActionListener(controleur);
        inscriptionButton.setActionCommand("connexion");
    }
}
