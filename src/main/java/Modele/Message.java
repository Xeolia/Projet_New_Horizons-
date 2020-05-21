package Modele;

import java.util.Date;

public class Message {

    public Date time;
    public String text;
    public String expediteur;
    public String destinataire;

    public Message(Date time, String text, String expediteur, String destinataire){
        this.time = time;
        this.text = text;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
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
