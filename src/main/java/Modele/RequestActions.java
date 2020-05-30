package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


import Vue.ConnexionPanel;
import Vue.FrameError;
import Vue.InscriptionPanel;

public class RequestActions {
    public static Socket socketInstance;
    public static String delimiteur = "/-/";

    //TODO CHIFFRER LES REQUETES ET FAIRE EN SORTE QU'ELLE NE PASSE PASSE PAS EN CLAIR

    public static boolean inscription() throws IOException {
        if(socketInstance == null){
            socketInstance = new Socket(TimeServer.host, 1515);
        }
        InscriptionPanel inscriptionPanel = Singletons.getInscriptionPanel();
        if (inscriptionPanel.getFieldMDP().getText().equals(inscriptionPanel.getFieldMDPVerification().getText())) {
            String requete = ProtocoleCode.CREATION_COMPTE + delimiteur + inscriptionPanel.getFieldPrenom().getText() + delimiteur + inscriptionPanel.getFieldNom().getText() + delimiteur + inscriptionPanel.getFieldPseudo().getText() + delimiteur + inscriptionPanel.getFieldMDP().getText();
            PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
            writer.write(requete);
            writer.flush();
            return true;
        }
        else{
            new FrameError();
            socketInstance.close();
            return false;
        }
    }

    public static Utilisateur connexion() throws IOException {
        if(socketInstance == null) {
            socketInstance = new Socket(TimeServer.host, 1515);
        }

        Utilisateur utilisateur = new Utilisateur();
        ConnexionPanel connexionPanel = Singletons.getConnexionPanel();
        String requete = ProtocoleCode.CONNEXION + delimiteur + connexionPanel.getFieldPseudo().getText() + delimiteur + connexionPanel.getFieldMDP().getText();
        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();

        BufferedInputStream bis = new BufferedInputStream(socketInstance.getInputStream());

        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = bis.read(b);
        response = new String(b, 0, stream);

        utilisateur=SerialisationUtilisateur.findUserInJson(response);

        return utilisateur;
    }

    public static void creationChatSimple(Utilisateur client, Utilisateur contact) throws IOException {
        //TODO (protocole CREATION_CHAT_SIMPLE)
    }

    public static void creationChatGroupe(Utilisateur client, ArrayList<Utilisateur>  contact) throws IOException {
        //TODO (protocole CREATION_CHAT_GROUPE)
    }

    public static void envoiMessage(Utilisateur expediteur, Utilisateur destinataire) throws IOException {
        String requete = ProtocoleCode.MESSAGE+ delimiteur +expediteur.getPseudo()+ delimiteur +Singletons.getInputPanel().getTextField().getText();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
    }

    public static void deconnexion(Utilisateur utilisateur) throws IOException {
        String requete = ProtocoleCode.DECONNEXION + delimiteur + utilisateur.getPseudo();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
        socketInstance.close();
    }
}
