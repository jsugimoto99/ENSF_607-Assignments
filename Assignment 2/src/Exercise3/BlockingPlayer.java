package Exercise3;


/**
 * @author Jeremy Sugimoto
 * 
 * This class represents a blocking player in a Tic-Tac-Toe game that extends the RandomPlayer class.
 * The BlockingPlayer tries to make moves to block the opponent from winning.
 *
 */
public class BlockingPlayer extends RandomPlayer {

		
	BlockingPlayer(String name, char mark) {
		super(name, mark);
	}
	/**
     * Makes a move in a row to block the opponent from winning if possible.
     *
     * @param row The row to make a blocking move.
     */
	public void makeRowBlockingMove(int row) {
		for( int col = 0; col < 3; col++) {
			if(board.getMark(row, col) == SPACE_CHAR) {
				board.addMark(row, col, mark);
				break;
			}
		}
	}
	/**
     * Makes a move in a column to block the opponent from winning if possible.
     *
     * @param col The column to make a blocking move.
     */
	public void makeColBlockingMove(int col) {
		for( int row = 0; row < 3; row++) {
			if(board.getMark(row, col) == SPACE_CHAR) {
				board.addMark(row, col, mark);
				break;
			}
		}
	}
	/**
     * Makes a move in a diagonal to block the opponent from winning if possible.
     *
     * @param rowStart The starting row of the diagonal.
     * @param colStart The starting column of the diagonal.
     * @param rowInc   The row increment for the diagonal.
     * @param colInc   The column increment for the diagonal.
     */
	public void makeDiagBlockingMove(int rowStart, int colStart, int rowInc, int colInc) {
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
     * Makes a move for the BlockingPlayer. It tries to make a blocking move if the opponent can win,
     * otherwise, it makes a random move.
     */
	public void makeMove() {
		makeBlockingMove();
	}
	/**
     * Makes a blocking move if the opponent can win, otherwise makes a random move.
     */
	public void makeBlockingMove() {
			
		if(checkBlockingCols()) {
			return;
		}
		else if(checkBlockingRows()) {
			return;
		}
		
		else if(checkBlockingDiag()) {
			return;
		}	
		else makeRandMove();
	}

		
	
	 /**
     * Checks if the opponent can win in any row and makes a blocking move if needed.
     *
     * @return True if a blocking move is made, false otherwise.
     */
	private boolean checkBlockingRows() {
		for (int row = 0;row < 3; row++) {
			if(checkRowForBlocking(row)) {
				makeRowBlockingMove(row);
				return true;
		}
	}
		return false;
	}
	/**
     * Checks if the opponent can win in a specific row and makes a blocking move if needed.
     *
     * @param row The row to check for a blocking move.
     * @return True if a blocking move is made in the row, false otherwise.
     */
	private boolean checkRowForBlocking(int row) {
		int opponentCount = 0;
		int emptyCount = 0;
		
		for (int col = 0; col < 3; col++) {
			char mark = board.getMark(row, col);
			if (mark == opponent.mark) {
				opponentCount++;
			} else if (mark == SPACE_CHAR) {
				emptyCount++;
			}
		}
		return opponentCount == 2 && emptyCount == 1;
	}
	
	/**
     * Checks if the opponent can win in any column and makes a blocking move if needed.
     *
     * @return True if a blocking move is made, false otherwise.
     */
	private boolean checkBlockingCols() {
		for (int col = 0; col < 3; col++) {
			if(checkColForBlocking(col)) {
				makeColBlockingMove(col);
				return true;
			}
		}
		return false;
	}
	/**
     * Checks if the opponent can win in a specific column and makes a blocking move if needed.
     *
     * @param col The column to check for a blocking move.
     * @return True if a blocking move is made in the column, false otherwise.
     */
	private boolean checkColForBlocking(int col) {
		int opponentCount = 0;
		int emptyCount = 0;
		
		for (int row = 0; row < 3; row++) {
			char mark = board.getMark(row, col);
			if(mark == opponent.mark ) {
				opponentCount++;
			} else if (mark == SPACE_CHAR) {
				emptyCount++;
			}
		}
		return opponentCount == 2 && emptyCount == 1;
	}
	/**
     * Checks if the opponent can win in any diagonal and makes a blocking move if needed.
     *
     * @return True if a blocking move is made, false otherwise.
     */
	private boolean checkBlockingDiag() {
		if(checkDiagForBlocking(0,0,1,1)) {
			makeDiagBlockingMove(0,0,1,1);
		}
		else if(checkDiagForBlocking(0,2,1,-1)) {
			makeDiagBlockingMove(0,2,1,-1);
		}
		
		return checkDiagForBlocking(0,0,1,1)||checkDiagForBlocking(0,2,1,-1);
	}
	/**
     * Checks if the opponent can win in any diagonal and makes a blocking move if needed.
     *
     * @return True if a blocking move is made, false otherwise.
     */	
	private boolean checkDiagForBlocking(int rowStart, int colStart, int rowInc, int colInc) {
		int opponentCount = 0;
		int emptyCount = 0;
		
		for (int i = 0; i < 3; i++) {
			char mark = board.getMark(rowStart, colStart);
			if(mark == opponent.mark) {
				opponentCount++;
			}else if(mark == SPACE_CHAR) {
				emptyCount++;
			}
			rowStart += rowInc;
			colStart += colInc;
		}
		return opponentCount == 2 && emptyCount == 1;
	}
	
}
