package Modele;

import java.util.HashMap;
import java.util.List;

public abstract class  Discussion {

    public String id;
    public String nom;
    public HashMap<String, String> listeMessages;

    public void setListeMessages(HashMap<String, String> listeMessages) {
        this.listeMessages = listeMessages;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Discussion(String id, String nom, HashMap<String, String> listeMessages){
        this.id = id;
        this.nom = nom;
        this.listeMessages = listeMessages;
    }

    public Discussion(){
        this.id = null;
        this.nom = null;
        this.listeMessages = null;
    }
    public HashMap<String, String> getListeMessages() {
        return listeMessages;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
