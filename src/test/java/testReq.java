import Modele.RequestActions;
import Modele.Utilisateur;
import org.junit.Test;

import java.io.IOException;

public class testReq {

    @Test
    public void testReq(){
        Utilisateur utilisateur = null;
        try {
            utilisateur = RequestActions.connexion();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert (utilisateur.getPseudo().equals("Tanguy"));
        assert (utilisateur.getPassword().equals("password"));
    }

/*    @Test
    public void testEnvMsg(){
        Utilisateur utilisateur1 = null;
        Utilisateur utilisateur2 = null;
        try {
            utilisateur1 = RequestActions.connexion(); //entrer pseudo et password pas encore implementé
            utilisateur2 = RequestActions.connexion();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            RequestActions.envoiMessage(utilisateur1, utilisateur2);
            assert true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error " + e);
            assert false;
        }
    }*/

    @Test
    public void testDeco(){
        Utilisateur utilisateur = null;
        try {
        utilisateur = RequestActions.connexion();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            RequestActions.deconnexion(utilisateur);
            assert true;
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }

    }
}
