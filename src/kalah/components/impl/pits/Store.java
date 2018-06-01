package kalah.components.impl.pits;

import kalah.enums.Player;

import java.util.Objects;

public class Store extends Pit {
    public Store(Player owner) {
        super(owner, 0);
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
    public int pickup() {
        return 0;
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

    @Override
    public int hashCode() {
        return Objects.hash(owner, seeds, 11);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Store) {
            Store other = (Store) obj;
            return owner.equals(other.owner) && seeds == other.seeds;
        }
        return false;
    }
}
