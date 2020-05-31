package Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException {
/*        Scanner scanNom = new Scanner(System.in);
        System.out.print("Renseignez votre pseudo :\n>");
        String nom = scanNom.nextLine();
        Socket socket = new Socket("127.0.0.1", 1515);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //Thread t = new Thread(new ClientConnexion(socket));
        //t.start();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ecrire le texte a envoyer au serveur :\n>");
            String messageServeur = sc.nextLine();
            writer.write(messageServeur);
            writer.flush();
        }*/
        HashMap<String, String> listMessage = new HashMap<>();
        listMessage.put("bobiiy", "Bonjour a toi guytanfeu");
        DiscussionSimple discussionSimple = new DiscussionSimple("5", "Famille", listMessage, "bobiiy","Guytanfeu");
        Serialisation.insertSimpleDiscussionToJson(discussionSimple);

        Discussion discussion= Serialisation.findSimpleDiscusionInJson("4");
        System.out.println("test");
    }
}
