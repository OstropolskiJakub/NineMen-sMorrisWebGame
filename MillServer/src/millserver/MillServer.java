package millserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class MillServer extends Thread {

    private int port;
    private ServerSocket ss;
    private int id = 0;
    private ArrayList<ClientThread> threadList = new ArrayList<>();
    
    /**
     * konstruktor serwera
     * @param p - port
     */
    public MillServer(int p){
        port = p;
    }
    
    /**
     * główna metoda uruchamiająca serwer i przyjmująca klientów
     */
    public void run(){
        System.out.println("Serwer startuje...");
        try {
            ss = new ServerSocket(port);
            boolean rejected = false;
            ClientListCorrector cl = new ClientListCorrector(threadList);
            cl.start();
            while(true){
                Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(),true);
                System.out.println("New client");
                String nick = in.readLine();
                
                for(int i=0; i<threadList.size(); i++){
                    if(threadList.get(i).getNick().equals(nick)){
                        out.println("REDUNDANT}{"+nick+"}{");
                        rejected = true;
                        break;
                    }     
                }
                if(rejected){
                    s.close();
                    rejected = false;
                }
                else{
                    out.println("OK");
                    //Tworzenie watku obsugujacego klienta
                    ClientThread thread = new ClientThread(s,id,nick,threadList);
                    thread.start();//uruchamianie watku
                    id++;
                    //dodawanie watku do listy
                    threadList.add(thread);
                    System.out.println(threadList.size());
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(MillServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MillServer m = new MillServer(3000);
        m.run();
    }
}
