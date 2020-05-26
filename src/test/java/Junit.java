import Modele.Singletons;
import Vue.MaFenetre;
import org.junit.Assert;
import org.junit.Test;

public class Junit {

    @Test
    public void testSingleton(){

        MaFenetre instance = null;
        instance = Singletons.getMaFenetre();
        Assert.assertNotNull(instance);
    }

}
