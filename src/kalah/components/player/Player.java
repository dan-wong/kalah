package kalah.components.player;

/**
 * Enum class to represent each player.
 */
public enum Player {
    ONE(1),
    TWO(2);

    private static final Player[] values = values();

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

    //https://stackoverflow.com/questions/17006239/whats-the-best-way-to-implement-next-and-previous-on-an-enum-type?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    public Player nextPlayer() {
        return values[(this.ordinal() + 1) % values.length];
    }
}
