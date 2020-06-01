package Vue;

import Controleur.Controleur;
import Modele.Serialisation;
import Modele.Singletons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe ListeDiscussion permet d'afficher le panel avec la liste de discussion de l'utilisateur
 */
public class ListeDiscussion extends JPanel {

    /**
     * Identifiant de la liste
     */
    List<String> idList = new ArrayList<>();

    /**
     * List affichant la liste de discussion
     */
    JList liste;

    /**
     * Label affichant le nom des discussions dans la liste
     */
    JLabel etiquette = new JLabel(" ");

    /**
     * Permet de définir le model de la liste
     */
    DefaultListModel listModel ;

    /**
     * Constructeur de la classe ListeDiscussion
     */
    public ListeDiscussion() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liste=new JList();

        initList();
        JScrollPane sp = new JScrollPane(liste);
        this.add(sp);

        setLayout(new GridLayout(idList.size(), 1, 500, 20));
    }

    /**
     * Cette méthode permet d'initialiser la liste
     */
    public void initList(){

        HashMap<String,String> idMap = new HashMap<>();
        try {
            idMap = Serialisation.isUserInDiscussion("bobiiy");//todo Change it
        } catch (IOException e) {
            e.printStackTrace();
        }
        listModel = new DefaultListModel();
        for (Map.Entry<?, ?> entry : idMap.entrySet()) {
            listModel.addElement(entry.getKey());
        }

        liste.addListSelectionListener(this::valueChanged);
        liste.setModel(listModel);

        this.updateUI();
    }

    /**
     * Cette méthode permet de changer des valeurs à l'intérieur des étiquettes de la liste
     * @param listSelectionEvent
     */
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        etiquette.setText((String)liste.getSelectedValue());
    }

    /**
     * Cette méthode permet de récupérer la liste de discussion
     */
    public JList getListe() {
        return liste;
    }
}
