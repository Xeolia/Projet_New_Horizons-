import Modele.ProtocoleCode;
import org.junit.Test;


public class testProtocole {

    @Test
    public void testDeco() {
        ProtocoleCode a = ProtocoleCode.DECONNEXION;
        assert (a.toString().equals("5"));
    }

}
