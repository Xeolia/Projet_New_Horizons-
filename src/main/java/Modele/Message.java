package Modele;

import java.util.Date;

/**
 * La classe message défini l'objet message dans une discussion
 */
public class Message {

    public String id;
    public String text;
    public String expediteur;

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
     * @return
     */
    public Personne getSender() {
        return sender;
    }

    public String getId() {
        return id;
    }
}
