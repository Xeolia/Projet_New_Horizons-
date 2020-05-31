package Controleur;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


/**
 * Controleur est la classe qui permet de controler les actions sur la Vue
 *
 * @author Tanguy Bénard
 * @version 1.0
 *
 *
 */
public class Controleur implements ActionListener, MouseListener {

    /**
     * Constructeur de la classe controleur
     */
    public Controleur() {
        Singletons.getInputPanel().enregistreEcouteur(this);
        Singletons.getInscriptionPanel().enregistreEcouteur(this);
        Singletons.getPanelNord().enregistreEcouteur(this);
        Singletons.getConnexionPanel().enregistreEcouteur(this);
    }


    /**
     * La méthode actionPerformed permet d'attribuer des actions a des évenements
     *
     * @param event
     *             Un nouvelle évenement
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            Singletons.getChatPanel().getTextArea().append(Singletons.getInputPanel().getTextField().getText() + "\n");
            try {
                RequestActions.envoiMessage(TimeServer.listClients.get(RequestActions.socketInstance), null);
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
        if (event.getActionCommand() == "discussion") {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.cyan);
            Singletons.getPanelCentre().add(jPanel);
            Singletons.getFenetreDiscussion().repaint();
            Singletons.getFenetreDiscussion().revalidate();

        }
    }


    /**
     * La méthode mouseClicked attribut une action a un click de souris
     *
     * @param mouseEvent
     *              Evenement de souris.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    /**
     * La méthode mousePressed attribut une action quand le bouton de souris est pressé
     *
     * @param mouseEvent
     *              Evenement de souris.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /**
     * La méthode mouseReleased attribut une action quand le bouton de souris est relaché
     *
     * @param mouseEvent
     *              Evenement de souris.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /**
     * La méthode mouseEntered attribut une action quand le pointeur de la souris entre dans une zone
     *
     * @param mouseEvent
     *              Evenement de souris.
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        Singletons.getInscriptionPanel().getInscriptionButton().setBackground(new Color(255,100,100));
        Singletons.getConnexionPanel().getConnexionButton().setBackground(new Color(255,100,100));
    }

    /**
     * La méthode mouseExited attribut une action quand le pointeur de la souris sort d'une zone
     *
     * @param mouseEvent
     *               Evenement de souris.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        Singletons.getInscriptionPanel().getInscriptionButton().setBackground(new Color(12, 91, 160));
        Singletons.getConnexionPanel().getConnexionButton().setBackground(new Color(12, 91, 160));
    }
}
