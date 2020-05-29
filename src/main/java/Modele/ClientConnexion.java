package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientConnexion implements Runnable {

    private Socket connexion;
    private PrintWriter writer;
    private BufferedInputStream reader;
    private static int count = 0;
    private String name = "Client-";

    public ClientConnexion(Socket socket, String nomClient) {
        //attribution numero client
        name += ++count;
        connexion = socket;
        name += nomClient;
    }


    public void run() {

        while(true) { //On autorise continuelement la connexion du client
            try {
                reader = new BufferedInputStream(connexion.getInputStream());
                //On envoie la commande au serveur
                //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
                //On attend la réponse
                String response = read();
                System.out.println("[CLIENT CONNEXION " + name + "] : Réponse reçue " + response);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Méthode pour lire les réponses du serveur
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
