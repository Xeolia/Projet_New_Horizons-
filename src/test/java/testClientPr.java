import Modele.ClientProcessor;
import org.junit.Test;

import java.net.Socket;

public class testClientPr {

    @Test
    public void testClientPr(){
        Socket s = new Socket();
        ClientProcessor c = new ClientProcessor(s);
        c.run();
    }
}
