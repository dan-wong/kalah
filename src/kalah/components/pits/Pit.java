package kalah.components.pits;

import kalah.components.enums.Player;

public abstract class Pit {
    protected final Player owner;
    protected int seeds;

    protected Pit(Player owner, int seeds) {
        this.owner = owner;
        this.seeds = seeds;
    }

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
}
