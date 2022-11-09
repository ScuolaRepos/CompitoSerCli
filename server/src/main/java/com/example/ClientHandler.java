package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket s, String nome, ArrayList<ClientHandler> cls) {
        this.s = s;
        setName(nome);
        clients = cls;
        
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Boolean chatOn = true;
            pr.println("Benvenuto alla biglietteria! Inizia con i seguenti comandi: d - visualizza biglietti rimasti, a - compra un biglietto, q - disconnettiti");
            while(chatOn) {
                String input = br.readLine();
                
                if(App.numBiglietti == 0) {
                    for(int i = 0; i < clients.size(); i++) {
                        clients.get(i).pr.println("Biglietti esauriti!");
                    }
                }

                switch(input) {
                    default: {
                        pr.println("comando non riconosciuto, prova d, a o q");
                    }break;
                    case("d"): {
                        pr.println("biglietti disponibili: " + App.numBiglietti);
                    }break;
                    case("a"): {
                        if(App.numBiglietti > 0) {
                            pr.println("biglietto acquistato");
                            App.numBiglietti--;
                        } else {                          
                            if(App.numBiglietti == 0) {
                                for(int i = 0; i < clients.size(); i++) {
                                    clients.get(i).pr.println("Biglietti esauriti!");
                                }
                            }
                        }

                    }break;
                    case("q"): {
                        pr.println("disconnessione");
                        chatOn = false;
                    }break;
                }
            }
            
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}

