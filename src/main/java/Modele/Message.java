package Modele;

import java.util.Date;

public class Message {

    public Date time;
    public String text;
    public String expediteur;
    public String destinataire;

    public Message(String text, String expediteur){
        this.text = text;
        this.expediteur = expediteur;
    }

    public Date getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }
}
