package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    TextArea textArea;

    public ChatPanel(){
        textArea = new TextArea();
        textArea.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER) ;
        this.add(Singletons.getInputPanel(), BorderLayout.SOUTH) ;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
