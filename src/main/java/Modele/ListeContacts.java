package Modele;

import java.util.ArrayList;
import java.util.List;

public class ListeContacts {

    private List<Personne> contact;

    public ListeContacts(){
        contact = new ArrayList<Personne>();
    }

    public void ajouterContact(String nom, String prenom, String pseudo){
        contact.add(new Personne(nom,prenom,pseudo));
    }

    public void supprimerContact(String pseudo){
        for(int index = 0; index<contact.size();index++){
            if(contact.get(index).getPseudo() == pseudo){
                contact.remove(index);
            }
        }
    }

    public List<Personne> getContact() {
        return contact;
    }
}
