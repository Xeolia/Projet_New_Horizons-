package Modele;

import Vue.FrameError;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * La classe ClientProcessor permet les intéractions entre le client et le serveur
 *
 * @author Alexion Serrier, Thomas Carpentier
 * @version 1.0
 */

public class ClientProcessor implements Runnable {

    private Socket socket;
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
        socket = pSock;
    }

    /**
     * La méthode run permet le traitement lancé dans un thread séparé
     */
    public void run() {
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while (!socket.isClosed()) {

            try {
                writer = new PrintWriter(socket.getOutputStream());
                reader = new BufferedInputStream(socket.getInputStream());

                String response = read();
                InetSocketAddress remote = (InetSocketAddress) socket.getRemoteSocketAddress();

                System.out.println("===================================\n" +
                        "\tString recue du client n° " + numClient + "\n" +
                        "> " + response + "\n" +
                        "===================================");

                String log = "";
                String[] tableauReponse = response.split(RequestActions.delimiteur);
                ProtocoleCode code = ProtocoleCode.values()[Integer.parseInt(tableauReponse[0])];
                switch (code) {
                    case CREATION_COMPTE:
                        String nom_inscription = tableauReponse[1];
                        String prenom_inscription = tableauReponse[2];
                        String pseudo_inscription = tableauReponse[3];
                        String mdp_inscription = tableauReponse[4];
                        Utilisateur utilisateur = new Utilisateur(nom_inscription, prenom_inscription,pseudo_inscription,mdp_inscription);
                        Serialisation.insertUserToJson(utilisateur);

                        log = "Inscription de : " + pseudo_inscription;
                        System.out.println(log);
                        break;
                    case CONNEXION:
                        String pseudo_connexion =tableauReponse[1];
                        String mdp_connexion =tableauReponse[2];
                        Utilisateur utilisateur_connexion = new Utilisateur();

                        try {
                            utilisateur_connexion= Serialisation.findUserInJson(pseudo_connexion);
                            if(utilisateur_connexion.getPseudo() != null && utilisateur_connexion.getPassword() !=null && utilisateur_connexion.getPassword().equals(mdp_connexion)){
                                TimeServer.listClients.put(socket,utilisateur_connexion);
                                Thread t = new Thread(new ClientConnexion(RequestActions.socketInstance, utilisateur_connexion.getPseudo()));
                                log = "Connexion de : " + pseudo_connexion;
                                System.out.println(log);
                                String toSend = pseudo_connexion;
                                writer.write(toSend);
                                writer.flush();
                            }
                            else{
                                Thread t = new Thread(new ClientConnexion(RequestActions.socketInstance,  " "));
                                String toSend = " ";
                                writer.write(toSend);
                                writer.flush();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        break;
                    case CREATION_CHAT_SIMPLE:
                        String nom_discussion = tableauReponse[1];
                        String pseudo1 = tableauReponse[2];
                        String pseudo2 = tableauReponse[3];
                        int id = 0;
                        if(Serialisation.findLastDiscussionId()!=null) {
                            id = Integer.parseInt(Serialisation.findLastDiscussionId()) + 1;
                        }
                            DiscussionSimple discussionSimple = new DiscussionSimple(String.valueOf(id), nom_discussion, new HashMap<String, HashMap<String, String>>(), pseudo1, pseudo2);
                            Serialisation.insertSimpleDiscussionToJson(discussionSimple);

                            log = "Discussion créé : " + nom_discussion;
                            System.out.println(log);

                        break;
                    case CREATION_CHAT_GROUPE:
                        //TODO creation de discussion (liste d'utilisateur)
                        break;
                    case MESSAGE:
                        String idDiscussion =tableauReponse[1];
                        String expediteur_message =tableauReponse[2];
                        String message =tableauReponse[3];
                        log = expediteur_message+ " : " + message;
                        System.out.println(log);
                        Serialisation.insertSimpleDiscusionMessage(idDiscussion, expediteur_message, message);
                        boolean toSend = true;
                        for (Socket s : TimeServer.listClients.keySet()) {
                            writer.write(String.valueOf(toSend));
                            writer.flush();
                        }
                        break;
                    case DECONNEXION:
                        String pseudo_deconnexion =tableauReponse[1];
                        Utilisateur utilisateur_deconnexion = Serialisation.findUserInJson(pseudo_deconnexion);

                        if (utilisateur_deconnexion!=null){
                            if(TimeServer.listClients.containsValue(utilisateur_deconnexion.getPseudo()))
                            {
                                TimeServer.listClients.remove(utilisateur_deconnexion);
                            }
                        }

                        System.out.println("Deconnexion de : " + pseudo_deconnexion);
                        socket.isClosed();
                        break;
                    default:
                        log = "Une erreur s'est produite, le code du protocole n'a pas été reconnu";
                        System.err.println(log);
                        break;
                }
                //Il FAUT IMPERATIVEMENT UTILISER flush()
                //Sinon les données ne seront pas transmises au client
                //et il attendra indéfiniment

                // on renvoie la reponse a tous les clients pour l'instant
                for (Socket s : TimeServer.listClients.keySet()) {
                    System.out.println(s.toString());
                }

                if (closeConnexion) {
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    socket.close();
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