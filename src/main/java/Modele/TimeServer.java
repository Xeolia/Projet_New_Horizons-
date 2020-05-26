package Modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class TimeServer {

    //On initialise des valeurs par défaut
    private int port = 1515;

    private ServerSocket server;
    private boolean isRunning = true;
    private Utilisateur utilisateur;
    private int countClients; // variable temp, utile en mode console
    private static String host = "127.0.0.1";
    public static HashMap<Socket, Utilisateur> listClients = new HashMap<Socket, Utilisateur> ();


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

    public void open(){

        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){

                    try {
                        //On attend une connexion d'un client
                        System.out.println("En attente d'un client numero " + countClients);
                        Socket sockClient = server.accept();
                        listClients.put(sockClient,utilisateur);
//                        listClients.forEach((s,u) -> System.out.println("user: "+u.toString()+" Socket:"+s)); //FIXME (affiche la liste des socket et des utilisateur, essentiellement pour debug)

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

    public void close(){
        isRunning = false;
    }

    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer();
        timeServer.open();
    }
}