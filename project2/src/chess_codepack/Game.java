package chess_codepack;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int startRow, startCol, endRow, endCol;
        Scanner newScanner = new Scanner(System.in);
        Board board = new Board();

        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        boolean isBlack = false;

        /**
         * Until the game is over, keep continuing to printing the board with newStr that I set for the toString() method,
         * and asking the inputs ([start row] [start col] [end row] [end col]). Get inputs woth String and converted it to integer with parseInt().
         */
        while (board.isGameOver() == false) {
            String newStr = board.toString();
            System.out.println("Board:");
            System.out.println(newStr);
            if (isBlack == false) {
                System.out.println("It is currently white's turn to play.");
            }
            else {
                System.out.println("It is currently black's turn to play.");
            }
            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");

            startRow = newScanner.nextInt();
            startCol = newScanner.nextInt();
            endRow = newScanner.nextInt();
            endCol = newScanner.nextInt();

            /**
             * Keep asking the [start row] [start col] [end row] [end col] when the players put Invalid inputs.
             */
            while (board.getPiece(startRow, startCol).getIsBlack() != isBlack || board.movePiece(startRow, startCol, endRow, endCol) == false) {
                System.out.println("Invalid move. What is your move again? (format: [start row] [start col] [end row] [end col])");
                startRow = newScanner.nextInt();
                startCol = newScanner.nextInt();
                endRow = newScanner.nextInt();
                endCol = newScanner.nextInt();
            }
            isBlack = !isBlack;
        }
        if (isBlack == false) {
            System.out.println("Black has won the game!");
        }
        else {
            System.out.println("White has won the game!");
        }
    }
}
