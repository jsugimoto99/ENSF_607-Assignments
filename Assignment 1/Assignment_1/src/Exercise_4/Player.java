package Exercise_4;
import java.util.Scanner;

/**
 *
 *@author Jeremy Sugimoto
 * 
 * The {@code Player} class represents a player in a Tic-Tac-Toe game.
 */
public class Player implements Constants {
    String name;
    Board board;
    Player opponent;
    char mark;

    /**
     * Constructs a new player with the specified name and mark ('X' or 'O').
     *
     * @param name The player's name.
     * @param mark The player's mark ('X' or 'O').
     */
    Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Sets the opponent player for this player.
     *
     * @param opponent The opponent player.
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Sets the game board for this player.
     *
     * @param board The game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Plays a turn of the game, making a move on the game board.
     */
    public void play() {
        while (!board.isFull() && !board.xWins() && !board.oWins()) {
            board.display();
            System.out.println(name + "'s turn (" + mark + "):");
            makeMove();
            if (board.xWins()) {
                board.display();
                System.out.println(name + " wins!");
            } else if (board.oWins()) {
                board.display();
                System.out.println(name + " wins!");
            } else if (board.isFull()) {
                board.display();
                System.out.println("It's a tie!");
            } else {
                opponent.play();
            }
        }
    }





    /**
     * Makes a move on the game board based on user input.
     */
    
    public void makeMove() {
        while (true) {
        	Scanner scanner = new Scanner(System.in);
            try {
                System.out.println(name + ", enter the row number (0, 1, 2): ");

                // Check if there is an available integer token to read
                if (scanner.hasNextLine()) {
                    int row = scanner.nextInt();

                    // Check if the entered row is within the valid range (0-2)
                    if (row < 0 || row > 2) {
                        System.out.println("Please enter a valid row number (0, 1, 2).");
                        continue; // Continue the loop to re-enter the row
                    }

                    System.out.println(name + ", enter the column number (0, 1, 2): ");

                    // Check if there is an available integer token to read
                    if (scanner.hasNextLine()) {
                        int col = scanner.nextInt();

                        // Check if the entered column is within the valid range (0-2)
                        if (col < 0 || col > 2) {
                            System.out.println("Please enter a valid column number (0, 1, 2).");
                            continue; // Continue the loop to re-enter the column
                        }

                        if (board.getMark(row, col) == SPACE_CHAR) {
                            board.addMark(row, col, mark);
                            break; // Valid move, exit the loop
                        } else {
                            System.out.println("That spot is already taken, please try again.");
                        }
                    } else {
                        System.out.println("Please enter a valid column number (0, 1, 2).");
                        scanner.nextLine(); // Consume the invalid input
                    }
                } else {
                    System.out.println("Please enter a valid row number (0, 1, 2).");
                    scanner.nextLine(); // Consume the invalid input
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Enter integers for row and column.");
                scanner.next(); // Consume the invalid input
            }
           
        }
  
    }
    
}
