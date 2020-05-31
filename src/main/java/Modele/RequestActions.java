package Modele;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


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
     * liste de message en mémoire
     * @see Message
     */
    public List<Message> listeMessagesTemp;

    /**
     * Map des logs sauvegarder
     */
    public static Map<String, Object> SaveLogs = new HashMap<String, Object>();

    /**
     * Map des
     */
    public static Map<String, Object> SaveLogsTemp = new HashMap<String, Object>();

    /**
     * Sert a mapper des objets
     * @see ObjectMapper
     */
    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Fichier Json des utilisateurs
     * @see File
     */
    public static File userDataSave = Paths.get("userData.json").toFile();

    //TODO CHIFFRER LES REQUETES ET FAIRE EN SORTE QU'ELLE NE PASSE PASSE PAS EN CLAIR

    /**
     * Cette méthodes permet d'envoyer la requête pour inscrir quelqu'un
     * @throws IOException Si le socket à une erreur
     */
    public static void inscription() throws IOException {
        //TODO
    }

    /**
     * Cette méthodes permet à l'utilisateur de se connecter
     * @return un utilisateur
     * @throws IOException Si le socket à une erreur
     */
    public static Utilisateur connexion() throws IOException {
        if(socketInstance == null){
            socketInstance = new Socket(TimeServer.host, 1515);
        }
        Utilisateur utilisateur = new Utilisateur("Tanguy", "Benard","Guytanfeu","password"); //TODO changer l'utilisateur avec les valeurs rentré dans le formulaire (après verification du serveur)
        TimeServer.listClients.put(socketInstance,utilisateur);
        SaveLogs.put("Tanguy4", "password");


        try {
            SaveLogsTemp =  mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
            for (Map.Entry<?, ?> entry : SaveLogsTemp.entrySet()) {
                SaveLogs.put((String)entry.getKey() ,entry.getValue());
            }
            mapper.writeValue(Paths.get("userData.json").toFile(), SaveLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread t = new Thread(new ClientConnexion(socketInstance, utilisateur.getPseudo()));
        return utilisateur;
    }

    /**
     * Cette méthode permet de créer un chat simple sur le server
     * @param client qui créé la discussion
     * @param contact la personne visé par la discussion
     * @throws IOException Si le socket à une erreur
     */
    public static void creationChatSimple(Utilisateur client, Utilisateur contact) throws IOException {
        //TODO (protocole CREATION_CHAT_SIMPLE)
    }

    /**
     * Cette méthode permet de créer un chat groupe
     * @param client qui créé la discussion
     * @param contact list de contact du groupe
     * @throws IOException Si le socket à une erreur
     */
    public static void creationChatGroupe(Utilisateur client, ArrayList<Utilisateur>  contact) throws IOException {
        //TODO (protocole CREATION_CHAT_GROUPE)
    }

    /**
     * Cette méthode permet d'envoyer un message
     * @param expediteur perseonne qui envoie le message
     * @param idDiscussion l'identifiant de la discussion du message
     * @throws IOException Si le socket à une erreur
     */
    public static void envoiMessage(Utilisateur expediteur, int idDiscussion) throws IOException {
        String requete = ProtocoleCode.MESSAGE+ delimiteur +expediteur.getPseudo()+ delimiteur +Singletons.getInputPanel().getTextField().getText();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
    }

    /**
     *Cette méthode permet de déconnecter l'utilisateur
     * @param utilisateur utilisateur souhaitant se déconnecter
     * @throws IOException Si le socket à une erreur
     */
    public static void deconnexion(Utilisateur utilisateur) throws IOException {
        String requete = ProtocoleCode.DECONNEXION + delimiteur + utilisateur.getPseudo();

        PrintWriter writer = new PrintWriter(socketInstance.getOutputStream());
        writer.write(requete);
        writer.flush();
        socketInstance.close();
    }
}
