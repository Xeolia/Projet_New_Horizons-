package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
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

                String response = read();
                InetSocketAddress remote = (InetSocketAddress) sock.getRemoteSocketAddress();

                System.out.println("===================================\n" +
                        "\tString recue du client n° " + numClient + "\n" +
                        "> " + response + "\n" +
                        "===================================");

                String toSend = "";
                String[] tableauReponse = response.split(RequestActions.delimiteur);
                ProtocoleCode code = ProtocoleCode.values()[Integer.parseInt(tableauReponse[0])];
                switch (code) {
                    case CREATION_COMPTE:
                        System.out.println("Creation de contact");
                        //TODO ajout protocole (ajout sur le serveur JSON du compte si le pseudo n'est pas deja utilisé)
                        break;
                    case CONNEXION:
                        String pseudo_connexion =tableauReponse[1];
                        toSend = "Connexion de : " + pseudo_connexion;

                        System.out.println(toSend);
                        break;
                    case CREATION_CHAT_SIMPLE:
                        //TODO creation de discussion (un expediteur, un destinataire)
                        break;
                    case CREATION_CHAT_GROUPE:
                        //TODO creation de discussion (un expediteur, un destinataire)
                        break;
                    case MESSAGE:
                        String expediteur_message =tableauReponse[1];
                        String message =tableauReponse[2];
                        toSend = expediteur_message+ " : " + message;
                        System.out.println(toSend);
                        break;
                    case DECONNEXION:
                        String pseudo_deconnexion =tableauReponse[1];
                        System.out.println("Deconnexion de : " + pseudo_deconnexion);
                        sock.isClosed();
                        break;
                    default:
                        toSend = "Une erreur s'est produite, le code du protocole n'a pas été reconnu";
                        System.err.println(toSend);
                        break;
                }
                //Il FAUT IMPERATIVEMENT UTILISER flush()
                //Sinon les données ne seront pas transmises au client
                //et il attendra indéfiniment

                // on renvoie la reponse a tous les clients pour l'instant
                for (Socket s : TimeServer.listClients.keySet()) {
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

    //La méthode que nous utilisons pour lire les réponses client
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }


}