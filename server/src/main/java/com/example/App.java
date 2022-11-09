package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    static int numBiglietti = 5;
    public static void main( String[] args ) throws IOException
    {
        Boolean running = true;
        ServerSocket ss = new ServerSocket(3000);
        ArrayList<ClientHandler> clients = new ArrayList<>();
        System.out.println("Server in ascolto sulla porta 3000");

        while(running) {
        Socket s = ss.accept();
        System.out.println("Client connesso");
        ClientHandler client = new ClientHandler(s, "Server Maurizio", clients);
        clients.add(client);
        client.start();
        } 
        ss.close();
    }
}
