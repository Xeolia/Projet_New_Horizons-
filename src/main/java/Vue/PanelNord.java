package Vue;

import javax.swing.*;
import java.awt.*;

public class PanelNord extends JPanel {
    JLabel labelDiscussion = new JLabel("Discussion");
    JPanel panelNord = new JPanel(new GridBagLayout());
    JButton button;
    JTextField usernameChooser = new JTextField();

    public PanelNord() {
        labelDiscussion.setFont(new Font(" ", Font.ITALIC, 15));
        setLayout(new GridLayout(2, 1));
        button = new JButton("Ajouter");
        panelNord.add(usernameChooser, 0);
        add(labelDiscussion, BorderLayout.NORTH);
        add(button,BorderLayout.NORTH);

    }
}
