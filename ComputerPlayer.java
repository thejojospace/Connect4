package connectfour;

import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random;

    // Set the computer player's colour and initialise random generator
    public ComputerPlayer(char color) {
        super(color);
        random = new Random();
    }

    // Method to make a move based on basic strategy
    public int makeMove(Board board) {
        // Check for a winning move or block opponent's winning move
        for (int col = 0; col < board.getColumnCount(); col++) {
            if (board.isValidMove(col)) {
                board.placeCounter(this.color, col);
                if (board.checkWin(this.color)) {
                    board.undoMove(col);
                    return col; // Return the winning move
                }
                board.undoMove(col);

                board.placeCounter('r', col);
                if (board.checkWin('r')) {
                    board.undoMove(col);
                    return col; // Return the blocking move
                }
                board.undoMove(col);
            }
        }

        // If the computer is not winning or blocking move, pick a random valid move
        int move;
        do {
            move = random.nextInt(board.getColumnCount());
        } while (!board.isValidMove(move));
        return move;
    }
}
