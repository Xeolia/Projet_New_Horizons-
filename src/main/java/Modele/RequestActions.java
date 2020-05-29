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
public class RequestActions {
    public static Socket socketInstance;
    public static String delimiteur = "/-/";
    public List<Message> listeMessagesTemp;
    public static Map<String, Object> SaveLogs = new HashMap<String, Object>();
    public static Map<String, Object> SaveLogsTemp = new HashMap<String, Object>();
    static ObjectMapper mapper = new ObjectMapper();
    public static File userDataSave = Paths.get("userData.json").toFile();

    //TODO CHIFFRER LES REQUETES ET FAIRE EN SORTE QU'ELLE NE PASSE PASSE PAS EN CLAIR

    public static void inscription() throws IOException {
        //TODO
    }

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
