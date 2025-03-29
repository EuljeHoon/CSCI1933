package chess_codepack;

public class Knight {
    public Knight(int row, int col, boolean isBlack) {
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

    //Check the movement for knight.
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (Math.abs(endRow - row) == 2 && Math.abs(endCol - col) == 1) {
                return true;
            }
            else if (Math.abs(endRow - row) == 1 && Math.abs(endCol - col) == 2) {
                return true;
            }
            return false;
        }
        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
