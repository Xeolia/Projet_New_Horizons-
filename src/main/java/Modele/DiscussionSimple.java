package Modele;

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
     * Première personne de la discussion
     * @see Personne
     */
    Personne personne1;

    /**
     * Deuxième personne de la discussion
     * @see Personne
     */
    Personne personne2;


    /**
     * Constructeur de la classe DiscussionSimple
     * @param id identifiant de la discussion
     * @param personne1 première personne de la discussion
     * @param personne2 deuxième personne de la discussion
     */
    public DiscussionSimple(int id, Personne personne1, Personne personne2){
        super(id);
        this.personne1 = personne1;
        this.personne2 = personne2;
    }

    /**
     * Cette méthode permet de retourner les deux personnes de la discussion
     * @return la liste des deux personnes de la discussion
     */
    public List<Personne> getPersonnes() {
        List<Personne> listPersonnes = new ArrayList<>();
        listPersonnes.add(personne1);
        listPersonnes.add(personne2);
        return listPersonnes;
    }
}
