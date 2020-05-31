package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;

/**
 * La classe ClientProcessor permet les intéractions entre le client et le serveur
 *
 * @author Alexion Serrier, Thomas Carpentier
 * @version 1.0
 */

public class ClientProcessor implements Runnable {

    /**
     * Le socket du client
     * @see Socket
     */
    private Socket sock;
    /**
     * Permet d'envoyé des données vers le serveur
     * @see PrintWriter
     */
    private PrintWriter writer;

    /**
     * Buffer pour lire les informations entrante
     * @see BufferedInputStream
     */
    private BufferedInputStream reader;

    /**
     * Numéro du client
     */
    private int numClient;

    /**
     * Constructeur de la classe ClientProcessor
     * @param pSock
     *          socket utilisé par le client
     */
    public ClientProcessor(Socket pSock){
        sock = pSock;
    }

    /**
     * La méthode run permet le traitement lancé dans un thread séparé
     */
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

    /**
     * La méthode read nous permet de lire les réponses client
     * @return Une chaine de caractères de la réponse client
     * @throws IOException Si le reader.read() ne marche pas
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