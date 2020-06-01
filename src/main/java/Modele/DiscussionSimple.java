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
    /**
     * Première utilisateur de la discussion
     * @see Utilisateur
     */
    String utilisateur1;

    /**
     * Deuxième utilisateur de la discussion
     * @see Utilisateur
     */
    String utilisateur2;


    /**
     * Cette méthode permet de récupérer une description de la discussion en string
     * @return description de la discussion
     */
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

    /**
     * Cette méthode permet de changer le premier utilisateur
     * @param utilisateur1 premier utilisateur
     */
    public void setUtilisateur1(String utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    /**
     * Cette méthode permet de changer le deuxième utilisateur
     * @param utilisateur2 deuxième utilisateur
     */
    public void setUtilisateur2(String utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

    /**
     * Cette méthode permet de récupérer le premier utilisateur
     * @return premier utilisateur
     */
    public String getUtilisateur1() {
        return utilisateur1;
    }

    /**
     * Cette méthode permet de récupérer le deuxième utilisateur
     * @return deuxième utilisateur
     */
    public String getUtilisateur2() {
        return utilisateur2;
    }

    /**
     * Constructeur de la classe DiscussionSimple
     * @param id identifiant de la discussion
     * @param nom nom de la discusion
     * @param listeMessages liste de message de la discussion
     * @param utilisateur1 premier utilisateur
     * @param utilisateur2 deuxième utilisateur
     */
    public DiscussionSimple(String id, String nom, HashMap<String, String> listeMessages, String utilisateur1, String utilisateur2){
        super(id, nom, listeMessages);
        this.utilisateur1 = utilisateur1;
        this.utilisateur2 = utilisateur2;
    }

    /**
     * Constructeur de la classe DiscussionSimple avec des valeurs par défaut
     */
    public DiscussionSimple(){
        this.utilisateur1 = null;
        this.utilisateur2 = null;
    }

}
