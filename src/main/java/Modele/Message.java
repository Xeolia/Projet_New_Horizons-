package Modele;

import java.util.Date;

/**
 * La classe message défini l'objet message dans une discussion
 */
public class Message {

    /**
     * Identifiant de la discussion
     */
    public String id;

    /**
     * Texte a l'intérieur du message
     */
    public String text;

    /**
     * Expediteur du message
     */
    public String expediteur;

    /**
     * Constructeur de la classe Message
     * @param id identifiant du message
     * @param text contenu du message
     * @param expediteur expéditeur du message
     */
    public Message(String id,String text, String expediteur){
        this.id = id;
        this.text = text;
        this.expediteur = expediteur;
    }


    /**
     * Cette méthode permet d'avoir le contenue d'un message
     * @return contenue du message
     */
    public String getText() {
        return text;
    }

    /**
     * Permet d'avoir l'expéditeur du message
     * @return l'expéditeur du message
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Permet d'avoir l'identifiant du message
     * @return idenfiant du message
     */
    public String getId() {
        return id;
    }
}
