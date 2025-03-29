package chess_codepack;
import java.util.Scanner;

public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        if (character == '\u2659' || character == '\u265F') {
            promote();
        }
    }

    /**
     * Returns the current chess unicode character.
     * @return Unicode character.
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other){
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }

    /**
     * This is the code for promote the piece. When a piece got to the end of the board, the function for promote will show up.
     * ex) Rook, Bishop, Queen, Knight.
     */
    public void promote() {
        if (isBlack && row == 7 || !isBlack && row == 0) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Promote to R for the Rook, B for the Bishop, Q for the Queen, K for the Knight: ");
            String s = scan.nextLine();
            if (s.equals("R")) { //Rook
                if (isBlack) {
                    character = '\u265C';
                }
                else {
                    character = '\u2656';
                }
            }
            else if (s.equals("B")) { //Bishop
                if (isBlack) {
                    character = '\u265D';
                }
                else {
                    character = '\u2657';
                }
            }
            else if (s.equals("Q")) { //Queen
                if (isBlack) {
                    character = '\u265B';
                }
                else {
                    character = '\u2655';
                }
            }
            else if (s.equals("K")) { //King
                if (isBlack) {
                    character = '\u265E';
                }
                else {
                    character = '\u2658';
                }
            }
            else {
                while (!s.equals("R") || !s.equals("B") || !s.equals("Q") || !s.equals("K")) { //When the player enter the wrong symbol beside R, B, Q, K.
                    System.out.println("Invalid. Try again.");
                    s = scan.nextLine();
                    if (s.equals("R")) {
                        if (isBlack) {
                            character = '\u265C';
                        }
                        else {
                            character = '\u2656';
                        }
                    }
                    else if (s.equals("B")) {
                        if (isBlack) {
                            character = '\u265D';
                        }
                        else {
                            character = '\u2657';
                        }
                    }
                    else if (s.equals("Q")) {
                        if (isBlack) {
                            character = '\u265B';
                        }
                        else {
                            character = '\u2655';
                        }
                    }
                    else if (s.equals("K")) {
                        if (isBlack) {
                            character = '\u265E';
                        }
                        else {
                            character = '\u2658';
                        }
                    }
                }
            }
        }

    }
}
