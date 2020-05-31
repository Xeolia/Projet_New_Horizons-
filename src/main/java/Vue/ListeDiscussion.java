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

public class ListeDiscussion extends JPanel {

    List<String> idList = new ArrayList<>();
    JList liste;
    JLabel etiquette = new JLabel(" ");
    DefaultListModel listModel ;

    public ListeDiscussion() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liste=new JList();

        initList();
        JScrollPane sp = new JScrollPane(liste);
        this.add(sp);

        setLayout(new GridLayout(idList.size(), 1, 500, 20));
    }

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


    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        etiquette.setText((String)liste.getSelectedValue());
    }

    public JList getListe() {
        return liste;
    }
}
