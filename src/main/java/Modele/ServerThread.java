package Modele;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket connect;
    private int num;

    public ServerThread(Socket connect , int num){
        this.connect=connect;
        this.num=num;
    }

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