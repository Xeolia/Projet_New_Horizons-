package Modele;

import java.util.Date;

public class Message {

    public String id;
    public String text;
    public String expediteur;

    public Message(String id,String text, String expediteur){
        this.id = id;
        this.text = text;
        this.expediteur = expediteur;
    }


    public String getText() {
        return text;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getId() {
        return id;
    }
}
