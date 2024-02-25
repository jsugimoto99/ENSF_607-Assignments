package Exercise3;
/**
 * 
 * @author Jeremy Sugimoto
 * 
 * The {@code Referee} class coordinates and manages the Tic-Tac-Toe game between two players.
 */
public class Referee {

    private Player xPlayer;
    private Player oPlayer;

    /**
     * Sets up and runs the Tic-Tac-Toe game between two players.
     */
    public void runTheGame() {
        // Set each player's opponent
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        // Create a game board
        Board board = new Board();

        // Set the game board for both players
        xPlayer.setBoard(board);
        oPlayer.setBoard(board);

        // Start the game with player X
        xPlayer.play();
    }

    /**
     * Sets the game board (not used in this implementation).
     *
     * @param board The game board to be set.
     */
    public void setBoard(Board board) {
        // This method is not used in this implementation.
    }

    /**
     * Sets the player who will be playing as 'O'.
     *
     * @param oPlayer The player representing 'O'.
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * Sets the player who will be playing as 'X'.
     *
     * @param xPlayer The player representing 'X'.
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }
}