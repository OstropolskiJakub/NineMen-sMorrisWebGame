package millclient;

/**
 *
 * @author Kuba
 */
public class GameLogic {
    
    /**
     * metoda okreslajaca ilosc zbić za ruchem
     * @param b - plansza w formie tablicy
     * @param type - typ pionka do ktorego ma porownać
     * @return
     */
    public int checkBoard(int[] b, int type){
        int mills = 0;
        if(b[0] == b[1] && b[1] == b[2] && b[2] == type){
            mills++;
        }
        if(b[2] == b[3] && b[3] == b[4] && b[4] == type){
            mills++;
        }
        if(b[4] == b[5] && b[5] == b[6] && b[6] == type){
            mills++;
        }
        if(b[6] == b[7] && b[7] == b[0] && b[0] == type){
            mills++;
        }
        if(b[8] == b[9] && b[9] == b[10] && b[10] == type){
            mills++;
        }
        if(b[10] == b[11] && b[11] == b[12] && b[12] == type){
            mills++;
        }
        if(b[12] == b[13] && b[13] == b[14] && b[14] == type){
            mills++;
        }
        if(b[14] == b[15] && b[15] == b[8] && b[8] == type){
            mills++;
        }
        if(b[16] == b[17] && b[17] == b[18] && b[18] == type){
            mills++;
        }
        if(b[18] == b[19] && b[19] == b[20] && b[20] == type){
            mills++;
        }
        if(b[20] == b[21] && b[21] == b[22] && b[22] == type){
            mills++;
        }
        if(b[22] == b[23] && b[23] == b[16] && b[16] == type){
            mills++;
        }
        if(b[1] == b[9] && b[9] == b[17] && b[17] == type){
            mills++;
        }
        if(b[3] == b[11] && b[11] == b[19] && b[19] == type){
            mills++;
        }
        if(b[5] == b[13] && b[13] == b[21] && b[21] == type){
            mills++;
        }
        if(b[7] == b[15] && b[15] == b[23] && b[23] == type){
            mills++;
        }
        return mills;
    }
}
