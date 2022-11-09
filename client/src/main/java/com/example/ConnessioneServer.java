package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnessioneServer extends Thread{
    Socket s;
    PrintWriter pr;
    BufferedReader br;
    BufferedReader tastiera;

    public ConnessioneServer(Socket s) {
        try {
            this.s = s;
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // per la tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        String inputStr;
        Boolean chatOn  = true;

        while(chatOn) {
            try {
                inputStr = br.readLine();
                if(inputStr.equals("disconnessione")) {
                    System.out.println("chiudo la connessione alla biglietteria");
                    chatOn = false;
                    s.close();
                    System.exit(0);
                }
                else if(inputStr.equals("Biglietti esauriti!")) {
                    System.out.println("Biglietti esauriti! chiudo la connessione alla biglietteria");
                    chatOn = false;
                    s.close();
                    System.exit(0);
                }
                System.out.println("S:" + inputStr);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}