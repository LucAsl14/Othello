package othello;

public class gameMaster {
    public static Board newGame(){
        Board b = new Board();
        return b;
    }
    public static int makeMove(int isWhite, Board b, char row, int col){
        if(row-'A'<0) return -1; // -1 == too high
        if(row-'H'>0) return -2; // -2 == too low
        if(!b.isPlayable(row, col)) return -3; // -3 == filled
        if(col>8) return -4; // -4 == too right
        if(col<1) return -5; // -5 == too left
        boolean effect = b.play(isWhite, row, col);
        if(!effect) return 0;
        return 1;
    }    
}
