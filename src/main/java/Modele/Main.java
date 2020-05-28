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
        Thread t = new Thread(new ClientConnexion(socket," "));
        t.start();

        while (true) {
            String messageServeur;
            Scanner sc = new Scanner(System.in);
            System.out.print("Ecrire le texte a envoyer au serveur :\n>");
            messageServeur = sc.nextLine();
            writer.write(messageServeur);
            writer.flush();
        }
    }
}
