package Controleur;

import Modele.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Controleur implements ActionListener, MouseListener {
    Socket socket;


    public Controleur() {
        Singletons.getInputPanel().enregistreEcouteur(this);
        Singletons.getInscriptionPanel().enregistreEcouteur(this);
        Singletons.getConnexionPanel().enregistreEcouteur(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            Singletons.getChatPanel().getTextArea().append(Singletons.getInputPanel().getTextField().getText() + "\n");
            try {
                RequestActions.envoiMessage(TimeServer.listClients.get(RequestActions.socketInstance),null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Singletons.getInputPanel().getTextField().setText("");

        }
        if (event.getActionCommand() == "inscription") {

            Singletons.getInscriptionPanel().getFieldPseudo().setText("");
            Singletons.getInscriptionPanel().getFieldMDP().setText("");
            Singletons.getInscriptionPanel().getFieldNom().setText("");
            Singletons.getInscriptionPanel().getFieldPrenom().setText("");
            Singletons.getInscriptionPanel().getFieldMDPVerification().setText("");

            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "connexion") {
            Singletons.getConnexionPanel().getFieldPseudo().setText("");
            Singletons.getConnexionPanel().getFieldMDP().setText("");

            Singletons.getPanelFond().remove(Singletons.getPanelOnglet());
            Singletons.getPanelFond().add(Singletons.getChatPanel());
            //Todo :  Après le test serveur de log, afficher le panel discussion
            try {
                Utilisateur utilisateur = RequestActions.connexion();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
