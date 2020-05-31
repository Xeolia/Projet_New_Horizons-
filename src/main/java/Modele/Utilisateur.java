package Modele;

/**
 * La classe Utilisateur définit l'objet utilisateur
 *
 * @author Cédric PASQUIER
 * @version 1.0
 */

public class Utilisateur extends Personne{

    /**
     * Nom de l'utilisateur
     */
    private String nom;

    /**
     * Prénom de l'utilisateur
     */
    private String prenom;

    /**
     * Pseudo de l'utilisateur
     */
    private String pseudo;

    /**
     * Password de l'utilisateur
     */
    private String password;

    /**
     * Constructeur de la classe Utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     * @param password password de l'utilisateur
     */
    public Utilisateur(String nom, String prenom, String pseudo, String password){
        super(nom,prenom,pseudo);
        this.pseudo = pseudo;
        this.password = password;
        this.nom= nom;
        this.prenom = prenom;
    }

    /**
     * Cette méthode permet d'afficher des informations sur l'utilisateur
     * @return desciption de l'utilisateur
     */
    @Override
    public String toString() {
        return "pseudo= " + pseudo;
    }

    /**
     * Cette méthode permet de récupérer le pseudo de l'utilisateur
     * @return le pseudo de l'utilisateur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Cette méthode permet de récupérer le password de l'utilisateur
     * @return le password de l'utilisateur
     */
    public String getPassword() { return password; }

    /**
     * Cette méthode permet de changer le password de l'utilisateur
     * @param password le nouveau password de l'utilisateur
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
