package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe DiscussionGroupe hérite de la classe Discussion et représente une discussion de groupe
 *
 * @author Cédric PASQUIER
 * @version 1.0
 */
public class DiscussionGroupe extends Discussion{

    /**
     * Liste d'utilisateurs pour la discussion
     * @see Personne
     */
    public ArrayList<Utilisateur> listUtilisateurs;

    /**
     * Constructeur de la classe DiscussionGroupe
     * @param id nouvelle identifiant de discussion
     * @param nom nom de la discussion
     * @param listeMessages liste de messages de la discussion
     * @param listUtilisateurs liste des utilisateurs
     */
    public DiscussionGroupe(String id, String nom, HashMap<String, String> listeMessages, ArrayList<Utilisateur> listUtilisateurs){
        super(id, nom, listeMessages);
        this.listUtilisateurs = listUtilisateurs;
    }

    /**
     * Cette méthode permet d'ajouter une personne dans la discussion
     * @param utilisateur utilisateur à ajouter dans la discussion
     */
    public void ajouterUtilisateur(Utilisateur utilisateur){
        listUtilisateurs.add(utilisateur);
    }

    /**
     * Cette méthode permet de retourner la liste de personnes de la discussion
     * @return la liste d'utilisateur
     */
    public ArrayList<Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }
}
