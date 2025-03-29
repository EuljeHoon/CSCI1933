package chess_codepack;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.

    /**
     * Make 8X8 board with 2D array.
     */
    public Board() {
        board = new Piece [8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        Piece piece = board[row][col];
        return piece;
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if(board[startRow][startCol] == null) {
            return false;
        }
        else {
            if(board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
                this.setPiece(endRow, endCol, getPiece(startRow, startCol));
                board[startRow][startCol].setPosition(endRow, endCol); //Set the start piece to end piece.
                board[startRow][startCol] = null; //Make previous location to a null.
                return true;
            }
            else {
                return false;
            }
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.

    /**
     * Checking the number of kings in the board, check game is over if there is less than two kings in the board.
     */
    public boolean isGameOver() {
        int numKings = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if(board[r][c] != null) {
                    if (board[r][c].getCharacter() == '\u265a' || board[r][c].getCharacter() == '\u2654') {
                        numKings += 1;
                    }
                }
            }
        }
        if (numKings == 2) {
            return false;
        }
        else {
            return true;
        }
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String str = "\t";
        for (int i = 0; i < board[0].length; i++) {
            str += i + "\t";
        }
        str += "\n";
        for (int r = 0; r < board.length; r++) {
            str += r + " | ";
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != null) {
                    str+=board[r][c] + " | ";
                }
                else {
                    str += " " + " | ";
                }
            }
            str += "\n";
        }
        return str;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - Both contain a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - Destination contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        boolean verifier;
        if(startRow >= 0 && startRow <= 7 && startCol >= 0 && startCol <= 7
                && endRow >= 0 && endRow <= 7 && endCol >= 0 && endCol <= 7
                && board[startRow][startCol] != null && (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack)
                && board[startRow][startCol].getIsBlack() == isBlack) {
            verifier = true;
        }
        else {
            verifier = false;
        }
        return verifier;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other

    /**
     *This method will check adjacent.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        boolean isRightPiece = false;

        if(!(startRow >= 0 && startRow <= 7 && startCol >= 0 && startCol <= 7
                && endRow >= 0 && endRow <= 7 && endCol >= 0 && endCol <= 7)) {
            return false;
        }
        if ((endRow == startRow - 1 && endCol == startCol) || (endRow == startRow + 1 && endCol == startCol)
                || (endCol == startCol + 1 && endRow == startRow) || (endCol == startCol - 1 && endRow == startRow)
                || (endRow == startRow - 1 && endCol == startCol - 1) || (endRow == startRow - 1 && endCol == startCol + 1)
                || (endRow == startRow + 1 && endCol == startCol - 1) || (endRow == startRow + 1 && endCol == startCol + 1)) { //Checks the all the cases that is adjacent.
            isRightPiece = true;
        }
        return isRightPiece;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (!(startRow >= 0 && startRow <= 7 && startCol >= 0 && startCol <= 7
                && endRow >= 0 && endRow <= 7 && endCol >= 0 && endCol <= 7)) { //Checks that the piece is in the right place to move.
            return false;
        }
        // Checks the cases that are not horizontal move.
        if (endRow != startRow) {
            return false;
        }
        else {
            if (endCol < startCol) { //Check left horizontal
                for (int i = startCol - 1; i > endCol; i --) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
            }
            else if (endCol > startCol) { //Check right horizontal
                for (int i = startCol + 1; i < endCol; i ++) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {

        if (!(startRow >= 0 && startRow <= 7 && startCol >= 0 && startCol <= 7
                && endRow >= 0 && endRow <= 7 && endCol >= 0 && endCol <= 7)) { //Checks that the piece is in the right place to move.
            return false;
        }
        //Checks the cases that are not vertical move.
        if (endCol != startCol) {
            return false;
        }
        else {
            if (endRow < startRow) { //Check vertical to upper part.
                for (int i = startRow - 1; i > endRow; i --) {
                    if (board[i][startCol] != null) {
                        return false;
                    }
                }
            }
            else if (endRow > startRow) { //Check vertical to lower part.
                for (int i = startRow + 1; i < endRow; i ++) {
                    if (board[i][startCol] != null) {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    //I used double for loop for this method. However, I think there might be the easier way to do it.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (!(startRow >= 0 && startRow <= 7 && startCol >= 0 && startCol <= 7
                && endRow >= 0 && endRow <= 7 && endCol >= 0 && endCol <= 7)) { //Checks that the piece is in the right place to move.
            return false;
        }
        //Checks the cases that are not diagonal move.
        if (endRow == startRow && endCol == startCol) {
            return false;
        }
        else {
            if (endRow < startRow && endCol > startCol) {//Up-Right
                if (startCol + startRow != endCol + endRow) {
                    return false;
                }
                int rowCount = startRow - 1;
                int colCount = startCol + 1;
                while (rowCount >= endRow + 1 && colCount <= endCol - 1) {
                    if (board[rowCount][colCount] != null) {
                        return false;
                    }
                    rowCount--;
                    colCount++;
                }
            }
            else if (endRow < startRow && endCol < startCol) { //Up-Left
                if (startRow - startCol != endRow - endCol) {
                    return false;
                }
                int rowCount = startRow - 1;
                int colCount = startCol - 1;
                while (rowCount >= endRow + 1 && colCount >= endCol + 1) {
                    if (board[rowCount][colCount] != null) {
                        return false;
                    }
                    rowCount--;
                    colCount--;
                }
            }
            else if (endRow > startRow && endCol < startCol) { //Down-Left
                if (startRow + startCol != endRow + endCol) {
                    return false;
                }
                int rowCount = startRow + 1;
                int colCount = startCol - 1;
                while (rowCount <= endRow - 1 && colCount >= endCol + 1) {
                    if (board[rowCount][colCount] != null) {
                        return false;
                    }
                    rowCount++;
                    colCount--;
                }
            }
            else if (endRow > startRow && endCol > startCol) { //Down-Right
                if (startRow - startCol != endRow - endCol) {
                    return false;
                }
                int rowCount = startRow + 1;
                int colCount = startCol + 1;
                while (rowCount <= endRow - 1 && colCount <= endCol - 1) {
                    if (board[rowCount][colCount] != null) {
                        return false;
                    }
                    rowCount++;
                    colCount++;
                }
            }
        }
        return true;
    }
}
