package Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class  Discussion {

    public int id;
    public List<Message> listeMessages;

    public Discussion(int id){
        this.id = id;
        listeMessages = new ArrayList<Message>();
    }


    public void envoyerMessage(Date time, String text, String expediteur, String destinataire){
        listeMessages.add(new Message(time, text, expediteur, destinataire));
    }

    public int getId() {
        return id;
    }

    public List<Message> getListeMessages() {
        return listeMessages;
    }
}
