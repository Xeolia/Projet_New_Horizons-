package Controleur;

import Vue.ChatPanel;
import Vue.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {
    ChatPanel chatPanel;
    InputPanel inputPanel;

    public Controleur(ChatPanel parChatPanel, InputPanel parInputPanel) {
        this.chatPanel = parChatPanel;
        this.inputPanel = parInputPanel;
        inputPanel.enregistreEcouteur(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            chatPanel.getTextArea().append(inputPanel.getTextField().getText() + "\n");
            //ClientSocket.sendOut(textField.getText(), textArea) ;
            inputPanel.getTextField().setText("");
        }

    }
}
