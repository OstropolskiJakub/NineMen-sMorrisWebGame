package millclient;

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
     * zwraca token z podanego stringa
     * @param i - numer tokenu do zwrocenia
     * @param s - string do tokenizacji
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
     * zwraca resztę ze stringa po 1 tokenie
     * @param s - string do obróbki
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
