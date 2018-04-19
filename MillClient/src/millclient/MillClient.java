package millclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class MillClient extends Thread{

    private Socket s;
    private String host;
    private String nick;
    private volatile String status = "";
    private int port;
    ProtocolTokenizer pt = new ProtocolTokenizer();
    BufferedReader inp;
    PrintWriter out;
    
    /**
     * konstruktor
     * @param host - ip hosta
     * @param port - port hosta
     * @param nick - nick gracza
     */
    public MillClient(String host, int port, String nick){
        this.host = host;
        this.port = port;
        this.nick = nick;
    }

    /**
     * zwraca status programu
     * @return
     */
    public String getStatus(){
        return status;
    }

    /**
     * zwraca nick gracza
     * @return
     */
    public String getNick(){
        return nick;
    }

    /**
     * zwraca gniazdo
     * @return
     */
    public Socket getSocket(){
        return s;
    }
    
    /**
     * zamyka gniazda/strumienie
     */
    public void close(){
        try {
            out.println("QUIT}{");
            inp.close();
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MillClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * główna metoda łącząca się z serwerem i validująca nick
     */
    public void run(){
        Scanner in = new Scanner(System.in);
        try {
            s = new Socket(host,port);
            inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(),true);
            out.println(nick);
            String msg = inp.readLine();
            /*while((msg = inp.readLine()) != null){
                
            }*/
            System.out.println(msg);
            msg = pt.getToken(1, msg);
                
            if(msg.equals("REDUNDANT")){
                System.out.println(msg);
                status = "ERR";
                out.close();
                s.close();
            }
            else
                status = "OK";

            
                
            
        } catch (IOException ex) {
            status = "CONN_ERR";
        }
        
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MillClient m = new MillClient("localhost",3000,"asd");
        m.run();
    }
    
}
