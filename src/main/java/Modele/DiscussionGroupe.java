package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscussionGroupe extends Discussion{

    public ArrayList<Utilisateur> listUtilisateurs;

    public DiscussionGroupe(String id, String nom, HashMap<String, String> listeMessages, ArrayList<Utilisateur> listUtilisateurs){
        super(id, nom, listeMessages);
        this.listUtilisateurs = listUtilisateurs;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur){
        listUtilisateurs.add(utilisateur);
    }

    public ArrayList<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
}
