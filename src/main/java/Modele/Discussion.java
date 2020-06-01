package Modele;

import java.util.HashMap;
import java.util.List;


/**
 * La classe Discussion est abstraite, elle est utilisé pour la discussion de groupe ou simple
 * @see DiscussionGroupe
 * @see DiscussionSimple
 * @author Cédric Pasquier, Simon Ledoigt
 * @version 2.0
 */
public abstract class  Discussion {

    /**
     * identifiant de la discussion
     */
    public String id;

    /**
     * nom de la discussion
     */
    public String nom;

    /**
     * List des messages
     * @see HashMap
     */
    public HashMap<String, String> listeMessages;

    /**
     * Cette méthode permet de changer de la liste de messages
     * @param listeMessages nouvelle liste de messages
     */
    public void setListeMessages(HashMap<String, String> listeMessages) {
        this.listeMessages = listeMessages;
    }

    /**
     * Cette méthode permet de récupérer le nom de la discussion
     * @return le nom de la discussion
     */
    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode permet de changer le nom de la discussion
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Constructeur de la classe Discussion
     * @param id identifiant de la discussion
     * @param nom nom de la discussion
     * @param listeMessages List des messages
     */
    public Discussion(String id, String nom, HashMap<String, String> listeMessages){
        this.id = id;
        this.nom = nom;
        this.listeMessages = listeMessages;
    }

    /**
     * Constructeur de la discussion avec valeur par défaut
     */
    public Discussion(){
        this.id = null;
        this.nom = null;
        this.listeMessages = null;
    }

    /**
     * Cette méthode permet de récupérer la liste de message de la discussion
     * @return la liste de message
     */
    public HashMap<String, String> getListeMessages() {
        return listeMessages;
    }

    /**
     * Cette méthode permet de récupérer l'identifiant de la discussion
     * @return
     */
    public String getId(){
        return id;
    }

    /**
     * Cette méthode permet de changer l'identifiant de la discussion
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }



}
