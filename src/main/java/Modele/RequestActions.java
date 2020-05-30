package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


import Vue.ComboChoix;
import Vue.ConnexionPanel;
import Vue.FrameError;
import Vue.InscriptionPanel;

public class RequestActions {
    public static Socket socketInstance;
    public static String delimiteur = "/-/";
    public static Utilisateur utilisateur;

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

        utilisateur = new Utilisateur();
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

        utilisateur= Serialisation.findUserInJson(response);

        return utilisateur;
    }

    public static void creationChatSimple(String nom,String pseudo1, String pseudo2) throws IOException {
        String requete = ProtocoleCode.CREATION_CHAT_SIMPLE+ delimiteur +nom+ delimiteur +pseudo1+ delimiteur +pseudo2;
        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();

    }

    public static void creationChatGroupe(Utilisateur client, ArrayList<Utilisateur>  contact) throws IOException {
        //TODO (protocole CREATION_CHAT_GROUPE)
    }

    public static void envoiMessage(Discussion discussion, String expediteur, String message) throws IOException {
        String requete = ProtocoleCode.MESSAGE+ delimiteur +discussion.getId()+ delimiteur +expediteur+ delimiteur +message;

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
    }

    public static void deconnexion() throws IOException {
        String requete = ProtocoleCode.DECONNEXION + delimiteur + utilisateur.getPseudo();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
        socketInstance.close();
        socketInstance=null;
    }
}
