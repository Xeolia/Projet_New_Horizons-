package Modele;

import Controleur.Controleur;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * La classe ChatThread créer un chat dans un thread et fonctionne en permanance
 *
 * @author Tanguy Bénard
 * @version 1.0
 */
public class ChatThread implements Runnable {

    /**
     * Initialisation de la variable isRunning
     */
    private boolean isRunning = true;


    /**
     * Constructeur de la classe ChatThread
     */
    public ChatThread() {
    }

    /**
     * Cette méthode permet de créer un thread qui écoute les réponses du serveur en permanence
     */
    public void run() {

        while (isRunning) { //On autorise continuelement la connexion de chat
            try {
                try {
                    String message = Serialisation.findSimpleDiscusionMessage(Controleur.idDiscussion);
                    if(message!=null){
                        Singletons.getChatPanel().getTextArea().setText(message);
                    }
                    Singletons.getMaFenetre().repaint();
                    Singletons.getMaFenetre().revalidate();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void close(){
        isRunning = false;
    }

    public void open(){
        isRunning = true;
    }
}
