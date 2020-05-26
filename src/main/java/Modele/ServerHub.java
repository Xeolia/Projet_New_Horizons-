package Modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerHub {
    public static void main(String[] args) {
        // initialisation des variables principales
        int port = 1515;
        String host = "127.0.0.1";
        ServerSocket server = null;
        boolean isRunning = true;
        int countClients; // variable temp, utile en mode console

        // Creation du serversocket
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        countClients = 1;

        // Demarrage du gestionnaire de serveur
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.start();

        while(true){

            try {
                // On attend une connexion d'un client
                System.out.println("En attente d'un client numero " + countClients);

                Socket sockClient = server.accept();
                serverHandler.addClient(sockClient);

                //Une fois reçue, on la traite dans un thread séparé
                System.out.println("Connexion cliente reçue.");
                countClients++;
                Thread t = new Thread(new ClientProcessor(sockClient, serverHandler, countClients, ""));
                t.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
