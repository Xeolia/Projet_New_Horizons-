import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String host = "127.0.0.1";
        int port = 1515;

        TimeServer ts = new TimeServer(host, port);
        ts.open();

        System.out.println("Serveur initialisé.");

   //     for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new ClientConnexion(host, port));
            t.start();
      //  }
    }
}
