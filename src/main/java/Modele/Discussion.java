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
