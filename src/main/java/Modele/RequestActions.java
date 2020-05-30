package Modele;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.*;


import Vue.ConnexionPanel;
import Vue.FrameError;
import Vue.InscriptionPanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.webkit.UtilitiesImpl;

public class RequestActions {
    public static Socket socketInstance;
    public static String delimiteur = "/-/";
    public static List<Object> listeMessagesTemp;
    public static Map<String, Object> SaveUserLogs = new HashMap<String, Object>();
    public static Map<String, Object> SaveLogsTemp = new HashMap<String, Object>();
    static ObjectMapper mapper = new ObjectMapper();
    public static File userDataSave = Paths.get("userData.json").toFile();
    public static Object temp;
    public static String tempS;
    static List<?> list = new ArrayList<>();



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
        Utilisateur utilisateur = new Utilisateur();
        ConnexionPanel connexionPanel = Singletons.getConnexionPanel();

        try {

            //List<Utilisateur> users = Arrays.asList(mapper.readValue(Paths.get("userData.json").toFile(), Utilisateur[].class));
            //users.forEach(System.out::println);


            //vérification Json de la connection
            Map<?, ?> map = mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
            String pseudo = null;
            String nom = null;
            String prenom = null;
            String password = null;
            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey().equals(connexionPanel.getFieldPseudo().getText())){
                    Map<?, ?> mapTemp = (Map) entry.getValue();
                    for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                        if(entry2.getKey() == "pseudo"){
                            pseudo = (String) entry2.getValue();
                            System.out.println(pseudo+" pseudo recup"); //il récupère
                        }
                        if(entry2.getKey() == "nom"){
                              nom = (String) entry2.getValue();
                            System.out.println(nom);
                        }
                        if(entry2.getKey() == "prenom"){
                             prenom = (String) entry2.getValue();
                            System.out.println(prenom);
                        }
                        if(entry2.getKey() == "password"){
                             password = (String) entry2.getValue();
                             System.out.println(password);
                        }

                    }

                }
            }

            utilisateur.setPseudo(pseudo);
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setPassword(password);

            System.out.println(utilisateur.getPseudo()+" "+utilisateur.getPassword()+" "+utilisateur.getNom()+" " + utilisateur.getPrenom());


            if(utilisateur.getPseudo() != null && utilisateur.getPassword() !=null && utilisateur.getPassword().equals(connexionPanel.getFieldMDP().getText())){
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
