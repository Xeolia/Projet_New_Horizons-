package Vue;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    TextArea textArea;
    Vue.InputPanel inputPanel;

    public ChatPanel(){
        textArea = new TextArea();
        inputPanel = new Vue.InputPanel();
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER) ;
        this.add(inputPanel, BorderLayout.SOUTH) ;

    }

    public TextArea getTextArea() {
        return textArea;
    }
}
