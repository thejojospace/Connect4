package connectfour;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private BufferedReader input;
    private Board board;
    private Player humanPlayer;
    private Player computerPlayer;
    private boolean win;

    // Initialise the game components
    public Game() {
        input = new BufferedReader(new InputStreamReader(System.in));
        humanPlayer = new HumanPlayer('r');
        computerPlayer = new ComputerPlayer('y');
        startGameLoop();
    }

    // Method to manage the overall game loop
    private void startGameLoop() {
        boolean playAgain = true;
        while (playAgain) {
            board = new Board();  // Reset the board for a new game
            win = false;
            playGame();
            playAgain = askPlayAgain();
        }
        System.out.println("Thank you for playing Connect 4!");
    }

    // Method to start and manage the game
    private void playGame() {
        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players: Red (Human) and Yellow (Computer)");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game, type in the number of the column you want to drop your counter in.");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally, or diagonally.");
        System.out.println("");
        board.printBoard();

        // Main game loop
        while (!win) {
            playerTurn(humanPlayer);  // Human player's turn
            if (win) break;
            playerTurn(computerPlayer);  // Computer player's turn
        }

        System.out.println("Game Over!");
    }

    // Method to handle a player's turn
    private void playerTurn(Player player) {
        if (player instanceof HumanPlayer) {
            System.out.println("Human (Red)'s turn. Enter column number:");
        } else {
            System.out.println("Computer (Yellow)'s turn.");
        }

        int move = -1;
        if (player instanceof HumanPlayer) {
            // Get user input for human player's move
            String userInput = getUserInput();
            try {
                move = Integer.parseInt(userInput.trim()) - 1;  // Adjust for zero-based index
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                playerTurn(player);
                return;
            }
        } else {
            // Generate move for computer player
            move = ((ComputerPlayer) player).makeMove(board);
            System.out.println("Computer chooses column: " + (move + 1));
        }

        // Validate and place the counter in the selected column
        if (move >= 0 && move < board.getColumnCount()) {
            if (board.placeCounter(player.getColor(), move)) {
                board.printBoard();
                win = board.checkWin(player.getColor());
                if (win) {
                    System.out.println(player.getColor() == 'r' ? "Human (Red) wins!" : "Computer (Yellow) wins!");
                }
            } else {
                System.out.println("Column is full. Try again.");
                playerTurn(player);
            }
        } else {
            System.out.println("Invalid column. Try again.");
            playerTurn(player);
        }
    }

    // Method to get user input
    private String getUserInput() {
        String toReturn = null;
        try {
            toReturn = input.readLine();
        } catch (Exception e) {
            System.out.println("An error occurred while reading input. Please try again.");
        }
        return toReturn;
    }

    // Method to ask if players want to play another game
    private boolean askPlayAgain() {
        System.out.println("Do you want to play another game? (yes/no)");
        String response = getUserInput().trim().toLowerCase();
        return response.equals("yes");
    }

    // Main method to start the game
    public static void main(String[] args) {
        new Game();
    }
}
