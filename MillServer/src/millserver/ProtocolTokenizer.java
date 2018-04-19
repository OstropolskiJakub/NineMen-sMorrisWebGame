package millserver;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Kuba
 */
public class ProtocolTokenizer {
    
    StringTokenizer st;
    String result;
    
    /**
     * zwraca odpowiedni token ze stringa
     * @param i - pozycja tokena
     * @param s - string
     * @return
     */
    public String getToken(int i, String s){
        st = new StringTokenizer(s, "}{");
        
        for(int j=0; j<i; j++){
            if(st.hasMoreTokens()){
                result = st.nextToken();
            }
        }
        return result;
    }
    
    /**
     * zwraca reszte stringa po pierwszym tokenie
     * @param s - string
     * @return
     */
    public ArrayList<String> getRest(String s){
        ArrayList<String> lista = new ArrayList<>();
        st = new StringTokenizer(s, "}{");
        st.nextToken();
        while(st.hasMoreTokens()){
            lista.add(st.nextToken());
        }
        return lista;
    }
}
