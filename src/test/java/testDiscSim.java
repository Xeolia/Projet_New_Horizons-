import Modele.DiscussionSimple;
import Modele.Utilisateur;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testDiscSim {

    @Test
    public void testDisc1(){
        DiscussionSimple d = new DiscussionSimple();
        Utilisateur u1 = new Utilisateur("nom", "prenom", "pseudo", "pwd");
        Utilisateur u2 = new Utilisateur("nom2", "prenom2", "pseudo2", "pwd2");

        d.setUtilisateur1(u1.toString());
        d.setUtilisateur2(u2.toString());
    }

    @Test
    public void testDisc2(){

        ObjectMapper mapper = new ObjectMapper();

        DiscussionSimple d = new DiscussionSimple();
        Utilisateur u1 = new Utilisateur("nom", "prenom", "pseudo", "pwd");
        Utilisateur u2 = new Utilisateur("nom2", "prenom2", "pseudo2", "pwd2");

        d.setUtilisateur1(u1.toString());
        d.setUtilisateur2(u2.toString());

        System.out.println(d.toString());
        assert (d.toString() != null);

    }

}
