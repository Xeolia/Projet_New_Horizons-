package Modele;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe hérite de la classe Discussion et représente une discussion simple
 *
 * @author Cédric PASQUIER
 * @version 1.0
 */
public class DiscussionSimple extends Discussion{

    String utilisateur1;

    public void setUtilisateur1(String utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    @Override
    public String toString() {
        return "DiscussionSimple{" +
                "utilisateur1='" + utilisateur1 + '\'' +
                ", utilisateur2='" + utilisateur2 + '\'' +
                ", id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", listeMessages=" + listeMessages +
                '}';
    }

    public void setUtilisateur2(String utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

    String utilisateur2;

    public String getUtilisateur1() {
        return utilisateur1;
    }

    public String getUtilisateur2() {
        return utilisateur2;
    }

    public DiscussionSimple(String id, String nom, HashMap<String, String> listeMessages, String utilisateur1, String utilisateur2){
        super(id, nom, listeMessages);
        this.utilisateur1 = utilisateur1;
        this.utilisateur2 = utilisateur2;
    }
    public DiscussionSimple(){
        this.utilisateur1 = null;
        this.utilisateur2 = null;
    }

}
