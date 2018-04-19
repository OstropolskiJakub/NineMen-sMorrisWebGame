package millclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Kuba
 */
public class LobbyLogic extends Thread {
    Socket s;
    String nick;
    BufferedReader inp;
    PrintWriter out;
    ProtocolTokenizer pt = new ProtocolTokenizer();
    boolean finished = false;
    boolean inGame = false;
    DefaultListModel dl;
    JTextArea txt;
    
    /**
     * Konstruktor
     * @param s - referencja do gniazda
     * @param nick - nick gracza
     */
    public LobbyLogic(Socket s, String nick){
        try {
            this.s = s;
            this.nick = nick;
            inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
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
     * setter modelu listy graczy
     * @param dl
     */
    public void setList(DefaultListModel dl){
        this.dl = dl;
    }
    
    /**
     * setter okna czatu
     * @param txt
     */
    public void setTextArea(JTextArea txt){
        this.txt = txt;
    }
    
    /**
     * getter stanu, czy gracz jest zajęty grą
     * @return
     */
    public boolean isInGame(){
        return inGame;
    }
    
    /**
     * metoda wysyłająca komendę zaproszenia
     * @param player - nick zapraszanego
     */
    public void invite(String player){
        out.println("JOIN}{" + player + "}{" + nick);
    }
    
    /**
     * metoda wysyłająca do wszystkich wiadomość
     * @param nick - autor
     * @param content - zawartość wiadomości
     */
    public void textAll(String nick, String content){
        out.println("TEXTALL}{" + nick + "}{" + content + "}{");
    }
    
    /**
     * metoda zamykająca gniazda/kończąca 
     */
    public void close(){
        try {
            finished = true;
            out.println("QUIT}{");
            inp.close();
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MillClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * główna metoda z całą logiką
     */
    public void run(){
        while(!inGame){
            try {
                
                String msg = inp.readLine();
                String command = pt.getToken(1, msg);
                if(command.equals("LIST")){
                    dl.clear();
                    ArrayList<String> lista = pt.getRest(msg);
                    for(int i=0; i<lista.size(); i++){
                        if(!lista.get(i).equals(nick)){
                            dl.addElement(lista.get(i));
                            System.out.println("dodano");
                        }
                            
                    }
                }
                else if(command.equals("JOIN")){
                    //System.out.println("zaproszono");
                    if(pt.getToken(2, msg).equals(nick)){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(null,pt.getToken(3, msg)+" zaprasza do gry!", "Wyzwanie!", dialogButton);
                        if(dialogResult == 0) {
                            //inGame = true;
                            out.println("ACCEPT}{"+nick+"}{"+pt.getToken(3, msg)+"}{");
                            System.out.println(pt.getToken(3, msg));
                            Game g = new Game(false,new BufferedReader(new InputStreamReader(s.getInputStream())),new PrintWriter(s.getOutputStream(),true),pt.getToken(3, msg),pt.getToken(2, msg));
                            g.setVisible(true);
                            g.updateBoard();
                            inGame = true;
                        } else {
                            out.println("DECLINE}{"+pt.getToken(3, msg));
                        }
                    }
                }
                else if(command.equals("TEXTALL")){
                    txt.append(pt.getToken(2, msg) + " => " + pt.getToken(3, msg) + "\n");
                }
                else if(command.equals("DECLINE")){
                    JOptionPane.showMessageDialog(null, "Gracz odrzucił wyzwanie.");
                }
                else if(command.equals("ACCEPT")){
                    System.out.println(pt.getToken(2, msg));
                    Game g = new Game(true,new BufferedReader(new InputStreamReader(s.getInputStream())),new PrintWriter(s.getOutputStream(),true),pt.getToken(2, msg),pt.getToken(3, msg));
                    g.setVisible(true);
                    inGame = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(LobbyLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
