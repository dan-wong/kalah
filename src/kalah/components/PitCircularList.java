package kalah.components;

import kalah.components.pits.House;
import kalah.components.pits.Pit;
import kalah.components.pits.PitId;
import kalah.components.pits.Store;

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
    private Map<PitId, House> houses;
    private Map<Player, Store> stores;

    public PitCircularList() {
        pitList = new ArrayList<>();
        houses = new HashMap<>();
        stores = new HashMap<>();
    }

    public void add(Pit pit) {
        if (pit instanceof House) {
            House house = (House) pit;
            houses.put(new PitId(house.getOwner(), house.getHouseNumber()), house);
        } else {
            Store store = (Store) pit;
            stores.put(store.getOwner(), store);
        }
        pitList.add(pit);
    }

    /**
     * Return the pit owned by the player and has the corresponding houseNumber
     *
     * @param player - Player owning the pit
     * @return the pit requested
     */
    public Pit get(Player player, int houseNumber) {
        House house = houses.get(new PitId(player, houseNumber));
        return pitList.get(pitList.indexOf(house));
    }

    public Pit getNext(Pit current) {
        int nextIndex = pitList.indexOf(current) + 1;
        if (nextIndex >= pitList.size()) {
            nextIndex = 0;
        }

        return pitList.get(nextIndex);
    }

    public Pit getOppositeHouse(Pit current) {
        if (current instanceof House) {
            House house = (House) current;
            return get(current.getOwner().nextPlayer(), oppositePitMap.get(house.getHouseNumber()));
        } else {
            return null;
        }
    }

    public Store getStore(Player player) {
        return stores.get(player);
    }

    /**
     * Get the total number of seeds in houses for each player
     *
     * @return a map of player to number of seeds
     */
    public Map<Player, Integer> getSeedsPerPlayers() {
        int playerOne = 0, playerTwo = 0;
        for (House house : houses.values()) {
            if (house.getOwner().equals(Player.ONE)) {
                playerOne += house.getNumberOfSeeds();
            } else {
                playerTwo += house.getNumberOfSeeds();
            }
        }

        Map<Player, Integer> seedPerPlayerMap = new HashMap<>();
        seedPerPlayerMap.put(Player.ONE, playerOne);
        seedPerPlayerMap.put(Player.TWO, playerTwo);
        return seedPerPlayerMap;
    }
}
