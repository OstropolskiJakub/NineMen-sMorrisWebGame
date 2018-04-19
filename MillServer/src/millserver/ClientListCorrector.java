/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millserver;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class ClientListCorrector extends Thread {
    
    ArrayList<ClientThread> threadList;
    ArrayList<Integer> toDelete = new ArrayList<>();
    
    /**
     * konstruktor
     * @param list - lista klientów
     */
    public ClientListCorrector(ArrayList<ClientThread> list){
        threadList = list;
    }
    
    /**
     * główna metoda usuwająca nieaktywnych graczy.
     */
    public void run(){
        try {
            while(true){
                System.out.println("cykl");
                for(int i=0; i<threadList.size(); i++){
                    if(threadList.get(i).isFinished()){
                        System.out.println(threadList.get(i).getNick()+" rozlaczyl sie.");
                        threadList.remove(i);
                    }
                }
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientListCorrector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
