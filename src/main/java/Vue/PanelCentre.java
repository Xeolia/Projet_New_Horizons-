package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCentre extends JPanel {

    JPanel quatriemeDiscussion= new JPanel();
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
        premiereDiscussion.setBackground(Color.cyan);
        deuxiemeDiscussion.setBackground(Color.red);
        troisiemeDiscussion.setBackground(Color.gray);
        quatriemeDiscussion.setBackground(Color.blue);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(10, 1,30,50));
        this.add(premiereDiscussion);
        this.add(deuxiemeDiscussion);
        this.add(troisiemeDiscussion);
        this.add(quatriemeDiscussion);


    }

}
