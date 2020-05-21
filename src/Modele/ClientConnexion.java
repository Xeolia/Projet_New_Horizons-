package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientConnexion implements Runnable {

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private static int count = 0;
    private String name = "Client-";

    public ClientConnexion(Socket socket) {
        //attribution numero client
        name += ++count;
        connexion = socket;
    }


    public void run() {

        //nous n'allons faire que 10 demandes par thread...
        // for(int i =0; i < 10; i++){
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            reader = new BufferedInputStream(connexion.getInputStream());
            //On envoie la commande au serveur
            //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
            //On attend la réponse
            String response = read();
            System.out.println("\t * " + name + " : Réponse reçue " + response);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
/*
    public static void main(String[] args) {
        ClientConnexion client = new ClientConnexion("127.0.0.1", 1515);
        client.run();
    }*/

}
