package Modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TimeServer {

    //On initialise des valeurs par défaut
    private int port = 1515;
    private String host = "127.0.0.1";
    private ServerSocket server;
    private boolean isRunning = true;
    private int countClients; // variable temp, utile en mode console
    static ArrayList<Socket> listClients = new ArrayList<Socket>();

    public TimeServer(){
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


    //On lance notre serveur
    public void open(){

        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){

                    try {
                        //On attend une connexion d'un client
                        System.out.println("En attente d'un client numero " + countClients);
                        Socket sockClient = server.accept();

                        listClients.add(sockClient);

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        countClients++;
                        Thread t = new Thread(new ClientProcessor(sockClient, countClients));
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