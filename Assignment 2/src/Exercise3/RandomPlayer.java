package Exercise3;

/**
 * This class represents a random player in a Tic-Tac-Toe game that extends the Player class.
 * The RandomPlayer makes random moves on the game board.
 */
public class RandomPlayer extends Player {

	/**
     * Constructs a RandomPlayer with a given name and mark.
     *
     * @param name The name of the player.
     * @param mark The player's mark ('X' or 'O').
     */
	RandomPlayer(String name, char mark) {
		super(name, mark);
	}
	    /**
	     * Makes a move on the game board based on user input.
	     */
	public void makeMove() {
		makeRandMove();
	}
	/**
     * Makes a random move on the game board.
     */
	public void makeRandMove() {
	    	RandomGenerator rG = new RandomGenerator();
	        int row, col;
	        do {
	            row = rG.discrete(0, 2);
	            col = rG.discrete(0, 2);
	        } while (board.getMark(row, col) != SPACE_CHAR);

	        board.addMark(row, col, mark);
	    }
	}
	

	
