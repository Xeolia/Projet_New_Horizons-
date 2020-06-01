import Modele.Personne;
import org.junit.Test;

public class testPersonne {

    @Test
    public void testNom(){
        String nom = "a";
        String prenom ="b";
        String pseudo = "c";
        String testVariable = "";

       Personne p = new Personne("a",  "b", "c");

       assert (p.getNom().equals(nom));
       assert (p.getPrenom().equals(prenom));
       assert (p.getPseudo().equals(pseudo));
    }

    @Test
    public void testSet(){
        Personne p = new Personne("a",  "b", "c");

        p.setNom("nom");
        p.setPrenom("prenom");
        p.setPseudo("pseudo");

        assert (p.getNom().equals("nom"));
        assert (p.getPrenom().equals("prenom"));
        assert (p.getPseudo().equals("pseudo"));
    }

}
