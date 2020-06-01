import Modele.Utilisateur;
import org.junit.Test;

public class testeUser {

    @Test
    public void testUser() {
        Utilisateur u = new Utilisateur("nom", "prenom", "pseudo", "pwd");
        String s1 = u.getPassword();
        String s2 = u.getPseudo();

        assert (s1.equals("pwd") || s2.equals("pseudo= pseudo"));
    }
}
