package Vue;

import Controleur.Controleur;
import Modele.Serialisation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class ComboChoix extends JPanel {
    protected int strokeSize = 1;
    protected Color shadowColor = Color.black;
    protected boolean shady = true;
    protected boolean highQuality = true;
    protected Dimension arcs = new Dimension(20, 20);
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;


    JButton boutonAjout = new JButton("Add");
    JComboBox<String> combo;
    JLabel labelNom;
    JLabel emptyLine;
    JTextField textFieldNom;

    public ComboChoix() {
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
                .add(boutonAjout);


    }
    public void enregistreEcouteur(Controleur parControleur){
        boutonAjout.addActionListener(parControleur);
        boutonAjout.setActionCommand("addDiscussion");
    }

    public JComboBox<String> getCombo() {
        return combo;
    }

    public JLabel getLabelNom() {
        return labelNom;
    }

    public JTextField getTextFieldNom() {
        return textFieldNom;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
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
}