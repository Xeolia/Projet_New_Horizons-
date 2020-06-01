package Controleur;

import Modele.*;
import Vue.FrameError;
import Vue.PanelAjoutDiscussion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
     * indentifiant de discussion
     */
    public static String idDiscussion;

    /**
     * scollpane de liste de discussion
     */
    public static JScrollPane sp;

    /**
     * utilisateur de l'application
     */
    public static Utilisateur utilisateur;

    /**
     * thread de chat
     */
    ChatThread chatThread= new ChatThread();

    /**
     * thread de chat
     */
    Thread thread= new Thread(chatThread);


    /**
     * Constructeur de la classe controleur
     */
    public Controleur() {
        Singletons.getInputPanel().enregistreEcouteur(this);
        Singletons.getInscriptionPanel().enregistreEcouteur(this);
        Singletons.getPanelNord().enregistreEcouteur(this);
        Singletons.getConnexionPanel().enregistreEcouteur(this);
        Singletons.getPanelDiscussion().enregistreEcouteur(this);
        Singletons.getChatPanel().enregistreEcouteur(this);
        Singletons.getPanelAjoutDiscussion().enregistreEcouteur(this);

        Singletons.getListeDiscussion().getListe().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String valueSelected = (String) Singletons.getListeDiscussion().getListe().getSelectedValue();
                String[] tabNomDiscussion = valueSelected.split(":");
                idDiscussion = tabNomDiscussion[0];
                try {
                    String message = Serialisation.findSimpleDiscusionMessage(idDiscussion);
                    if(message!=null){
                            Singletons.getChatPanel().getTextArea().setText(message);
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


    /**
     * La méthode actionPerformed permet d'attribuer des actions a des évenements
     *
     * @param event
     *             Un nouvelle évenement
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "envoiMessage") {
            try {
                RequestActions.envoiMessage(idDiscussion, utilisateur.getPseudo() ,Singletons.getInputPanel().getTextField().getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Singletons.getInputPanel().getTextField().setText("");
            String message = null;
            try {
                message = Serialisation.findSimpleDiscusionMessage(Controleur.idDiscussion);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(message!=null){
                Singletons.getChatPanel().getTextArea().setText(message);
            }

            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();
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
            try {
                utilisateur = RequestActions.connexion();
                if(utilisateur.getNom()!=null){
                    Singletons.getConnexionPanel().getFieldPseudo().setText("");
                    Singletons.getConnexionPanel().getFieldMDP().setText("");

                    Singletons.getPanelFond().remove(Singletons.getPanelOnglet());
                    Singletons.getPanelFond().add(Singletons.getPanelDiscussion());

                    Singletons.getListeDiscussion().initList();

                    Singletons.getMaFenetre().repaint();
                    Singletons.getMaFenetre().revalidate();
                }
                else{
                    new FrameError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getActionCommand() == "discussion") {

            Singletons.getPanelFond().remove(Singletons.getPanelDiscussion());
            Singletons.getPanelFond().add(Singletons.getPanelAjoutDiscussion());

            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "refresh") {

            String message = null;
            try {
                message = Serialisation.findSimpleDiscusionMessage(Controleur.idDiscussion);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(message!=null){
                Singletons.getChatPanel().getTextArea().setText(message);
            }
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();
            Singletons.getChatPanel().repaint();
            Singletons.getChatPanel().revalidate();

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
            idDiscussion = null;
            Singletons.getChatPanel().getTextArea().setText("");
            Singletons.getPanelFond().remove(Singletons.getChatPanel());
            Singletons.getPanelFond().add(Singletons.getPanelDiscussion());
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "retourDiscussion") {
            Singletons.getPanelAjoutDiscussion().getTextFieldNom().setText("");
            Singletons.getPanelFond().remove(Singletons.getPanelAjoutDiscussion());
            Singletons.getPanelFond().add(Singletons.getPanelDiscussion());
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();

        }
        if (event.getActionCommand() == "addDiscussion") {
            PanelAjoutDiscussion panelAjoutDiscussion = Singletons.getPanelAjoutDiscussion();
            try {
                RequestActions.creationChatSimple(panelAjoutDiscussion.getTextFieldNom().getText(), utilisateur.getPseudo(), panelAjoutDiscussion.getCombo().getSelectedItem().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Singletons.getListeDiscussion().initList();


            Singletons.getPanelFond().remove(Singletons.getPanelAjoutDiscussion());
            Singletons.getPanelFond().add(Singletons.getPanelDiscussion());
            Singletons.getMaFenetre().repaint();
            Singletons.getMaFenetre().revalidate();
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
