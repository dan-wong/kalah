package kalah.components;

import kalah.components.pits.Pit;

import java.util.ArrayList;
import java.util.List;

public class PitCircularList {
    private List<Pit> pitList;

    public PitCircularList() {
        pitList = new ArrayList<>();
    }

    public void add(Pit pit) {
        pitList.add(pit);
    }

    public Pit get(Pit pit) {
        return pitList.get(pitList.indexOf(pit));
    }

    public Pit getNext(Pit current) {
        int nextIndex = pitList.indexOf(current) + 1;
        if (nextIndex >= pitList.size()) {
            nextIndex = 0;
        }

        return pitList.get(nextIndex);
    }
}
