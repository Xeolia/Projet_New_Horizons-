package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * La classe ChatThread créer un chat dans un thread et fonctionne en permanance
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class ChatThread implements Runnable {

    /**
     * Socket qui sera utilisé pour le serveur
     * @see Socket
     */
    private Socket connexion;

    /**
     * Permet d'envoyé des données
     * @see PrintWriter
     */
    private PrintWriter writer;

    /**
     * Buffer pour lire les informations entrante
     * @see BufferedInputStream
     */
    private BufferedInputStream reader;

    /**
     * Initialisation de la variable count à 0
     */
    private static int count = 0;

    /**
     * Initialisation de la variable name à "Client-"
     */
    private String name = "Client-";


    /**
     * Constructeur de la classe ChatThread
     * @param socket socket utilisé par l'utilisateur
     * @param nomClient nom du client
     */
    public ChatThread(Socket socket, String nomClient) {
        //attribution numero client
        name += ++count;
        connexion = socket;
        name += nomClient;
    }

    /**
     * Cette méthode permet de créer un thread qui écoute les réponses du serveur en permanence
     */
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

    /**
     * Cette méthode renvoie la récupérer les réponses serveur en string
     * @return réponse serveur en string
     * @throws IOException si la méthode reader.read() ne fonctionne pas
     */
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
