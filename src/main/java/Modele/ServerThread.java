package Modele;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket conect;
    private int num;

    public ServerThread(Socket conect , int num){

        this.conect=conect;
        this.num=num;

    }

    public void run (){

        try{

            BufferedReader reception = new BufferedReader(
                    new InputStreamReader(
                            conect.getInputStream())
            );

            PrintWriter envoi = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    conect.getOutputStream())), true);

            envoi.println("bonjour, saisir stop pour arreter");
            String msg = reception.readLine();
            while(!(msg.equals("stop"))){


                envoi.println(msg);

            }
            conect.close();

        }catch (Exception e){

            System.out.println(e);

        }


    }

}