package kalah.components.pits;

import kalah.components.Player;

import java.util.Objects;

public class PitId {
    private Player owner;
    private int houseNumber;

    public PitId(Player owner, int houseNumber) {
        this.owner = owner;
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PitId) {
            PitId pitId = (PitId) obj;
            return this.owner.equals(pitId.owner) && this.houseNumber == pitId.houseNumber;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, houseNumber);
    }
}
