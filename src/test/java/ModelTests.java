
import Modele.Singletons;
import Modele.TimeServer;
import Vue.MaFenetre;
import org.junit.Assert;
import org.junit.Test;

public class ModelTests {


    @Test
    public void testSingleton(){

        MaFenetre instance = null;
        instance = Singletons.getMaFenetre();
        Assert.assertNotNull(instance);

    }

}
