package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * La classe FormBuilder permet de construire la fenêtre avec les différents éléments
 *
 * @author Tanguy Bénard
 * @version 1.0
 */

public class FormBuilder {

    /**
     * Conteneur qui contiendra plusieurs JLabel
     * @see Container
     */
    private final Container container;

    /**
     * Variable de numéro de ligne
     */
    private int row;

    /**
     * Variable de numéro de colonne
     */
    private int col = -1;

    /**
     * Contrainte applicable a des label
     * @see GridBagConstraints
     */
    private GridBagConstraints labelCons;

    /**
     * Constructeur de la classe FormBuilder
     * @param container conteneur des éléments graphiques
     */
    private FormBuilder(Container container) {
        this.container = container;
        container.setLayout(new GridBagLayout());
    }

    /**
     * Méhtode static permettant de créer un buidler préfabriqué
     * @param container ce que le buider contiendra
     * @return le builder
     */
    public static FormBuilder init(Container container) {
        FormBuilder builder = new FormBuilder(container);
        return builder;
    }

    /**
     * Cette méthode initialise les contraintes sur les labels
     */
    private void initLabelCons() {
        labelCons = new GridBagConstraints();
        labelCons.weightx = 0;
        labelCons.gridwidth = 1;
        labelCons.ipadx = 1;
        labelCons.anchor = GridBagConstraints.NORTHEAST;
    }

    /**
     * Cette méthode permet d'ajouter un composant sans label
     * @param comp le composant
     * @return un objet FormBuilder
     */
    //add component without label
    public FormBuilder add(JComponent comp) {
        this.add(null, comp, null);
        return this;
    }

    /**
     * Cette méthode permet d'ajouter un composant sans label et de permettre d'appliquer des contraintes
     * @param comp le composant
     * @param consSetters les contraintes a appliquer
     * @return un objet FormBuilder
     */
    //add component without label and allow caller to set constraints
    @SafeVarargs
    public final FormBuilder add(JComponent comp, Consumer<GridBagConstraints>... consSetters) {
        this.add(null, comp, consSetters);
        return this;
    }

    /**
     * Cette méthode permet d'ajouter un composant avec label et d'appliquer des contraintes
     * @param label nom du label
     * @param comp composent JComponent
     * @param consSetters contrainte sur GridBagConstraints
     * @return un objet FormBuilder
     */
    //add component with label and allow caller to set constraints
    @SafeVarargs
    public final FormBuilder add(String label, JComponent comp, Consumer<GridBagConstraints>... consSetters) {
        if (label != null) {
            if (this.labelCons == null) {
                initLabelCons();
            }
            JLabel jLabel = new JLabel(label);
            labelCons.gridx = ++col;
            labelCons.gridy = row;
            container.add(jLabel, labelCons);
        }
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = ++col;
        cons.gridy = row;
        cons.fill = GridBagConstraints.BOTH;//by default fill the available grid space
        cons.insets = new Insets(0, 5, 5, 0);
        if (consSetters != null) {//allow client side to set constraints
            for (Consumer<GridBagConstraints> cs : consSetters) {
                cs.accept(cons);
            }
        }
        container.add(comp, cons);
        return this;
    }

    /**
     * Cette méthode permet d'ajouter plusieurs label en temps que légende de ligne
     * @param labels les labels a ajouter
     * @return un objet FormBuilder
     */
    //add multiple labels in current row
    public FormBuilder addLabelsAsRowHeading(String... labels) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridy = row;
        for (String label : labels) {
            cons.gridx = ++col;
            JLabel jLabel = new JLabel(label);
            container.add(jLabel, cons);
        }
        return this;
    }

    /**
     * Cette méthode créé une nouvelle ligne dans l'interface
     * @return un objet FormBuilder
     */
    //start new row
    public FormBuilder newRow() {
        ++row;
        col = -1;
        return this;
    }

    /**
     * Cette méthode permet de sauter un colonne
     * @param columns le numéro de colonne à sauter
     * @return un objet FormBuilder
     */
    //skip columns
    public FormBuilder skipColumns(int columns) {
        col+=columns;
        return this;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //horizontal span 2
    public static void spanX2(GridBagConstraints c) {
        c.gridwidth = 2;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //horizontal span 3
    public static void spanX3(GridBagConstraints c) {
        c.gridwidth = 3;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //horizontal span 4
    public static void spanX4(GridBagConstraints c) {
        c.gridwidth = 4;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //vertical span 2
    public static void spanY2(GridBagConstraints c) {
        c.gridheight = 2 ;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //fills horizontally if parent resized
    public static void fillParentX(GridBagConstraints c) {
        c.weightx = 1;
    }

    /**
     * Méthode pour paramétrer les contraintes
     * @param c objet GridBagConstraints
     */
    //fills vertically if parent resized
    public static void fillParentY(GridBagConstraints c) {
        c.weighty = 1;
    }
    //todo add more if needed
}