package Modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {


    public static void main(String[] test) {

        ServerSocket socketserver;
        Socket socketduserveur;
        BufferedReader in;
        PrintWriter out;

        final Scanner sc = new Scanner(System.in);
        try {
            socketserver = new ServerSocket(1515);
            socketduserveur = socketserver.accept();
            out = new PrintWriter(socketduserveur.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
            String s;
            s = sc.next();
            out.println(s);
            out.flush();
            String message_client = null;
            try {
                message_client = in.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Client : " + message_client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}