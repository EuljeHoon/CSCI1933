package chess_codepack;

public class Rook {
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */

    //Check both horizontal and vertical
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
       if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
               && (board.verifyVertical(row, col, endRow, endCol)
               || board.verifyHorizontal(row, col, endRow, endCol))) {
           return true;
       }
       return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
