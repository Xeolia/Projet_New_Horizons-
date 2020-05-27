package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCentre extends JPanel {

    JPanel premiereDiscussion = new JPanel();
    JPanel deuxiemeDiscussion = new JPanel();
    JPanel troisiemeDiscussion = new JPanel();

    public JPanel getPremiereDiscussion() {
        return premiereDiscussion;
    }

    public JPanel getDeuxiemeDiscussion() {
        return deuxiemeDiscussion;
    }

    public JPanel getTroisiemeDiscussion() {
        return troisiemeDiscussion;
    }

    public PanelCentre() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        FormBuilder.init(this)
                .add(premiereDiscussion)
                .add(deuxiemeDiscussion)
                .add(troisiemeDiscussion);


    }

}
