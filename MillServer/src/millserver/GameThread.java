/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class GameThread extends Thread {
    
    Socket p1;
    Socket p2;
    private BufferedReader in;
    private PrintWriter out;
    ProtocolTokenizer pt = new ProtocolTokenizer();
    private volatile boolean finished = false;
    
    public GameThread(Socket s, Socket s2){
        p1 = s;
        p2 = s2;
    }
    
    public boolean isFinished(){
        return finished;
    }
    
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            out = new PrintWriter(p2.getOutputStream(),true);
            
            String text;
            while(!finished){
                text = in.readLine();
                    if(pt.getToken(1, text).equals("SET")){
                        System.out.println("doszło"+text);
                        out.println(text);
                    }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
