import Modele.ListeContacts;
import Modele.Personne;
import org.junit.Test;

import java.util.List;

public class testListeCont {
    @Test
    public void testListCont(){
        String nom = "a";
        String pre = "b";
        String pse = "c";
        ListeContacts contacts = new ListeContacts();

        contacts.ajouterContact(nom, pre, pse);
        List<Personne> p = contacts.getContact();
        assert (p.get(0).getNom().equals(nom));
        assert (p.get(0).getPrenom().equals(pre));
        assert (p.get(0).getPseudo().equals(pse));
    }

    @Test
    public void testSuppCont(){
        ListeContacts contacts = new ListeContacts();
        contacts.ajouterContact("a", "b", "c");
        contacts.supprimerContact("c");

        assert (contacts.getContact().isEmpty());
    }

}
