package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de créer une liste de contact pour les utilisateurs
 *
 * @author Cédric PASQUIER
 * @version 1.0
 */

public class ListeContacts {

    /**
     * Liste de personnes
     * @see Personne
     */
    private List<Personne> contact;

    /**
     * Constructeur de la classe ListeContacts
     */
    public ListeContacts(){
        contact = new ArrayList<Personne>();
    }

    /**
     * Cette méthodes permet d'ajouter une personne à ses contacts
     * @param nom nom de la personne à ajouter
     * @param prenom prenom de la personne à ajouter
     * @param pseudo pseudo de la personne à ajouter
     */
    public void ajouterContact(String nom, String prenom, String pseudo){
        contact.add(new Personne(nom,prenom,pseudo));
    }

    /**
     * Cette méthodes permet de supprimer une personne de ses contacts
     * @param pseudo pseudo de la personne à enlever
     */
    public void supprimerContact(String pseudo){
        for(int index = 0; index<contact.size();index++){
            if(contact.get(index).getPseudo() == pseudo){
                contact.remove(index);
            }
        }
    }

    /**
     * Cette méthode retourne les contacts d'un utilisateur
     * @return liste de personnes
     */
    public List<Personne> getContact() {
        return contact;
    }
}
