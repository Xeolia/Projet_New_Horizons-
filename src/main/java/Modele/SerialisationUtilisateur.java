package Modele;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SerialisationUtilisateur {
    public static Map<String, Object> SaveUserLogs = new HashMap<String, Object>();
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
            if (entry.getKey().equals(pseudo_connexion)){

                Map<?, ?> mapTemp = (Map) entry.getValue();
                for (Map.Entry<?, ?> entry2 : mapTemp.entrySet()) {
                    if(entry2.getKey() == "pseudo"){
                        pseudo_temp = (String) entry2.getValue();
                    }
                    if(entry2.getKey() == "nom"){
                        nom_temp = (String) entry2.getValue();
                    }
                    if(entry2.getKey() == "prenom"){
                        prenom_temp = (String) entry2.getValue();
                    }
                    if(entry2.getKey() == "password"){
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

    public static void insertUserToJson(Utilisateur utilisateur) throws IOException {
        SaveUserLogs.put(utilisateur.getPseudo(), utilisateur);
        try {
            mapper.writeValue(Paths.get("userData.json").toFile(), SaveUserLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
