package kalah.components.pits;

import kalah.components.Player;
import kalah.exceptions.IllegalMoveException;

public class House extends Pits {
    private static final int INITIAL_NUMBER_SEEDS = 4;

    public House(Player owner) {
        super.owner = owner;
        super.seeds = INITIAL_NUMBER_SEEDS;
    }

    /**
     * When a player captures a House, all the seeds are removed from the House
     *
     * @return the number of seeds in this house
     */
    public int capture() {
        int seeds = super.seeds;
        super.seeds = 0;

        return seeds;
    }

    @Override
    public int pickup(Player player) throws IllegalMoveException {
        if (player.equals(super.owner)) {
            int seeds = super.seeds;
            super.seeds = 0;

            return seeds;
        } else {
            throw new IllegalMoveException("Player cannot pickup from a House they don't own!");
        }
    }

    @Override
    public int sow(Player player, int seedsToSow) {
        super.seeds++;
        return seedsToSow - 1;
    }
}
