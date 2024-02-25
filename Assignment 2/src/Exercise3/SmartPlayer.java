package Exercise3;

/**
 * @author Jeremy Sugimoto
 * This class represents a smart player in a Tic-Tac-Toe game that extends the BlockingPlayer class.
 * The SmartPlayer tries to make winning moves when possible, and if not, it tries to block the opponent.
 */
public class SmartPlayer extends BlockingPlayer {

	/**
     * Constructs a SmartPlayer with a given name and mark.
     *
     * @param name The name of the player.
     * @param mark The player's mark ('X' or 'O').
     */
	SmartPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}
	/**
     * Makes a move in a row to win the game if possible.
     *
     * @param row The row to make a winning move.
     */
	public void makeRowWinMove(int row) {
		for( int col = 0; col < 3; col++) {
			if(board.getMark(row, col) == SPACE_CHAR) {
				board.addMark(row, col, mark);
				break;
			}
		}
	}
	/**
     * Makes a move in a column to win the game if possible.
     *
     * @param col The column to make a winning move.
     */
	public void makeColWinMove(int col) {
		for( int row = 0; row < 3; row++) {
			if(board.getMark(row, col) == SPACE_CHAR) {
				board.addMark(row, col, mark);
				break;
			}
		}
	}
	/**
     * Makes a move in a diagonal to win the game if possible.
     *
     * @param rowStart The starting row of the diagonal.
     * @param colStart The starting column of the diagonal.
     * @param rowInc   The row increment for the diagonal.
     * @param colInc   The column increment for the diagonal.
     */
	public void makeDiagWinMove(int rowStart, int colStart, int rowInc, int colInc) {
		for (int i = 0; i < 3; i++) {
			if(board.getMark(rowStart, colStart) == SPACE_CHAR) {
				board.addMark(rowStart, colStart, mark);
				break;
		}
			rowStart += rowInc;
			colStart += colInc;
			}
		}
	/**
     * Makes a move for the SmartPlayer. It tries to make a winning move if possible,
     * otherwise, it makes a blocking move.
     */
	public void makeMove() {
		makeWinningMove();
		
	}

	/**
     * Makes a winning move if possible, otherwise makes a blocking move.
     */
	public void makeWinningMove() {
		if(checkWinCols())
			;
		else if(checkWinRows())
			;
		else if(checkWinDiag())
			;
		else makeBlockingMove()
			;
	}
		
	
	/**
     * Checks if there is a winning move in any row and makes a blocking move if found.
     *
     * @return True if a winning move is found and a blocking move is made, false otherwise.
     */
	private boolean checkWinRows() {
		for (int row = 0;row < 3; row++) {
			if(checkRowForWin(row)) {
				makeRowBlockingMove(row);
				return true;
		}
	}
		return false;
	}
	/**
     * Checks if a specific row contains a winning move.
     *
     * @param row The row to check for a winning move.
     * @return True if a winning move is found in the row, false otherwise.
     */
	private boolean checkRowForWin(int row) {
		int markCount = 0;
		int emptyCount = 0;
		
		for (int col = 0; col < 3; col++) {
			char mark = board.getMark(row, col);
			if (mark == this.mark) {
				markCount++;
			} else if (mark == SPACE_CHAR) {
				emptyCount++;
			}
		}
		return markCount == 2 && emptyCount == 1;
	}
	
	/**
     * Checks if there is a winning move in any column and makes a blocking move if found.
     *
     * @return True if a winning move is found and a blocking move is made, false otherwise.
     */
	private boolean checkWinCols() {
		for (int col = 0; col < 3; col++) {
			if(checkColForWin(col)) {
				makeColWinMove(col);
				return true;
			}
		}
		return false;
	}
	 /**
     * Checks if a specific column contains a winning move.
     *
     * @param col The column to check for a winning move.
     * @return True if a winning move is found in the column, false otherwise.
     */
	private boolean checkColForWin(int col) {
		int markCount = 0;
		int emptyCount = 0;
		
		for (int row = 0; row < 3; row++) {
			char mark = board.getMark(row, col);
			if(mark == this.mark ) {
				markCount++;
			} else if (mark == SPACE_CHAR) {
				emptyCount++;
			}
		}
		return markCount == 2 && emptyCount == 1;
	}
	/**
     * Checks if there is a winning move in any diagonal and makes a blocking move if found.
     *
     * @return True if a winning move is found and a blocking move is made, false otherwise.
     */
	private boolean checkWinDiag() {
		if(checkDiagForWin(0,0,1,1)) {
			makeDiagWinMove(0,0,1,1);
			return true;
		}
		else if(checkDiagForWin(0,2,1,-1)) {
			makeDiagWinMove(0,2,1,-1);
			return true;
		}
		
		return false;
	}
	/**
     * Checks if a specific diagonal contains a winning move.
     *
     * @param rowStart The starting row of the diagonal.
     * @param colStart The starting column of the diagonal.
     * @param rowInc   The row increment for the diagonal.
     * @param colInc   The column increment for the diagonal.
     * @return True if a winning move is found in the diagonal, false otherwise.
     */
	private boolean checkDiagForWin(int rowStart, int colStart, int rowInc, int colInc) {
		int markCount = 0;
		int emptyCount = 0;
		
		for (int i = 0; i < 3; i++) {
			char mark = board.getMark(rowStart, colStart);
			if(mark == this.mark) {
				markCount++;
			}else if(mark == SPACE_CHAR) {
				emptyCount++;
			}
			rowStart += rowInc;
			colStart += colInc;
		}
		return markCount == 2 && emptyCount == 1;
	}
	
}

