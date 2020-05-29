package Modele;

public class Utilisateur extends Personne{

    private String nom;
    private String prenom;
    private String pseudo;
    private String password;

    public Utilisateur(String nom, String prenom, String pseudo, String password){
        super(nom,prenom,pseudo);
        this.pseudo = pseudo;
        this.password = password;
        this.nom= nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "pseudo= " + pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
}
