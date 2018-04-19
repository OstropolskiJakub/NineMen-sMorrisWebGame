package millserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class ClientThread extends Thread {
    
    private Socket s;
    private int id;
    private String Nick;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isFinished = false;
    private boolean inGame = false;
    ProtocolTokenizer pt = new ProtocolTokenizer();
    private volatile ArrayList<ClientThread> list;
    
    /**
     * konstruktor wątku klienta
     * @param s - gniazdo
     * @param id - id klienta
     * @param n - nick klienta
     * @param t - lista graczy
     */
    public ClientThread(Socket s, int id, String n, ArrayList<ClientThread> t){
        this.s = s;
        this.id = id;
        Nick = n;
        list = t;
    }
    
    /**
     * zwraca id klienta
     * @return
     */
    public int getID(){
        return id;
    }
    
    /**
     * zwraca nick klienta
     * @return
     */
    public String getNick(){
        return Nick;
    }
    
    /**
     * zwraca socket klienta
     * @return
     */
    public Socket getSocket(){
        return s;
    }
    
    /**
     * zwraca stan zakonczenia wątku
     * @return
     */
    public boolean isFinished(){
        return isFinished;
    }
    
    /**
     * zwraca, czy klient jest zajęty grą
     * @return
     */
    public boolean isInGame(){
        return inGame;
    }
    
    /**
     * ustawia id klienta
     * @param id - id klienta
     */
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * wysyła komendę do klienta
     * @param cmd - komenda do przesłania
     */
    public void send(String cmd){
        System.out.println("test" + Nick);
        out.println(cmd);
    }
    
    /**
     * wysyła wszystkim komendę
     * @param n - komenda do przesłania
     */
    public void sendToAll(String n){
        for(int i=0; i<list.size(); i++){
            list.get(i).send(n);
        }
    }
    
    /**
     * wysyła konkretnemu klientowi komendę
     * @param t - komenda do przesłania
     * @param n - klient
     */
    public void sendTo(String t, String n){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getNick().equals(n)){
                list.get(i).send(t);
                
                break;
            } 
        }
    }
    
    /**
     * główna metoda komunikująca się poprzez protokoły
     */
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(),true);
            
            String text;
            while(!isFinished){
                while((text = in.readLine()) != null){
                    if(pt.getToken(1, text).equals("QUIT")){
                        in.close();
                        out.close();
                        s.close();
                        isFinished = true;
                        break;
                    }
                    else if(pt.getToken(1, text).equals("LIST")){
                        //System.out.println("LIST");
                        String msg = "LIST}{";
                        for(int i=0; i<list.size(); i++){
                            msg += (list.get(i).getNick() + "}{");
                        }
                        out.println(msg);
                    }
                    else if(pt.getToken(1, text).equals("JOIN")){
                        sendTo(text,pt.getToken(2, text));
                        //System.out.println(list.size());
                    }
                    else if(pt.getToken(1, text).equals("SET")){
                        sendTo("SET}{"+pt.getToken(2, text),pt.getToken(3, text));
                    }
                    else if(pt.getToken(1, text).equals("TEXTALL")){
                        sendToAll(text);
                    }
                    else if(pt.getToken(1, text).equals("DECLINE")){
                        sendTo("DECLINE}{",pt.getToken(2, text));
                    }
                    else if(pt.getToken(1, text).equals("ACCEPT")){
                        sendTo(text,pt.getToken(3, text));
                        inGame = true;
                    }
                    else
                        System.out.println(text);
                }
                break;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
