package Vue;

import Controleur.Controleur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnexionPanel extends JPanel {

    protected int strokeSize = 1;
    protected Color shadowColor = Color.black;
    protected boolean shady = true;
    protected boolean highQuality = true;
    protected Dimension arcs = new Dimension(20, 20);
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;
    protected JButton connexionButton;

    protected JLabel labelConnexion;
    protected JLabel emptyLine;
    protected JTextField fieldPseudo;
    protected JLabel labelPseudo;
    protected JTextField fieldMDP;
    protected JLabel labelMDP;

    public ConnexionPanel(){
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(600,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);
        labelConnexion = new JLabel("Connexion");
        emptyLine = new JLabel(" ");
        fieldPseudo = new JTextField();
        labelPseudo = new JLabel("Pseudo");
        fieldMDP = new JPasswordField();
        labelMDP = new JLabel("Mot de passe");
        connexionButton = new JButton("Connexion");

        labelConnexion.setFont(new Font("", Font.ITALIC, 20));

        fieldMDP.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldMDP.setForeground(new Color(102, 102, 102));
        fieldMDP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldPseudo.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldPseudo.setForeground(new Color(102, 102, 102));
        fieldPseudo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        fieldMDP.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        fieldMDP.setForeground(new Color(102, 102, 102));
        fieldMDP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 91, 160)));

        labelPseudo.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelPseudo.setForeground(new Color(153, 153, 153));

        labelMDP.setFont(new Font("Segoe UI", 0, 10)); // NOI18N
        labelMDP.setForeground(new Color(153, 153, 153));

        connexionButton.setBackground (new Color(12, 91, 160));
        connexionButton.setForeground (Color.white);
        connexionButton.setOpaque(true);
        connexionButton.setBorderPainted(false);


        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        FormBuilder.init(this)
                .add(labelConnexion, FormBuilder::spanX3)
                .newRow()
                .add(emptyLine)
                .newRow()
                .add(labelPseudo)
                .newRow()
                .add(fieldPseudo, FormBuilder::spanX3)
                .newRow()
                .add(labelMDP)
                .newRow()
                .add(fieldMDP, FormBuilder::spanX3)
                .newRow()
                .add("", connexionButton, FormBuilder::spanX2);
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

    public JButton getConnexionButton() {
        return connexionButton;
    }

    public JTextField getFieldPseudo() {
        return fieldPseudo;
    }

    public JTextField getFieldMDP() {
        return fieldMDP;
    }

    public void enregistreEcouteur(Controleur controleur) {
        connexionButton.addMouseListener(controleur);
        connexionButton.addActionListener(controleur);
        connexionButton.setActionCommand("connexion");
    }


}