package Modele;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe Serialisation permet de serializer des objets
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class Serialisation {
    /**
     * Liste des logs de l'utilisateur
     */
    public static Map<String, Utilisateur> SaveUserLogs = new HashMap<String, Utilisateur>();

    /**
     * Liste des logs d'un discussion simple
     */
    public static Map<String, DiscussionSimple> SaveSimpleDiscussionLogs = new HashMap<String, DiscussionSimple>();

    /**
     * Mapper d'objet
     */
    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * Cette méthode permet de récupérer un utilistauer dans un Json
     * @param pseudo_connexion pseudo de l'utilisateur souhaitant se connecter
     * @return l'objet utilisateur qui est connecté
     * @throws IOException si le mapper à une erreur
     */
    public static Utilisateur findUserInJson(String pseudo_connexion) throws IOException {
        //vérification Json de la connection
        Map<?, ?> map = mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
        Utilisateur utilisateur_connexion = new Utilisateur();
        String pseudo_temp = null;
        String nom_temp = null;
        String prenom_temp = null;
        String password_temp = null;
        // print map entries
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals(pseudo_connexion)) {

                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                    if (entry2.getKey() == "pseudo") {
                        pseudo_temp = (String) entry2.getValue();
                    }
                    if (entry2.getKey() == "nom") {
                        nom_temp = (String) entry2.getValue();
                    }
                    if (entry2.getKey() == "prenom") {
                        prenom_temp = (String) entry2.getValue();
                    }
                    if (entry2.getKey() == "password") {
                        password_temp = (String) entry2.getValue();
                    }
                }
            }
        }

        utilisateur_connexion.setPseudo(pseudo_temp);
        utilisateur_connexion.setNom(nom_temp);
        utilisateur_connexion.setPrenom(prenom_temp);
        utilisateur_connexion.setPassword(password_temp);

        return utilisateur_connexion;
    }

    /**
     * Cette méthode permet de récupérer une liste d'utilisateur dans un Json
     * @return une liste d'utilisateur
     * @throws IOException si le mapper a une erreur
     */
    public static String [] findListUsersInJson() throws IOException {
        //vérification Json de la connection
        Map<?, ?> map = mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
        String [] listePseudo = new String [1000];
        int i = 0;
        // print map entries
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            listePseudo[i] = (String) entry.getKey();
            i++;
        }

        return listePseudo;
    }

    /**
     * Cette méthode permet de récupérer une discussion simple dans un Json
     * @param id identifiant de la discussion
     * @return l'objet discussion correspondant
     * @throws IOException si le mapper à une erreur
     */
    public static Discussion findSimpleDiscusionInJson(String id) throws IOException {
        //vérification Json de la connection
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        DiscussionSimple discussionSimple = new DiscussionSimple();
        String nom = null;
        String utilisateur1 = null;
        String utilisateur2 = null;
        HashMap<String, HashMap<String,String>> listeMessages = null;
        // print map entries
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {

                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {

                    if (entry2.getKey() == "listeMessages") {
                        listeMessages = (HashMap) entry2.getValue();
                    }
                    if (entry2.getKey() == "nom") {
                        nom = (String) entry2.getValue();
                    }
                    if (entry2.getKey() == "utilisateur1") {
                        utilisateur1 = (String) entry2.getValue();
                    }
                    if (entry2.getKey() == "utilisateur2") {
                        utilisateur2 = (String) entry2.getValue();
                    }
                }
            }
        }

        discussionSimple.setId(id);
        discussionSimple.setNom(nom);
        discussionSimple.setListeMessages(listeMessages);
        discussionSimple.setUtilisateur1(utilisateur1);
        discussionSimple.setUtilisateur2(utilisateur2);

        return discussionSimple;
    }

    /**
     * Cette méthode permet de récupérer une liste de message dans une discussion
     * @param id l'identifiant de la discussion
     * @return la liste de messages
     * @throws IOException si le mapper à une erreur
     */
    public static String findSimpleDiscusionMessage(String id) throws IOException {
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        HashMap<String,String> listeMessages = new HashMap<>();
        String message = null;
        // print map entries
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {
                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                    if (entry2.getKey() == "listeMessages") {
                        Map<?, ?> mapTemp2 = (Map) entry2.getValue();
                        for (Map.Entry<?, ?> entry3 : mapTemp2.entrySet()) {
                            Map<?, ?> mapTemp3 = (Map) entry3.getValue();
                            for (Map.Entry<?, ?> entry4 : mapTemp3.entrySet()) {
                                if (message==null){
                                    message = (String) entry4.getKey()+ " : " + (String) entry4.getValue()+" \n";
                                }
                                else{
                                    message = message + (String) entry4.getKey()+ " : " + (String) entry4.getValue()+" \n";
                                }
                            }
                        }
                    }
                }
            }
        }


        return message;
    }

    /**
     * Cette méthode permet d'ajouter un message dans une discussion simple
     * @param id identifiant de la discussion
     * @param expediteur utilisateur qui envoie le message
     * @param message contenu du message
     * @throws IOException si le mapper à une erreur
     */
    public static void insertSimpleDiscusionMessage(String id, String expediteur, String message) throws IOException {
        DiscussionSimple discussion = (DiscussionSimple) findSimpleDiscusionInJson(id);

        HashMap<String, String> messageTemp = new HashMap<>();
        messageTemp.put(expediteur,message);
        int lastIdMessage = Integer.parseInt(findLastMessageId());
        lastIdMessage = lastIdMessage+1;

        discussion.getListeMessages().put(String.valueOf(lastIdMessage), messageTemp);
        Serialisation.insertSimpleDiscussionToJson(discussion);
    }

    /**
     * Cette méthode permet de trouver l'identifiant de la dernière discussion
     * @return l'identifiant de la dernière discussion
     * @throws IOException si le mapper à une erreur
     */
    public static String findLastDiscussionId() throws IOException {
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        String id =null ;
        String idMessages=null;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {
                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                    if (entry2.getKey() == "listeMessages") {
                        for (Map.Entry<?, ?> entry3 : mapTemp.entrySet()) {
                                idMessages = (String) entry3.getKey();
                        }
                    }
                }
            }
        }

        return idMessages;
    }

    /**
     * Cette méthode permet de trouver l'identifiant de la dernier message
     * @return l'identifiant de la dernière message
     * @throws IOException si le mapper à une erreur
     */
    public static String findLastMessageId() throws IOException {
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        String id =null ;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                    if (entry2.getKey() == "listeMessages") {
                        Map<?, ?> mapTemp2 = (Map) entry2.getValue();
                        for (Map.Entry<?, ?> entry3 : mapTemp2.entrySet()) {
                            id = (String) entry3.getKey();
                        }
                    }
                }
            }

        return id;
    }

    /**
     * Cette méthode permet d'ajouter un utilisateur dans le Json
     * @param utilisateur l'objet utilisateur à ajouter
     * @throws IOException si le mapper à une erreur
     */
    public static void insertUserToJson(Utilisateur utilisateur) throws IOException {
        SaveUserLogs = mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
        SaveUserLogs.put(utilisateur.getPseudo(), utilisateur);
        try {
            mapper.writeValue(Paths.get("userData.json").toFile(), SaveUserLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode permet de savoir si un utilisateur est dans une discussion
     * @param pseudo pseudo de l'utilisateur
     * @return la HashMap des discussion ou l'utilisateur est
     * @throws IOException si le mapper à une erreur
     */
    public static HashMap<String, String> isUserInDiscussion(String pseudo) throws IOException {
        HashMap<String,String> mapId = new HashMap<String,String>();
        String nomTemp = null;
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Map<?, ?> mapTemp = (Map) entry.getValue();
            for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                if (entry2.getKey() == "nom") {
                    nomTemp = (String) entry2.getValue();
                }
                if (entry2.getValue().equals(pseudo)) {
                    mapId.put((String) entry.getKey(), nomTemp);
                }
            }
        }
        return mapId;
    }

    /**
     * Cette méthode permet d'ajouter un discussion simple dans le Json
     * @param discussionSimple la discussion simple
     * @throws IOException si le mapper à une erreur
     */
    public static void insertSimpleDiscussionToJson(DiscussionSimple discussionSimple) throws IOException{
        SaveSimpleDiscussionLogs = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        SaveSimpleDiscussionLogs.put(discussionSimple.getId(), discussionSimple);
        try {
            mapper.writeValue(Paths.get("simpleDiscussionData.json").toFile(), SaveSimpleDiscussionLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
