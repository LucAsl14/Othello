package othello;
import java.util.Scanner;

public class driver {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            // To start game or not
            System.out.print("Enter 'new' for new game or 'quit' to quit game: ");
            String nextCommand = sc.nextLine();
            if(nextCommand.equals("quit")) break;
            if(!nextCommand.equals("new")) continue;
            

            // Board setup
            Board b1 = gameMaster.newGame();
            b1.printBoard();
            int turn = 0;

            String[] players = {"White", "Black"};

            // Playing
            while(!b1.checkFullBoard()){
                int playable = 0;
                while(playable!=1){
                    System.out.println(players[turn%2] + ", make a move. Example: A1 ");
                    String next = sc.nextLine();
                    char moveRow = next.charAt(0);
                    int moveCol = next.charAt(1)-'0';
                    playable = gameMaster.makeMove(((turn+1)%2), b1, moveRow, moveCol);
                    if(playable!=1){
                        System.out.print("Invalid move: ");
                        if(playable == -1) System.out.println("Too high");
                        if(playable == -2) System.out.println("Too low");
                        if(playable == -3) System.out.println("Filled or Lonely");
                        if(playable == -4) System.out.println("Too far right");
                        if(playable == -5) System.out.println("Too far left");
                        if(playable == -0) System.out.println("No effect -- " + next);
                    }
                }
                b1.printBoard();
                turn++;
            }

            // Winner of the game
            String winner = b1.searchWinner();
            System.out.println(winner + " wins!");


        }
        sc.close(); 
    }   
}
