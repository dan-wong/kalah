package kalah.components;

/**
 * Enum class to represent each player.
 */
public enum Player {
    ONE(1),
    TWO(2);

    /**
     * Represents the player number
     */
    private final int number;

    Player(int number) {
        this.number = number;
    }

    /**
     * Returns the number representation of the player
     *
     * @return number
     */
    public int number() {
        return number;
    }
}
