package connectfour;

public abstract class Player {
    protected char color;

    // Set the player's colour
    public Player(char color) {
        this.color = color;
    }

    // Method to get the player's colour
    public char getColor() {
        return color;
    }
}
