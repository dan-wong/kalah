package kalah.components.interfaces.pits;

import kalah.components.impl.pits.House;
import kalah.components.impl.pits.Pit;
import kalah.components.impl.pits.Store;
import kalah.enums.Player;

import java.util.List;
import java.util.Map;

public interface CircularList {
    void add(House house);

    void add(Store store);

    House get(Player player, int houseNumber);

    Store getStore(Player player);

    Pit getNext(Pit current);

    House getOppositeHouse(Pit pit);

    Map<Player, List<Integer>> getState();
}
