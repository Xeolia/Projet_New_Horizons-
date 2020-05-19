package Modele;

import java.util.ArrayList;

public class DiscussionGroupe extends Discussion{

    public ArrayList<Utilisateur> listUtilisateurs;

    public DiscussionGroupe(int id){
        super(id);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur){
        listUtilisateurs.add(utilisateur);
    }

    public ArrayList<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
}
