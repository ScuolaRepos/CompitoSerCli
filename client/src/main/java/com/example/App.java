package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Socket s = new Socket("localhost", 3000);
        Boolean loop = true;
        ConnessioneServer con = new ConnessioneServer(s);
        con.start();

        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        
        while(loop) {
            
            pr.println(tastiera.readLine());
        };
        br.close();
        pr.close();
        s.close();
    }
}
