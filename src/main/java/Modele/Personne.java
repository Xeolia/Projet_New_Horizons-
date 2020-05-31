package Modele;

/**
 * La classe Peronne définie l'objet personne
 *
 * @author Cédric PAQUIER
 * @version 1.0
 */
public class Personne{

    /**
     * nom de la personne
     */
    private String nom;

    /**
     * prénom de la personne
     */
    private String prenom;

    /**
     * pseudo de la personne
     */
    private String pseudo;

    /**
     * Constructeur de la classe Personne
     * @param nom nom de la nouvelle personne
     * @param prenom prénom de la nouvelle personne
     * @param pseudo pseudo de la nouvelle personne
     */
    public Personne(String nom, String prenom, String pseudo){
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    public Personne(){
        this.nom = null;
        this.prenom = null;
        this.pseudo = null;
    }

    /**
     * Cette méthode permet d'avoir le nom de la personne
     * @return nom de la personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode permet de changer le nom de la personne
     * @param nom nouveu nom de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Cette méthode permet d'avoir le prénom d'une personne
     * @return prénom de la personne
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Cette méthode permet de changer le prénom de la personne
     * @param prenom nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Cette méthode permet d'avoir le pseudo de la personne
     * @return
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Cette méthode permet de changer le pseudo de la personne
     * @param pseudo nouveau pseudo de la personne
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
