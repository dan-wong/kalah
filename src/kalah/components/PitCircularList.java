package kalah.components;

import kalah.components.pits.Pit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PitCircularList {
    private static Map<Integer, Integer> oppositePitMap = new HashMap<Integer, Integer>() {{
        put(1, 6);
        put(2, 5);
        put(3, 4);
        put(4, 3);
        put(5, 2);
        put(6, 1);
    }};

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

    public Pit getOpposite(Pit current) {
        int currentIndex = pitList.indexOf(current);
        return pitList.get(oppositePitMap.get(currentIndex));
    }
}
