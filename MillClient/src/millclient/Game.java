/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millclient;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kuba
 */
public class Game extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form Game
     */
    private int setter = 0;
    boolean isFirst;
    String opponent;
    String nick;
    BufferedReader inp;
    PrintWriter out;
    ProtocolTokenizer pt = new ProtocolTokenizer();
    ImageIcon pawn;
    ImageIcon white;
    ImageIcon black;
    int [] board = new int [24];
    int pawnType = 0;
    volatile boolean finished = false;
    boolean initial = true;
    boolean listenerOn = true;
    ArrayList<JLabel> poleList = new ArrayList<>();
    JLabel previous = new JLabel();
    GameLogic gl = new GameLogic();
    int mills = 0;
    int pawns = 9;

    /**
     *
     * @param isFirst - określa czy zaczyna gre
     * @param inp - strumien wejscia
     * @param out - strumien wyjscia
     * @param op - nick opponenta
     * @param nick - nick gracza
     */
    public Game(boolean isFirst, BufferedReader inp, PrintWriter out, String op, String nick) {
        this.isFirst = isFirst;
        this.opponent = op;
        this.nick = nick;
        this.inp = inp;
        this.out = out;

        initComponents();
        int w = f9.getWidth();
        int h = f9.getHeight();
        white = new ImageIcon(new ImageIcon("img/whitePawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        black = new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        poleList.add(f9);poleList.add(f10);poleList.add(f11);
        poleList.add(f12);poleList.add(f13);poleList.add(f14);
        poleList.add(f15);poleList.add(f16);poleList.add(f17);
        poleList.add(f18);poleList.add(f19);poleList.add(f20);
        poleList.add(f21);poleList.add(f22);poleList.add(f23);
        poleList.add(f24);poleList.add(f25);poleList.add(f26);
        poleList.add(f27);poleList.add(f28);poleList.add(f29);
        poleList.add(f30);poleList.add(f31);poleList.add(f32);
        if(isFirst){
            jLabel33.setIcon(new ImageIcon(new ImageIcon("img/whitePawn.png").getImage().getScaledInstance(jLabel33.getWidth(), jLabel33.getHeight(), Image.SCALE_DEFAULT)));
            pawnType = 1;
            pawn = new ImageIcon(new ImageIcon("img/whitePawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            jLabel7.setText("Ustaw pionek");
        }
        else{
            jLabel33.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(jLabel33.getWidth(), jLabel33.getHeight(), Image.SCALE_DEFAULT)));
            pawnType = -1;
            pawn = new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        }
        jLabel8.setIcon(new ImageIcon(new ImageIcon("img/millBkg.png").getImage().getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_DEFAULT)));
        jLabel3.setText(nick);
        jLabel1.setText(opponent);
        /*jLabel9.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel10.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel11.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel12.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel13.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel14.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel15.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel16.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel31.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel30.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
        jLabel32.setIcon(new ImageIcon(new ImageIcon("img/blackPawn.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));*/
        f9.addMouseListener(this);
        f10.addMouseListener(this);
        f11.addMouseListener(this);
        f12.addMouseListener(this);
        f13.addMouseListener(this);
        f14.addMouseListener(this);
        f15.addMouseListener(this);
        f16.addMouseListener(this);
        f17.addMouseListener(this);
        f18.addMouseListener(this);
        f19.addMouseListener(this);
        f20.addMouseListener(this);
        f21.addMouseListener(this);
        f22.addMouseListener(this);
        f23.addMouseListener(this);
        f24.addMouseListener(this);
        f25.addMouseListener(this);
        f26.addMouseListener(this);
        f27.addMouseListener(this);
        f28.addMouseListener(this);
        f29.addMouseListener(this);
        f30.addMouseListener(this);
        f31.addMouseListener(this);
        f32.addMouseListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        f9 = new javax.swing.JLabel();
        f10 = new javax.swing.JLabel();
        f11 = new javax.swing.JLabel();
        f12 = new javax.swing.JLabel();
        f13 = new javax.swing.JLabel();
        f14 = new javax.swing.JLabel();
        f15 = new javax.swing.JLabel();
        f16 = new javax.swing.JLabel();
        f17 = new javax.swing.JLabel();
        f18 = new javax.swing.JLabel();
        f19 = new javax.swing.JLabel();
        f20 = new javax.swing.JLabel();
        f21 = new javax.swing.JLabel();
        f22 = new javax.swing.JLabel();
        f23 = new javax.swing.JLabel();
        f24 = new javax.swing.JLabel();
        f25 = new javax.swing.JLabel();
        f26 = new javax.swing.JLabel();
        f27 = new javax.swing.JLabel();
        f28 = new javax.swing.JLabel();
        f29 = new javax.swing.JLabel();
        f30 = new javax.swing.JLabel();
        f31 = new javax.swing.JLabel();
        f32 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(810, 669));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setText("Chat :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 390));

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 140, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 160, 530));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel1");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 190, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 190, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel4.setText("vs");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 770, 70));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(f9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));
        f9.getAccessibleContext().setAccessibleName("0");

        jPanel1.add(f10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 50, 50));
        f10.getAccessibleContext().setAccessibleName("1");

        jPanel1.add(f11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 50, 50));
        f11.getAccessibleContext().setAccessibleName("2");

        jPanel1.add(f12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 50, 50));
        f12.getAccessibleContext().setAccessibleName("3");

        jPanel1.add(f13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 50, 50));
        f13.getAccessibleContext().setAccessibleName("4");

        jPanel1.add(f14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 50, 50));
        f14.getAccessibleContext().setAccessibleName("5");

        jPanel1.add(f15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 50, 50));
        f15.getAccessibleContext().setAccessibleName("6");

        jPanel1.add(f16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 50, 50));
        f16.getAccessibleContext().setAccessibleName("7");

        jPanel1.add(f17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 50, 50));
        f17.getAccessibleContext().setAccessibleName("8");

        jPanel1.add(f18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 50, 50));
        f18.getAccessibleContext().setAccessibleName("9");

        jPanel1.add(f19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 50, 50));
        f19.getAccessibleContext().setAccessibleName("10");

        jPanel1.add(f20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 50, 50));
        f20.getAccessibleContext().setAccessibleName("11");

        jPanel1.add(f21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 50, 50));
        f21.getAccessibleContext().setAccessibleName("12");

        jPanel1.add(f22, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 50, 50));
        f22.getAccessibleContext().setAccessibleName("13");

        jPanel1.add(f23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 50, 50));
        f23.getAccessibleContext().setAccessibleName("14");

        jPanel1.add(f24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 50, 50));
        f24.getAccessibleContext().setAccessibleName("15");

        jPanel1.add(f25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 50, 50));
        f25.getAccessibleContext().setAccessibleName("16");

        jPanel1.add(f26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 50, 50));
        f26.getAccessibleContext().setAccessibleName("17");

        jPanel1.add(f27, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 50, 50));
        f27.getAccessibleContext().setAccessibleName("18");

        jPanel1.add(f28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 50, 50));
        f28.getAccessibleContext().setAccessibleName("19");

        jPanel1.add(f29, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 50, 50));
        f29.getAccessibleContext().setAccessibleName("20");

        jPanel1.add(f30, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 50, 50));
        f30.getAccessibleContext().setAccessibleName("21");

        jPanel1.add(f31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 50, 50));
        f31.getAccessibleContext().setAccessibleName("22");

        jPanel1.add(f32, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 50, 50));
        f32.getAccessibleContext().setAccessibleName("23");

        jLabel8.setAutoscrolls(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 430, 430));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 450, 450));
        jPanel1.getAccessibleContext().setAccessibleName("");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");
        jLabel5.setToolTipText("");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 260, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Twój kolor :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, -1));

        jButton1.setText("Poddaj Grę");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("jLabel5");
        jLabel7.setToolTipText("");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 260, -1));
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 80, 80));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 610, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });*/
    }
    
    /**
     * metoda aktualizująca planszę gry
     */
    public void updateBoard(){
        try {
                if(pawns < 3){
                    out.println("WIN}{"+opponent+"}{");
                    jLabel7.setForeground(Color.red);
                    jLabel7.setText("Porażka !");
                }
                else{
                    listenerOn = false;
                    jLabel7.setText("Ruch przeciwnika...");
                    String msg = inp.readLine();
                    System.out.println(msg);
                    if(pt.getToken(1, msg).equals("SET")){
                        System.out.println("update");
                        int pole = Integer.parseInt(pt.getToken(2, msg));
                        System.out.println(pole);
                        board[pole] = pawnType * -1;
                        for(int i=0; i<poleList.size(); i++){
                            if(poleList.get(i).getAccessibleContext().getAccessibleName().equals(pt.getToken(2, msg))){
                                poleList.get(i).setIcon(pawn);
                                break;
                            }
                        }
                        if(setter < 9)
                            jLabel7.setText("Ustaw pionek");
                        else
                            jLabel7.setText("Twój ruch");
                        listenerOn = true; 
                    }
                    else if((pt.getToken(1, msg).equals("WIN")) || ((pt.getToken(1, msg).equals("RESIGN")))){
                        jLabel7.setForeground(Color.CYAN);
                        jLabel7.setText("Zwycięstwo!");
                        jButton1.setText("Wyjdź");
                    }
                    else if(pt.getToken(1, msg).equals("MILL")){
                        if(Integer.parseInt(pt.getToken(3, msg)) > 0){
                            pawns--;
                            int pole = Integer.parseInt(pt.getToken(2, msg));
                            board[pole] = 0;
                            for(int i=0; i<poleList.size(); i++){
                                if(poleList.get(i).getAccessibleContext().getAccessibleName().equals(pt.getToken(2, msg))){
                                    poleList.get(i).setIcon(null);
                                    break;
                                }
                            }
                        }
                        else{
                            listenerOn = true;
                        }
                    }
                    else
                        listenerOn = true;
                }
                
                
            
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metoda wysyłająca komunikat o poddaniu.
     */
    public void resign(){
        out.println("RESIGN}{");
        finished = true;
        this.setVisible(false);
    }
    
    /**
     *
     * @return
     */
    public boolean isFinished(){
        return finished;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel f10;
    private javax.swing.JLabel f11;
    private javax.swing.JLabel f12;
    private javax.swing.JLabel f13;
    private javax.swing.JLabel f14;
    private javax.swing.JLabel f15;
    private javax.swing.JLabel f16;
    private javax.swing.JLabel f17;
    private javax.swing.JLabel f18;
    private javax.swing.JLabel f19;
    private javax.swing.JLabel f20;
    private javax.swing.JLabel f21;
    private javax.swing.JLabel f22;
    private javax.swing.JLabel f23;
    private javax.swing.JLabel f24;
    private javax.swing.JLabel f25;
    private javax.swing.JLabel f26;
    private javax.swing.JLabel f27;
    private javax.swing.JLabel f28;
    private javax.swing.JLabel f29;
    private javax.swing.JLabel f30;
    private javax.swing.JLabel f31;
    private javax.swing.JLabel f32;
    private javax.swing.JLabel f9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param me - listener klikniecia myszki na pole planszy
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if(listenerOn){
            String field = me.getComponent().getAccessibleContext().getAccessibleName();
            int pole = Integer.parseInt(field);
            if(setter < 9){
                if(board[pole] == 0){
                    board[pole] = pawnType;
                    out.println("SET}{"+pole+"}{"+opponent+"}{");
                    setter++;
                    javax.swing.JLabel pressed = (javax.swing.JLabel) me.getComponent();
                    pressed.setIcon(pawn);
                    System.out.println("pressed");
                    updateBoard();
                }
            }
            else{
                if(previous == null){
                    if(board[pole] == pawnType)
                        previous = (javax.swing.JLabel) me.getComponent();
                }
                else{
                    if(mills > 0){
                        if(board[pole] == pawnType * -1){
                            board[pole] = 0;
                            javax.swing.JLabel pressed = (javax.swing.JLabel) me.getComponent();
                            pressed.setIcon(null);
                            mills--;
                            out.println("MILL}{"+pole+"}{"+mills+"}{"+opponent+"}{");
                            if(mills == 0){
                                previous = null;
                                updateBoard();
                            }  
                        }
                    }
                    else{
                        if(board[pole] == 0){
                            board[pole] = pawnType;
                            javax.swing.JLabel pressed = (javax.swing.JLabel) me.getComponent();
                            pressed.setIcon(pawn);
                            previous.setIcon(null);
                            mills = gl.checkBoard(board, pawnType);
                            if(mills > 0)
                                jLabel7.setText("Zbij "+mills+"pionki");
                            else{
                                previous = null;
                                updateBoard();
                            } 
                        }
                    }
                          
                }
            }
        }
    }

    /**
     *
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param me
     */
    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
