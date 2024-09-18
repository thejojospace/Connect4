package connectfour;

public class Board {
    private char[][] board;

    // Initialise the game board
    public Board() {
        board = new char[6][7];
    }

    // Method to get the number of columns in the board
    public int getColumnCount() {
        return board[0].length;
    }

    // Method to print the current state of the board
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'r') {
                    System.out.print("| r ");
                } else if (board[i][j] == 'y') {
                    System.out.print("| y ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("----------------------------");
        System.out.println("  1   2   3   4   5   6   7  ");
    }

    // Method to place a counter in a specified column
    public boolean placeCounter(char player, int position) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][position] == '\0') {
                board[i][position] = player;
                return true;
            }
        }
        return false;
    }

    // Method to check if a move is valid (column is not full)
    public boolean isValidMove(int column) {
        return board[0][column] == '\0';
    }

    // Method to undo the last counter if it was placed in a column
    public void undoMove(int column) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] != '\0') {
                board[i][column] = '\0';
                break;
            }
        }
    }

    // Method to check if a player has won the game
    public boolean checkWin(char player) {
        // Check the horizontal
        for (int i = 0; i < board.length; i++) {
            int count = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == player) {
                    count++;
                    if (count >= 4) return true;
                } else {
                    count = 0;
                }
            }
        }

        // Check the vertical
        for (int j = 0; j < board[0].length; j++) {
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == player) {
                    count++;
                    if (count >= 4) return true;
                } else {
                    count = 0;
                }
            }
        }

        // Check the diagonal (bottom-left to top-right)
        for (int i = 3; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] == player &&
                        board[i-1][j+1] == player &&
                        board[i-2][j+2] == player &&
                        board[i-3][j+3] == player) {
                    return true;
                }
            }
        }

        // Check the diagonal (top-left to bottom-right)
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] == player &&
                        board[i+1][j+1] == player &&
                        board[i+2][j+2] == player &&
                        board[i+3][j+3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
}
