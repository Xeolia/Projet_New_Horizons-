package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ClientProcessor implements Runnable {

    private Socket sock;
    private PrintWriter writer;
    private BufferedInputStream reader;
    private int numClient;
    private Hashtable listClients;

    public ClientProcessor(Socket pSock){
        sock = pSock;
    }
    public ClientProcessor(Socket sockClient, int countClients, Hashtable listClients1) {
        this.listClients = listClients1;
    }

    public int getNumClient() {
        return numClient;
    }

    public String getNom() {
        return nom;
    }

    private String nom;

    public ClientProcessor(Socket pSock, Hashtable listClients) {
        this.sock = pSock;
        this.listClients = listClients;
    }

    //Le traitement lancé dans un thread séparé
    public void run() {
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while (!sock.isClosed()) {

            try {
                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());

                //On attend la demande du client
                String response = read();
                InetSocketAddress remote = (InetSocketAddress) sock.getRemoteSocketAddress();

                /*
                //On affiche quelques infos, pour le débuggage
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
                debug += " Sur le port : " + remote.getPort() + ".\n";
                debug += "\t -> Commande reçue : " + response + "\n";
                System.err.println("\n" + debug);
                 */

                System.out.println("===================================\n" +
                        "\tString recue du client n° " + numClient + "\n" +
                        "> " + response + "\n" +
                        "===================================");

                //On traite la demande du client en fonction de la commande envoyée
                String toSend = "";
                String[] tableauReponse = response.split("::");
                Protocole code = Protocole.values()[Integer.parseInt(tableauReponse[0])];
                String message =tableauReponse[1];
                switch (code) {
                    case CREATION_COMPTE:
                        System.out.println("Creation de contact");
                        //TODO ajout protocole
                        break;
                    case CONNEXION:
                        toSend = "Connexion ";
                        System.out.println(toSend);
                        break;
                    case CREATION_CONTACT:
                        System.out.println(">> Saisissez le nom du répertoire où vous voulez aller : ");

                        break;
                    case MESSAGE:
                        toSend = "[SERVER] message : " + message;
                        System.out.println(toSend);
                        break;
                    case DECONNEXION:
                        System.out.println("GOOD BYE");
                        sock.isClosed();
                        break;
                    default:
                        toSend = "Erreur";
                        System.err.println(toSend);
                        break;
                }
                // System.out.println("Reponse serveur : " + toSend);
                //On envoie la réponse au client
                /*
                writer.write(toSend);
                writer.flush();
                 */
                //Il FAUT IMPERATIVEMENT UTILISER flush()
                //Sinon les données ne seront pas transmises au client
                //et il attendra indéfiniment

                // on renvoie la reponse a tous les clients
                for (Socket s : TimeServer.listClients) {
                    PrintWriter writerDiffusion = new PrintWriter(s.getOutputStream());
                    writerDiffusion.write(toSend);
                    writerDiffusion.flush();
                }

                if (closeConnexion) {
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                    break;
                }
            } catch (SocketException e) {
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                System.out.println(e.fillInStackTrace());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //La méthode que nous utilisons pour lire les réponses
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }


}