package othello;

public class Board {
    private char[][] othelloBoard;
    private final int rows = 8;
    private final int cols = 8;

    private int[][] direction = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

    public Board(){
        othelloBoard = new char[rows+2][cols+2];
        for(int i=1; i<rows+2; i++){
            othelloBoard[i][0] = (char)(i+'@');
            for(int j=1; j<cols+2; j++){
                othelloBoard[i][j] = '.';
            }
        }
        for(int j=0; j<cols+1; j++){
            othelloBoard[0][j] = (char)(j+'0');
        }
        othelloBoard[4][4] = 'W';
        othelloBoard[4][5] = 'B';
        othelloBoard[5][4] = 'B';
        othelloBoard[5][5] = 'W';
    }

    public void printBoard(){
        for(int i=0; i<=rows; i++){
            for(int j=0; j<=cols; j++){
                System.out.print(" " + othelloBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] returnBoard(){
        return othelloBoard;
    }

    public int checkEffect(int row, int col, int[] dir, int startRow, int startCol, boolean isWhite){
        if(othelloBoard[row][col]=='.') return 0;
        if(row==0||col==0) return 0;
        if(isWhite==(othelloBoard[row][col]=='W')){
            for(int i=0; i<=Math.max(Math.abs(row-startRow),Math.abs(col-startCol)); i++){
                othelloBoard[startRow+dir[0]*i][startCol+dir[1]*i] = isWhite?'W':'B';
            }
            if(row==startRow+dir[0]&&col==startCol+dir[1]) return 0;
            return 1;
        }
        int toReturn = checkEffect(row+dir[0], col+dir[1], dir, startRow, startCol, isWhite);
        return toReturn;
    }

    private boolean isAvailable(char row, int col){
        return othelloBoard[row-'@'][col]=='.';
    }

    private boolean isLonely(char row, int col){
        for(int[]a:direction){
            char toCheck = othelloBoard[(row-'@')+a[0]][col+a[1]];
            if(toCheck=='W'||toCheck=='B') return false;
        }
        return true;
    }

    public boolean isPlayable(char row, int col){
        if(!isLonely(row, col)&&isAvailable(row, col)) return true;
        return false;
    }



    public boolean play(int isWhite, char row, int col){
        char toPlay = isWhite==1?'W':'B';
        int rowInt = row-'@';
        othelloBoard[rowInt][col] = toPlay;
        int result = 0;
        for(int i=0; i<8; i++){
            result += Math.max(result,checkEffect(rowInt+direction[i][0], col+direction[i][1], direction[i], rowInt, col, isWhite==1));
        }
        if(result==0) othelloBoard[rowInt][col] = '.';
        return result>0;
        // return true;
    }

    public boolean checkFullBoard(){
        for(int i=1; i<=rows; i++)
            for(int j=1; j<=cols; j++)
                if(othelloBoard[i][j]=='.')
                    return false;
        return true;
    }
    
    public String searchWinner(){
        int w = 0;
        int b = 0;
        for(int i=1; i<=rows; i++)
            for(int j=1; j<=cols; j++)
                if(othelloBoard[i][j]=='W') b++;
                else w++;
        return w>=b?"White wins"+w+':'+b:"Black wins"+b+':'+w;
    }
}
