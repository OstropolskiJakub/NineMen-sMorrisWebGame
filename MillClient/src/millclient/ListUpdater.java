package millclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class ListUpdater extends Thread {
    Socket s;
    PrintWriter out;
    boolean halted = false;
    
    /**
     * konstruktor z gniazdem
     * @param s
     */
    public ListUpdater(Socket s){
        try {
            this.s = s;
            out = new PrintWriter(s.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(LobbyLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * getter gniazda
     * @return
     */
    public Socket getSocket(){
        return s;
    }
    
    /**
     * metoda wstrzymująca klasę
     */
    public void halt(){
        halted = true;
    }
    
    /**
     * metoda wznawiająca klasę
     */
    public void unhalt(){
        halted = false;
    }
    
    /**
     * metoda zamykająca połączenie
     */
    public void close(){
        try {
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MillClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metoda wysyłająca co 3 sek. polecenie udostępnienia listy zalogowanych
     */
    public void run(){
        while(true){
            try {
                if(halted){
                    Thread.sleep(3000);
                }
                else{
                    out.println("LIST");
                    Thread.sleep(3000);
                }    
            } catch (InterruptedException ex) {
                Logger.getLogger(ListUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
