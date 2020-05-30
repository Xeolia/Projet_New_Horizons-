package Modele;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Serialisation {
    public static Map<String, Utilisateur> SaveUserLogs = new HashMap<String, Utilisateur>();
    public static Map<String, DiscussionSimple> SaveSimpleDiscussionLogs = new HashMap<String, DiscussionSimple>();
    public static ObjectMapper mapper = new ObjectMapper();

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


    public static Discussion findSimpleDiscusionInJson(String id) throws IOException {
        //vérification Json de la connection
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        DiscussionSimple discussionSimple = new DiscussionSimple();
        HashMap<String, String> listeMessages = null;
        String nom = null;
        String utilisateur1 = null;
        String utilisateur2 = null;
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

    public static HashMap<String, String> findSimpleDiscusionMessage(String id) throws IOException {
        Discussion discussion = findSimpleDiscusionInJson(id);
        HashMap<String,String> mapMessage = null;

        return discussion.getListeMessages();
    }

    public static String findLastDiscussionId() throws IOException {
        Map<?, ?> map = mapper.readValue(Paths.get("simpleDiscussionData.json").toFile(), Map.class);
        String id =null ;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            id = (String) entry.getKey();
        }

        return id;
    }

    public static void insertUserToJson(Utilisateur utilisateur) throws IOException {
        SaveUserLogs = mapper.readValue(Paths.get("userData.json").toFile(), Map.class);
        SaveUserLogs.put(utilisateur.getPseudo(), utilisateur);
        try {
            mapper.writeValue(Paths.get("userData.json").toFile(), SaveUserLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
