package Modele;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Requete {
    private int port = 1515;
    private String host = "127.0.0.1";
    private Socket connexion = null;

    public Requete() {
        try {
            connexion = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ConnectionChat() throws IOException {
        //Nous allons faire une demande au serveur web
        //ATTENTION : les \r\n sont OBLIGATOIRES ! Sinon ça ne fonctionnera pas ! !
        String request = "GET /wiki/Digital_Learning HTTP/1.1\r\n";
        request += "Host: fr.wikipedia.org\r\n";
        request += "\r\n";
        
        //nous créons donc un flux en écriture
        BufferedOutputStream bos = new BufferedOutputStream(connexion.getOutputStream());

        //nous écrivons notre requête
        bos.write(request.getBytes());
        //Vu que nous utilisons un buffer, nous devons utiliser la méthode flush
        //afin que les données soient bien écrite et envoyées au serveur
        bos.flush();

        //On récupère maintenant la réponse du serveur
        //dans un flux, comme pour les fichiers...
        BufferedInputStream bis = new BufferedInputStream(connexion.getInputStream());

        //Il ne nous reste plus qu'à le lire
        String content = "";
        int stream;
        byte[] b = new byte[1024];
        while ((stream = bis.read(b)) != -1) {
            content += new String(b, 0, stream);
        }

    }
