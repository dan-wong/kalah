package kalah.components.pits;

import kalah.components.Player;

import java.util.Objects;

/**
 * Abstract to prevent instantiation
 */
public abstract class Pit {
    protected Player owner;
    protected int seeds;

    public Player getOwner() {
        return owner;
    }

    public int getNumberOfSeeds() {
        return seeds;
    }

    /**
     * Pickup all the seeds in this pit
     *
     * @return the number of seeds picked up
     */
    public abstract int pickup();

    /**
     * Sow a seed to the pit
     *
     * @param player     - Identifier of the player making the move
     * @param seedsToSow - The number of seeds remaining the player is sowing
     * @return the number of seeds remaining to sow after the sow method is called.
     */
    public abstract int sow(Player player, int seedsToSow);

    @Override
    public int hashCode() {
        return Objects.hash(owner, seeds);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pit) {
            Pit other = (Pit) obj;
            return owner.equals(other.owner) && seeds == other.seeds;
        }
        return false;
    }
}
