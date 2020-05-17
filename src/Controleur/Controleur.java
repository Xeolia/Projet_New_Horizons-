package Controleur;

import Vue.ChatPanel;
import Vue.InputPanel;
import Vue.InscriptionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controleur implements ActionListener, MouseListener {
    ChatPanel chatPanel;
    InputPanel inputPanel;
    InscriptionPanel inscriptionPanel;

    public Controleur(ChatPanel parChatPanel, InputPanel parInputPanel, InscriptionPanel parInscriptionPanel) {
        this.chatPanel = parChatPanel;
        this.inputPanel = parInputPanel;
        this.inscriptionPanel = parInscriptionPanel;
        inputPanel.enregistreEcouteur(this);
        inscriptionPanel.enregistreEcouteur(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            chatPanel.getTextArea().append(inputPanel.getTextField().getText() + "\n");
            //ClientSocket.sendOut(textField.getText(), textArea) ;
            inputPanel.getTextField().setText("");
        }


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        inscriptionPanel.getInscriptionButton().setBackground(new Color(255,100,100));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        inscriptionPanel.getInscriptionButton().setBackground(new Color(12, 91, 160));
    }
}
