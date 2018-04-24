package kalah.components;

import kalah.components.pits.House;
import kalah.components.pits.Pit;
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
    private Map<Integer, House> playerOneHouses;
    private Map<Integer, House> playerTwoHouses;
    private Map<Player, Store> stores;

    public PitCircularList() {
        pitList = new ArrayList<>();
        playerOneHouses = new HashMap<>();
        playerTwoHouses = new HashMap<>();
        stores = new HashMap<>();
    }

    public void add(Pit pit) {
        if (pit instanceof House) {
            House house = (House) pit;
            if (house.getOwner().equals(Player.ONE)) {
                playerOneHouses.put(house.getHouseNumber(), house);
            } else {
                playerTwoHouses.put(house.getHouseNumber(), house);
            }
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
        Map<Integer, House> playerHouses = player.equals(Player.ONE) ? playerOneHouses : playerTwoHouses;
        House house = playerHouses.get(houseNumber);
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
            return null; //Should never be possible
        }
    }

    public Store getStore(Player player) {
        return stores.get(player);
    }

    /**
     * Get the sum of all seeds for each player
     * Stores ARE included
     *
     * @return a map of player to number of seeds
     */
    public Map<Player, Integer> getSumOfSeedsForPlayers() {
        Map<Player, Integer> seedsForPlayers = getSeedsInPlayForPlayers();
        seedsForPlayers.put(Player.ONE, seedsForPlayers.get(Player.ONE) + getStore(Player.ONE).getNumberOfSeeds());
        seedsForPlayers.put(Player.TWO, seedsForPlayers.get(Player.TWO) + getStore(Player.TWO).getNumberOfSeeds());
        return seedsForPlayers;
    }

    /**
     * Get the sum of seeds in houses for each player
     * Stores are NOT included
     *
     * @return a map of player to number of seeds
     */
    public Map<Player, Integer> getSeedsInPlayForPlayers() {
        int playerOne = 0, playerTwo = 0;
        for (House house : playerOneHouses.values()) {
            playerOne += house.getNumberOfSeeds();
        }
        for (House house : playerTwoHouses.values()) {
            playerTwo += house.getNumberOfSeeds();
        }

        Map<Player, Integer> seedsInPlayForPlayers = new HashMap<>();
        seedsInPlayForPlayers.put(Player.ONE, playerOne);
        seedsInPlayForPlayers.put(Player.TWO, playerTwo);
        return seedsInPlayForPlayers;
    }
}
