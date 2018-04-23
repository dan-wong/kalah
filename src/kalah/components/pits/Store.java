package kalah.components.pits;

import kalah.components.Player;
import kalah.exceptions.IllegalMoveException;

public class Store extends Pit {
    public Store(Player owner) {
        super.owner = owner;
        super.seeds = 0;
    }

    /**
     * Deposits the seeds captured by a player
     *
     * @param capturedSeeds - Number of seeds to deposit
     */
    public void deposit(int capturedSeeds) {
        super.seeds += capturedSeeds;
    }

    @Override
    public int pickup(Player player) throws IllegalMoveException {
        throw new IllegalMoveException("Cannot pickup from a Store!");
    }

    /**
     * If the player sowing the seeds is the owner, add one to the store and subtract one from the seedsToSow
     * Otherwise, return the original amount of seedsToSow
     */
    @Override
    public int sow(Player player, int seedsToSow) {
        if (player.equals(super.owner)) {
            super.seeds++;
            return seedsToSow - 1;
        } else {
            return seedsToSow;
        }
    }
}
