package Exercise_4;

import java.io.*;

/**
 * 
 * @author Jeremy Sugimoto
 * 
 * The {@code Game1} class represents a Tic-Tac-Toe game. It initializes the game board, players,
 * and referee, and then runs the game.
 */
public class Game1 implements Constants {

    private Board theBoard;
    private Referee theRef;

    /**
     * Initializes a new game with an empty game board.
     */
    public Game1() {
        theBoard = new Board();
    }

    /**
     * Appoints a referee for the game and runs the game.
     *
     * @param r The referee object responsible for controlling the game.
     * @throws IOException If there is an input/output error during the game.
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.runTheGame();
    }

    /**
     * The main method that sets up and runs the Tic-Tac-Toe game.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If there is an input/output error during the game setup.
     */
    public static void main(String[] args) throws IOException {
        Referee theRef;
        Player xPlayer, oPlayer;
        BufferedReader stdin;
        Game1 theGame = new Game1();
        stdin = new BufferedReader(new InputStreamReader(System.in));

        // Prompt for the 'X' player's name
        System.out.print("\nPlease enter the name of the 'X' player: ");
        String name = stdin.readLine();
        while (name == null) {
            System.out.print("Please try again: ");
            name = stdin.readLine();
        }

        xPlayer = new Player(name, LETTER_X);
        xPlayer.setBoard(theGame.theBoard);

        // Prompt for the 'O' player's name
        System.out.print("\nPlease enter the name of the 'O' player: ");
        name = stdin.readLine();
        while (name == null) {
            System.out.print("Please try again: ");
            name = stdin.readLine();
        }

        oPlayer = new Player(name, LETTER_O);
        oPlayer.setBoard(theGame.theBoard);

        // Create and set up the referee
        theRef = new Referee();
        theRef.setBoard(theGame.theBoard);
        theRef.setoPlayer(oPlayer);
        theRef.setxPlayer(xPlayer);

        // Appoint the referee and start the game
        theGame.appointReferee(theRef);
    }
}
