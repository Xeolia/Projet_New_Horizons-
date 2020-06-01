package Modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * La classe TimeServer permet de connecter plusieurs utilisateurs au serveur
 *
 * @author Thomas Carpentier, Alexia Serrier
 */

public class TimeServer {

    /**
     * Initialisation de variable port
     */
    //On initialise des valeurs par défaut
    private int port = 1515;

    /**
     * Socket du serveur
     */
    private ServerSocket server;

    /**
     * Initialisation de la variable isRunning
     */
    private boolean isRunning = true;

    /**
     * Objet utilisateur
     * @see Utilisateur
     */
    private Utilisateur utilisateur;

    /**
     * Variable permettant de compter le nombre de client
     */
    private int countClients; // variable temp, utile en mode console

    /**
     * Initialisation de l'adresse d'hôte
     */
    public static String host = "127.0.0.1";

    /**
     * liste de clients
     */
    public static HashMap<Socket, Utilisateur> listClients = new HashMap<Socket, Utilisateur> ();

    /**
     * Constructeur de la classe TimeServer avec des valeurs par défaut
     */
    public TimeServer() {
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        countClients = 1;
    }

    /**
     * Constructeur de la classe TimeServer
     * @param pHost adresse de l'hôte
     * @param pPort numéro de port
     */
    public TimeServer(String pHost, int pPort){
        host = pHost;
        port = pPort;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        countClients = 1;

    }

    /**
     * Cette méthode permet d'ouvrir le serveur
     */
    public void open(){

        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){
                    try {
                        //On attend une connexion d'un client
                        System.out.println("En attente d'un client numero " + countClients);
                        Socket sockClient = server.accept();
                        listClients.put(sockClient,utilisateur);

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        countClients++;
                        Thread t = new Thread(new ClientProcessor(sockClient));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    /**
     * Cette méthode permet de fermer le server
     */
    public void close(){
        isRunning = false;
    }

    /**
     * Cette méthode permet de mettre en marche le serveur
     * @param args arguments de la méthode main
     */
    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer();
        timeServer.open();
    }
}