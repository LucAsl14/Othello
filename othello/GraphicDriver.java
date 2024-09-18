package othello;
import javax.swing.*;
import java.awt.event.*;

public class GraphicDriver extends JFrame implements ActionListener {
    private ImageIcon board = new ImageIcon("D:\\coding\\code\\game images\\othello-board.png");
    private ImageIcon white = new ImageIcon("D:\\coding\\code\\game images\\othello-black-piece.png");
    private ImageIcon black = new ImageIcon("D:\\coding\\code\\game images\\othello-white-piece.png");
    private ImageIcon green = new ImageIcon("D:\\coding\\code\\game images\\othello-green.png");
    JButton slot[][] = new JButton[8][8];
    String[] players = {"White", "Black"};
    Board b1;
    int turn = 0;

    public GraphicDriver(){
        super("Othello by Lucas :)");
        setLayout(null);  
        setSize(810,810);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        
        //Board
        JLabel shownBoard = new JLabel(board);
        shownBoard.setBounds(10,10,760,760);
        add(shownBoard);

        //Buttons
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                slot[i][j] = new JButton();
                slot[i][j].setBounds(10+j*95,12+i*95,90,90);
                slot[i][j].addActionListener(this);
                int I=i;
                int J=j;
                slot[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        slot[I][J].setIcon(turn%2==0?white:black);
                    }
                
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        char[][] toPrint = b1.returnBoard();
                        slot[I][J].setIcon(toPrint[I+1][J+1]=='.'?green:
                                           toPrint[I+1][J+1]=='B'?black:white);
                    }
                });
                add(slot[i][j]);
            }
        }

        //Game
        b1 = new Board();
        showBoard();
    }

    public void reset(){
        new GraphicDriver();
    }

    public void actionPerformed(ActionEvent e){
        checkloop: for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(e.getSource()==slot[i][j]){
                    int playable = gameMaster.makeMove(((turn+1)%2), b1, (char)(i+'A'), j+1);
                    // System.out.println((char)(i+(int)('A'))+""+(j+1));
                    if(playable!=1){
                        if(playable == -3) JOptionPane.showMessageDialog(GraphicDriver.this,"Filled or Lonely");
                        if(playable == -0) JOptionPane.showMessageDialog(GraphicDriver.this,"No Effect");
                    } else {
                        turn++;
                        break checkloop;
                    }           
                }
            }
        }
        showBoard();
        checkWin();
    }

    // private void clearButton(){
    //     for(int i=0; i<8; i++){
    //         for(int j=0; j<8; j++){
    //             int playable = gameMaster.makeMove(((turn+1)%2), b1, (char)(i+'A'), j+1);
    //             if(playable!=1){
    //                 slot[i][j].setEnabled(false);
    //             }
    //         }
    //     }
    // }

    private void checkWin(){
        if(b1.checkFullBoard()){
            // Winner of the game
            String winner = b1.searchWinner();
            JOptionPane.showMessageDialog(GraphicDriver.this,winner + " !");
        }
    }

    private void showBoard(){
        char[][] toPrint = b1.returnBoard();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(toPrint[i+1][j+1]=='B'){
                    slot[i][j].setIcon(black);
                } else if(toPrint[i+1][j+1]=='W'){
                    slot[i][j].setIcon(white);
                } else {
                    slot[i][j].setIcon(green);
                }
            }
        }
        // b1.printBoard();
    }

    public static void main(String[] args){
        new GraphicDriver();
    }
}
