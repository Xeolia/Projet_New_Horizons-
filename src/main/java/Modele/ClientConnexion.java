package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * La classe ClientConnexion permet la connexion du client au server
 *
 * @author Alexia Serrier, Thomas Carpentier
 * @version 1.0
 */
public class ClientConnexion implements Runnable {

    /**
     * Le socket du client
     * @see Socket
     */
    private Socket connexion;

    /**
     * Buffer pour lire les informations entrante
     * @see BufferedInputStream
     */
    private BufferedInputStream reader;

    /**
     * Initialisation d'un compteur
     */
    private static int count = 0;

    /**
     * Initialisation de la chaine de caractères name
     */
    private String name = "Client-";


    /**
     * Constructeur de la classe ClientConnexion
     *
     * @param socket
     *              nouveau socket de connexion du client
     * @param nomClient
     *              nom du client qui souhaite ce connecter
     */
    public ClientConnexion(Socket socket, String nomClient) {
        //attribution numero client
        name += ++count;
        connexion = socket;
        name += nomClient;
    }

    /**
     * La méthode run créer un thread qui écoute le serveur en permanance
     *
     * @throws IOException si jamais le reader est null
     * @throws InterruptedException si le sleep sur le currentThread ne marche pas
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

    //

    /**
     * La méthode read permet de lire les réponses du serveur
     *
     * @return la réponse du serveur en chaine de caractère
     * @throws IOException Si jamais le reader.read() ne fonctionne pas
     */
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
