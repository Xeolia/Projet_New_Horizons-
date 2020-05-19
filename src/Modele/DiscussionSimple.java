package Modele;

import java.util.ArrayList;

public class DiscussionSimple extends Discussion{

    Utilisateur expediteur;


    public DiscussionSimple(int id, Utilisateur expediteur){
        super(id);
        this.expediteur = expediteur;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }
}
