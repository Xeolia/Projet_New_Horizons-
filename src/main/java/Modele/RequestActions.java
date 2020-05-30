package Modele;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


import Vue.ConnexionPanel;
import Vue.FrameError;
import Vue.InscriptionPanel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestActions {
    public static Socket socketInstance;
    public static String delimiteur = "/-/";
    public List<Message> listeMessagesTemp;
    public static Map<String, Object> SaveUserLogs = new HashMap<String, Object>();
    public static Map<String, Object> SaveLogsTemp = new HashMap<String, Object>();
    static ObjectMapper mapper = new ObjectMapper();
    public static File userDataSave = Paths.get("userData.json").toFile();


    //TODO CHIFFRER LES REQUETES ET FAIRE EN SORTE QU'ELLE NE PASSE PASSE PAS EN CLAIR

    public static boolean inscription() throws IOException {
        if(socketInstance == null){
            socketInstance = new Socket(TimeServer.host, 1515);
        }
        InscriptionPanel inscriptionPanel = Singletons.getInscriptionPanel();
        if (inscriptionPanel.getFieldMDP().getText().equals(inscriptionPanel.getFieldMDPVerification().getText())){
            Utilisateur utilisateur = new Utilisateur(inscriptionPanel.getFieldNom().getText(), inscriptionPanel.getFieldPrenom().getText(),inscriptionPanel.getFieldPseudo().getText(),inscriptionPanel.getFieldMDP().getText());
            SaveUserLogs.put(utilisateur.getPseudo(), utilisateur);
            try {

                mapper.writeValue(Paths.get("userData.json").toFile(), SaveUserLogs);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            new FrameError();
            return false;
        }

        socketInstance.close();
        return false;
    }

    public static Utilisateur connexion() throws IOException {
        if(socketInstance == null){
            socketInstance = new Socket(TimeServer.host, 1515);
        }//TODO changer l'utilisateur avec les valeurs rentré dans le formulaire (après verification du serveur)
        Utilisateur utilisateur = null;
        ConnexionPanel connexionPanel = Singletons.getConnexionPanel();

        try {
            SaveLogsTemp = mapper.readValue(Paths.get("userData.json").toFile(), HashMap.class);
            for (Map.Entry<String, Object> entry : SaveLogsTemp.entrySet()) {
                SaveUserLogs.put(entry.getKey(), entry.getValue());
            }
            utilisateur = (Utilisateur) SaveUserLogs.get(connexionPanel.getFieldPseudo().getText());
            if(utilisateur != null && utilisateur.getPassword().equals(connexionPanel.getFieldMDP().getText())){
                TimeServer.listClients.put(socketInstance,utilisateur);
                Thread t = new Thread(new ClientConnexion(socketInstance, utilisateur.getPseudo()));
            }
            else{
                Singletons.getPanelError().getLabelError().setText(connexionPanel.getFieldPseudo().getText());
                new FrameError();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
