package chess_codepack;

public class Bishop {
    public Bishop(int row, int col, boolean isBlack) {
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

    //Check diagonal.
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)
                && board.verifyDiagonal(row, col, endRow, endCol)) {
            return true;
        }
        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
