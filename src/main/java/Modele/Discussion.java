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

public abstract class  Discussion {

    ObjectMapper mapper = new ObjectMapper();
    public int id;
    public List<Message> listeMessages;
    /*public List<Message> listeMessagesTemp;
    public Map<String, Object> Save = new HashMap<String, Object>();*/

    public Discussion(int id){
        this.id = id;
        listeMessages = new ArrayList<Message>();
    }


    public void envoyerMessage(Date time, String text, String expediteur, String destinataire){
        //listeMessagesTemp = new ArrayList<Message>();
        listeMessages.add(new Message(time, text, expediteur, destinataire));
        /*listeMessagesTemp.add(new Message(time, text, expediteur, destinataire));
        Save.put(expediteur, listeMessagesTemp);
        try {
            mapper.writeValue(new File("result.json"), Save);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    public int getId() {
        return id;
    }

    public List<Message> getListeMessages() {
        return listeMessages;
    }
}
