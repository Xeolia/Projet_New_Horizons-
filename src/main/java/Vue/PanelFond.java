/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;

/**
 * La classe PanelFond permet de définir le panel de fond
 *
 * @author Tanguy Bénard, Alexia Serrier
 * @version 1.0
 */

public class PanelFond extends JPanel {

    /**
     * Initialisation de la couleur kStartColor
     * @see Color
     */
    public Color kStartColor = new Color(255,100,100);

    /**
     * Initialisation de la couleur kEndColor
     * @see Color
     */
    public Color kEndColor = Color.BLUE;

    /**
     * Initialisation de la variable kTransparentControls sur true
     */
    public boolean kTransparentControls = true;

    /**
     * Initialisation de la variable kGradientFocus sur 500
     */
    public int kGradientFocus = 500;

    /**
     * label de titre
     * @see JLabel
     */
    private JLabel labelTitre;

    /**
     * Cette méthode permet de récupérer la variable kStartColor
     * @return variable kStartColor
     */
    public Color getkStartColor() {
        return kStartColor;
    }

    /**
     * Cette méthode permet de récupérer la variable kStartColor
     * @param kStartColor la variable kStartColor
     */
    public void setkStartColor(Color kStartColor) {
        this.kStartColor = kStartColor;
    }

    /**
     * Cette méthode permet de récupérer la variable kEndColor
     * @return kEndColor
     */
    public Color getkEndColor() {
        return kEndColor;
    }

    /**
     * Cette méthode permet de changer la variable kEndColor
     * @param kEndColor nouvelle variable kEndColor
     */
    public void setkEndColor(Color kEndColor) {
        this.kEndColor = kEndColor;
    }

    /**
     * Cette méthode permet de récupérer la variable kEndColor
     * @return la variable kTransparentControls
     */
    public boolean iskTransparentControls() {
        return kTransparentControls;
    }

    /**
     * Cette méthode permet de changer la variable kTransparentControls
     * @param kTransparentControls nouvelle variable kTransparentControls
     */
    public void setkTransparentControls(boolean kTransparentControls) {
        this.kTransparentControls = kTransparentControls;
    }

    /**
     * Cette méthode permet de récupérer la variable kGradientFocus
     * @return la variable kGradientFocus
     */
    public int getkGradientFocus() {
        return kGradientFocus;
    }


    /**
     * Cette méthode permet de changer la variable kGradientFocus
     * @param kGradientFocus nouvelle variable kGradientFocus
     */
    public void setkGradientFocus(int kGradientFocus) {
        this.kGradientFocus = kGradientFocus;
    }

    /**
     * Constructeur de la classe PanelFond
     */
    public PanelFond() {

        if (kTransparentControls) {
            setBg(true);
        } else {
            setBg(false);
        }
        this.add(Singletons.getPanelOnglet());
    }

    /**
     * Cette Méthode permet de mettre les mouvements de souris sur écoute
     * @param l objet MouseMotionListerner
     */
    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Cette méthode permet de changer l'aspect de l'interface
     * @param g type de graphique a utiliser
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, kStartColor, kGradientFocus, h, kEndColor);;

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        //g2d.dispose();
    }

    /**
     * Cette méthode permet de paramétrer
     * @param isOpaque variable d'opacité
     */
    private void setBg(boolean isOpaque) {
        Component[] components = this.getComponents();
        for (Component component : components) {

            ((JLabel) component).setOpaque(isOpaque);
            ((JCheckBox) component).setOpaque(isOpaque);
            ((JTextField) component).setOpaque(isOpaque);
            ((JPasswordField) component).setOpaque(isOpaque);
            ((JFormattedTextField) component).setOpaque(isOpaque);
            ((JToolBar) component).setOpaque(isOpaque);
            ((JRadioButton) component).setOpaque(isOpaque);

        }
    }

    /**
     * Cette méthode permet de récupérer l'objet InscriptionPanel
     * @return objet InscriptionPanel
     */
    public InscriptionPanel getInscriptionPanel() {
        return Singletons.getInscriptionPanel();
    }
}
