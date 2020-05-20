package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 1515);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        Thread t = new Thread(new ClientConnexion(socket));
        t.start();
        Boolean connexion = true;

        while (connexion) {
            String messageServeur;
            Scanner sc = new Scanner(System.in);
            messageServeur = sc.nextLine();
            System.out.print("Commande envoyé au serveur " + messageServeur);
            writer.write(messageServeur);
            writer.flush();

        }

    }

}
