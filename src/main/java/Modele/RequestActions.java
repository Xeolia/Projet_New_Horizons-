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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * La classe RequestActions permet d'envoyer des requêtes côté serveur
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class RequestActions {
    /**
     * Instance du socket qui envoie les requêtes
     * @see Socket
     */
    public static Socket socketInstance;

    /**
     * Permet de découper la requête
     */
    public static String delimiteur = "/-/";

    /**
     * Utilisateur connecté à l'application
     */
    public static Utilisateur utilisateur;

    //TODO CHIFFRER LES REQUETES ET FAIRE EN SORTE QU'ELLE NE PASSE PASSE PAS EN CLAIR

    /**
     * Cette méthode static permet d'inscire un utilisateur
     * @return un boolean si l'utilisateur est connecté
     * @throws IOException si le socket à une erreur
     */
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

    /**
     * Cette méthode permet à un utilisateur de se connecter
     * @return un utlisateur si la connexion est réussi
     * @throws IOException si le socket à une erreur
     */
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

    /**
     * Cette méthode permet de créer un chat simple
     * @param nom nom de la discussion simple
     * @param pseudo1 nom du première utilisateur
     * @param pseudo2 nom du deuxième utilisateur
     * @throws IOException si le socket a une erreur
     */
    public static void creationChatSimple(String nom,String pseudo1, String pseudo2) throws IOException {
        String requete = ProtocoleCode.CREATION_CHAT_SIMPLE+ delimiteur +nom+ delimiteur +pseudo1+ delimiteur +pseudo2;
        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();

    }

    /**
     * Cette méthode permet de créer un chat groupe
     * @param client qui créé la discussion
     * @param contact list de contact du groupe
     * @throws IOException si le socket à une erreur
     */
    public static void creationChatGroupe(Utilisateur client, ArrayList<Utilisateur>  contact) throws IOException {
        //TODO (protocole CREATION_CHAT_GROUPE)
    }

    /**
     * Cette méthode permet d'envoyer un message
     * @param idDiscussion identifiant de la discussion
     * @param expediteur utilisateur qui envoie le message
     * @param message contenu du message
     * @throws IOException si le socket à une erreur
     */
    public static void envoiMessage(String idDiscussion, String expediteur, String message) throws IOException {
        String requete = ProtocoleCode.MESSAGE+ delimiteur +idDiscussion+ delimiteur +expediteur+ delimiteur +message;

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
    }

    /**
     * Cette méthode permet à un utilisateur de se déconnecter
     * @throws IOException si le socket à une erreur
     */
    public static void deconnexion() throws IOException {
        String requete = ProtocoleCode.DECONNEXION + delimiteur + utilisateur.getPseudo();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
        socketInstance.close();
        socketInstance=null;
    }
}
