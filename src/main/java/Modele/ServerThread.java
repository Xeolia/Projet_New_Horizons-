package Modele;

import java.io.*;
import java.net.Socket;

/**
 * La classe ServerThread créer un serveur dans un thread et fonctionne en permanance
 *
 * @author Alexia Serrier
 * @version 1.0
 */

public class ServerThread extends Thread {

    /**
     * Socket qui sera utilisé pour le serveur
     * @see Socket
     */
    private Socket connect;

    /**
     * numéro du thread
     */
    private int num;

    /**
     * Constructeur de la classe ServerThread
     * @param connect nouveau socket
     * @param num numéro de socket
     */
    public ServerThread(Socket connect , int num){
        this.connect=connect;
        this.num=num;
    }

    /**
     * Méthode permettant de créer un thread du serveur qui tourne en permanance
     */
    public void run (){

        try{
            BufferedReader reception = new BufferedReader(
                    new InputStreamReader(
                            connect.getInputStream())
            );

            PrintWriter envoi = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    connect.getOutputStream())), true);

            envoi.println("bonjour, saisir '!stop' pour arreter");
            String msg = reception.readLine();
            while(!(msg.equals("!stop"))){
                envoi.println(msg);
            }
            connect.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}