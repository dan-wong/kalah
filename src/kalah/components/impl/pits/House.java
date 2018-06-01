package kalah.components.impl.pits;

import kalah.components.impl.board.KalahBoard;
import kalah.enums.Player;

import java.util.Objects;

public class House extends Pit {
    private static final int INITIAL_NUMBER_SEEDS = 4;

    private final int houseNumber;

    public House(Player owner, int houseNumber) {
        super(owner, INITIAL_NUMBER_SEEDS);
        this.houseNumber = houseNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getOppositeHouseNumber() {
        return (KalahBoard.NUMBER_OF_HOUSES + 1) - houseNumber;
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
