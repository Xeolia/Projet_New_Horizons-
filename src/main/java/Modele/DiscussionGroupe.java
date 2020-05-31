package Modele;

import java.util.ArrayList;

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
    public ArrayList<Personne> listPersonnes;

    /**
     * Constructeur de la classe DiscussionGroupe
     * @param id l'id de la discussion
     */
    public DiscussionGroupe(int id){
        super(id);
    }

    /**
     * Cette méthode permet d'ajouter une personne dans la discussion
     * @param personne personne a ajouter dans la discussion
     */
    public void ajouterPersonne(Personne personne){
        listPersonnes.add(personne);
    }

    /**
     * Cette méthode permet de retourner la liste de personnes de la discussion
     * @return la liste de personnes
     */
    public ArrayList<Personne> getListPerssones() {
        return listPersonnes;
    }
}
