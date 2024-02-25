package Exercise3;



//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * @author Jeremy Sugimoto
 * The Board class represents a Tic-Tac-Toe game board.
 *
 */
public class Board implements Constants {
    private char theBoard[][];
    private int markCount;

    /**
     * Initializes a new game board with empty cells.
     */
    public Board() {
        markCount = 0;
        theBoard = new char[3][];
        for (int i = 0; i < 3; i++) {
            theBoard[i] = new char[3];
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        }
    }

    /**
     * Retrieves the mark at the specified row and column.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The mark ('X', 'O', or ' ') at the given position.
     */
    public char getMark(int row, int col) {
        return theBoard[row][col];
    }

    /**
     * Checks if the game board is full.
     *
     * @return {true} if the board is full, {false} otherwise.
     */
    public boolean isFull() {
        return markCount == 9;
    }

    /**
     * Checks if player X has won the game.
     *
     * @return {true} if X wins, {false} otherwise.
     */
    public boolean xWins() {
        if (checkWinner(LETTER_X) == 1)
            return true;
        else
            return false;
    }

    /**
     * Checks if player O has won the game.
     *
     * @return {true} if O wins, {false} otherwise.
     */
    public boolean oWins() {
        if (checkWinner(LETTER_O) == 1)
            return true;
        else
            return false;
    }

    /**
     * Displays the current state of the game board.
     */
    public void display() {
        displayColumnHeaders();
        addHyphens();
        for (int row = 0; row < 3; row++) {
            addSpaces();
            System.out.print("    row " + row + ' ');
            for (int col = 0; col < 3; col++)
                System.out.print("|  " + getMark(row, col) + "  ");
            System.out.println("|");
            addSpaces();
            addHyphens();
        }
    }

    /**
     * Adds a player's mark to the specified position on the board.
     *
     * @param row  The row index.
     * @param col  The column index.
     * @param mark The player's mark ('X' or 'O').
     */
    public void addMark(int row, int col, char mark) {
        theBoard[row][col] = mark;
        markCount++;
    }

    /**
     * Clears the game board by resetting all cells to empty.
     */
    public void clear() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        markCount = 0;
    }

    /**
     * Checks if a player with the given mark has won the game.
     *
     * @param mark The player's mark ('X' or 'O') to check for a win.
     * @return 1 if the player wins, 0 otherwise.
     */
    int checkWinner(char mark) {
        int row, col;
        int result = 0;

        // Check rows for a win
        for (row = 0; result == 0 && row < 3; row++) {
            int row_result = 1;
            for (col = 0; row_result == 1 && col < 3; col++)
                if (theBoard[row][col] != mark)
                    row_result = 0;
            if (row_result != 0)
                result = 1;
        }

        // Check columns for a win
        for (col = 0; result == 0 && col < 3; col++) {
            int col_result = 1;
            for (row = 0; col_result != 0 && row < 3; row++)
                if (theBoard[row][col] != mark)
                    col_result = 0;
            if (col_result != 0)
                result = 1;
        }

        // Check diagonal from top-left to bottom-right for a win
        if (result == 0) {
            int diag1Result = 1;
            for (row = 0; diag1Result != 0 && row < 3; row++)
                if (theBoard[row][row] != mark)
                    diag1Result = 0;
            if (diag1Result != 0)
                result = 1;
        }

        // Check diagonal from top-right to bottom-left for a win
        if (result == 0) {
            int diag2Result = 1;
            for (row = 0; diag2Result != 0 && row < 3; row++)
                if (theBoard[row][3 - 1 - row] != mark)
                    diag2Result = 0;
            if (diag2Result != 0)
                result = 1;
        }
        return result;
    }

    /**
     * Displays column headers above the game board.
     */
    void displayColumnHeaders() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("|col " + j);
        System.out.println();
    }

    /**
     * Adds hyphens to separate rows on the game board.
     */
    void addHyphens() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("+-----");
        System.out.println("+");
    }

    /**
     * Adds spaces to format cells within rows.
     */
    void addSpaces() {
        System.out.print("          ");
        for (int j = 0; j < 3; j++)
            System.out.print("|     ");
        System.out.println("|");
    }
}
