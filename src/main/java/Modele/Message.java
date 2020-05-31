package Modele;

import java.util.Date;

/**
 * La classe message défini l'objet message dans une discussion
 */
public class Message {

    /**
     * Stock un date
     * @see Date
     */
    public Date time;

    /**
     * Texte a l'intérieur du message
     */
    public String text;

    /**
     * Personne qui envoie le message
     * @see Personne
     */
    public Personne sender;

    /**
     * identifiant de la discussion du message
     */
    public int idDiscussion;

    /**
     * Constructeur de la classe Message
     * @param time date actuelle du message
     * @param text texte du message
     * @param sender personne qui envoie le message
     * @param idDiscussion identifiant de la discussion
     */
    public Message(Date time, String text, Personne sender, int idDiscussion){
        this.time = time;
        this.text = text;
        this.sender = sender;
        this.idDiscussion = idDiscussion;
    }

    /**
     * Cette méthode permet d'avoir la date du message
     * @return date du message
     */
    public Date getTime() {
        return time;
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
     * @return
     */
    public Personne getSender() {
        return sender;
    }

    /**
     * Permet de récupérer l'identifiant de discussion du message
     * @return identifiant de discussion
     */
    public int getDiscussionID() {
        return idDiscussion;
    }
}
