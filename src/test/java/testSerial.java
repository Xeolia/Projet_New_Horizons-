import Modele.Serialisation;
import Modele.Utilisateur;
import org.junit.Test;

import java.io.IOException;

public class testSerial {

    @Test
    public void testFindUser(){
        Utilisateur u = new Utilisateur("nom", "prenom", "pseudo", "pwd");
        try {

            Serialisation.insertUserToJson(u);
            Serialisation.findUserInJson("pseudo");

        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
        assert true;
    }
}
