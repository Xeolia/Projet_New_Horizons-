package Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * La classe Discussion est abstraite, elle est utilisé pour la discussion de groupe ou simple
 * @see DiscussionGroupe
 * @see DiscussionSimple
 * @author Cédric Pasquier, Simon Ledoigt
 * @version 2.0
 */
public abstract class  Discussion {

    /**
     * Sert a mapper des objets
     * @see ObjectMapper
     */
    ObjectMapper mapper = new ObjectMapper();

    /**
     * L'id de la discussion
     */
    public int id;

    /**
     * Liste des messages d'une discussion
     * @see Message
     */
    public List<Message> listeMessages;
    /*public List<Message> listeMessagesTemp;
    public Map<String, Object> Save = new HashMap<String, Object>();*/

    /**
     * Constructeur de la classe Discussion
     * @param id identifiant de la discussion
     */
    public Discussion(int id){
        this.id = id;
        listeMessages = new ArrayList<Message>();
    }

    /**
     * Cette méthode enregistre le message d'un utilisateur dans la discussion
     *
     * @param time  l'heure a laquel le message est envoyé
     * @param text le contenue du message
     * @param sender la personne qui envoie le message
     * @param idDiscussion l'identifiant de la discussion
     * @throws Exception si le mapper.writeValue ne fonctionne pas
     */
    public void envoyerMessage(Date time, String text, Personne sender, int idDiscussion){
        //listeMessagesTemp = new ArrayList<Message>();
        listeMessages.add(new Message(time, text, sender, idDiscussion));
        /*listeMessagesTemp.add(new Message(time, text, expediteur, destinataire));
        Save.put(expediteur, listeMessagesTemp);
        try {
            mapper.writeValue(new File("result.json"), Save);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    /**
     * Cette méthode permet d'avoir l'id de la discussion
     * @return l'id de la discussion
     */
    public int getId() {
        return id;
    }

    /**
     * Cette méthode permet de retourner la liste de message de la discussion
     * @return la liste de message
     */
    public List<Message> getListeMessages() {
        return listeMessages;
    }
}
