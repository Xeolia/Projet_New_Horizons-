package Controleur;

import Modele.*;
import Vue.ComboChoix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Controleur implements ActionListener, MouseListener {
    Socket socket;
    public static String idDiscussion;
    public Utilisateur utilisateur;

    public Controleur() {
        Singletons.getInputPanel().enregistreEcouteur(this);
        Singletons.getInscriptionPanel().enregistreEcouteur(this);
        Singletons.getPanelNord().enregistreEcouteur(this);
        Singletons.getConnexionPanel().enregistreEcouteur(this);
        Singletons.getPanelDiscussion().enregistreEcouteur(this);
        Singletons.getChatPanel().enregistreEcouteur(this);
        Singletons.getComboChoix().enregistreEcouteur(this);


        Singletons.getListeDiscussion().getListe().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                idDiscussion = (String) Singletons.getListeDiscussion().getListe().getSelectedValue();

                try {
                    HashMap<String,String> listeMessage = Serialisation.findSimpleDiscusionMessage(idDiscussion);
                    for (Map.Entry<?, ?> entry : listeMessage.entrySet()) {
                        Singletons.getChatPanel().getTextArea().setText(entry.getKey() + " : " + entry.getValue());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Singletons.getPanelFond().remove(Singletons.getPanelDiscussion());
                Singletons.getPanelFond().add(Singletons.getChatPanel());

                Singletons.getMaFenetre().repaint();
                Singletons.getMaFenetre().revalidate();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            Singletons.getChatPanel().getTextArea().append(Singletons.getInputPanel().getTextField().getText() + "\n");
            //try {
                //RequestActions.envoiMessage(TimeServer.listClients.get(RequestActions.socketInstance), null);
           // } catch (IOException e) {
              //  e.printStackTrace();
           // }
            Singletons.getInputPanel().getTextField().setText("");

        }
        if (event.getActionCommand() == "inscription") {
            boolean code = false;
            try {
                code = RequestActions.inscription();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (code) {
                Singletons.getInscriptionPanel().getFieldPseudo().setText("");
                Singletons.getInscriptionPanel().getFieldMDP().setText("");
                Singletons.getInscriptionPanel().getFieldNom().setText("");
                Singletons.getInscriptionPanel().getFieldPrenom().setText("");
                Singletons.getInscriptionPanel().getFieldMDPVerification().setText("");

                Singletons.getMaFenetre().repaint();
                Singletons.getMaFenetre().revalidate();
            }
        }
        if (event.getActionCommand() == "connexion") {
            //Todo :  Après le test serveur de log, afficher le panel discussion
            try {
                utilisateur = RequestActions.connexion();
                if(utilisateur!=null){
                    Singletons.getConnexionPanel().getFieldPseudo().setText("");
                    Singletons.getConnexionPanel().getFieldMDP().setText("");

                    Singletons.getPanelFond().remove(Singletons.getPanelOnglet());
                    Singletons.getPanelFond().add(Singletons.getPanelDiscussion());

                    Singletons.getMaFenetre().repaint();
                    Singletons.getMaFenetre().revalidate();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        if (event.getActionCommand() == "discussion") {

            Singletons.getPanelFond().remove(Singletons.getPanelDiscussion());
            Singletons.getPanelFond().add(Singletons.getComboChoix());

            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "deconnexion") {
            try {
                RequestActions.deconnexion();
                Singletons.getPanelFond().remove(Singletons.getPanelDiscussion());
                Singletons.getPanelFond().add(Singletons.getPanelOnglet());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "quitChat") {
            Singletons.getChatPanel().getTextArea().setText("");
            Singletons.getPanelFond().remove(Singletons.getChatPanel());
            Singletons.getPanelFond().add(Singletons.getPanelDiscussion());
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();
        }
        if (event.getActionCommand() == "addDiscussion") {
            ComboChoix comboChoix = Singletons.getComboChoix();
            try {
                RequestActions.creationChatSimple(comboChoix.getTextFieldNom().getText(), utilisateur.getPseudo(), comboChoix.getCombo().getSelectedItem().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Singletons.getListeDiscussion().initList();


            Singletons.getPanelFond().remove(Singletons.getComboChoix());
            Singletons.getPanelFond().add(Singletons.getPanelDiscussion());
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();
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
        Singletons.getInscriptionPanel().getInscriptionButton().setBackground(new Color(255,100,100));
        Singletons.getConnexionPanel().getConnexionButton().setBackground(new Color(255,100,100));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Singletons.getInscriptionPanel().getInscriptionButton().setBackground(new Color(12, 91, 160));
        Singletons.getConnexionPanel().getConnexionButton().setBackground(new Color(12, 91, 160));
    }


}
