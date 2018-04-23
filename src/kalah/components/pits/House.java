package kalah.components.pits;

import kalah.components.Player;

import java.util.Objects;

public class House extends Pit {
    private static final int INITIAL_NUMBER_SEEDS = 4;

    private int houseNumber;

    public House(Player owner, int houseNumber) {
        super.owner = owner;
        super.seeds = INITIAL_NUMBER_SEEDS;
        this.houseNumber = houseNumber;
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
    public int pickup() {
        int seeds = super.seeds;
        super.seeds = 0;

        return seeds;
    }

    @Override
    public int sow(Player player, int seedsToSow) {
        super.seeds++;
        return seedsToSow - 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, seeds, houseNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof House) {
            House other = (House) obj;
            return owner.equals(other.owner) && seeds == other.seeds && houseNumber == other.houseNumber;
        }
        return false;
    }
}
