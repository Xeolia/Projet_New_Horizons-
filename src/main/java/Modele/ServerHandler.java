package Modele;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerHandler extends Thread {

    private ArrayList<Socket> listClients = new ArrayList<Socket>();
    private ArrayList<String> messagesAEnvoyer = new ArrayList<String>();

    boolean isRunning;

    // Constructeur inutilise
    /*
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
     */

    public synchronized void addClient(Socket sClient) {
        listClients.add(sClient);
    }

    public synchronized void broadCastMessage(String message) {
        System.out.println("Methode de diffusion");
        messagesAEnvoyer.add(message);
        System.out.println("Taille de l'arraylist : " + messagesAEnvoyer.size());
    }

    public void run() {
        try {
            while(true) {
                int taille = messagesAEnvoyer.size();
                System.out.println("Taille de l'arraylist : ");// + messagesAEnvoyer.size());
                String toSend = "";
                if(taille > 0) {
                    System.out.println("On rentre dans la condition");
                    toSend = messagesAEnvoyer.remove(0);
                    System.out.println("String envoyee a tous les clients : " + toSend);
                    for(Socket s : listClients) {
                        PrintWriter writerDiffusion = new PrintWriter(s.getOutputStream());
                        writerDiffusion.write(toSend);
                        writerDiffusion.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("On entre dans l'exception");
            e.printStackTrace();
        }
    }
}