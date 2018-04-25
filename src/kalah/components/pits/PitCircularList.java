package kalah.components.pits;

import kalah.components.player.Player;
import kalah.components.player.PlayerHouses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PitCircularList {
    private List<Pit> pitList;
    private Map<Player, PlayerHouses> playerHousesMap;
    private Map<Player, Store> playerStoreMap;

    public PitCircularList() {
        pitList = new ArrayList<>();
        playerHousesMap = new HashMap<>();
        for (Player player : Player.values()) {
            playerHousesMap.put(player, new PlayerHouses(player));
        }
        playerStoreMap = new HashMap<>();
    }

    public void add(House house) {
        playerHousesMap.get(house.getOwner()).put(house);
        pitList.add(house);
    }

    public void add(Store store) {
        playerStoreMap.put(store.getOwner(), store);
        pitList.add(store);
    }

    /**
     * Return the pit owned by the player and has the corresponding houseNumber
     *
     * @param player - Player owning the pit
     * @return the pit requested
     */
    public House get(Player player, int houseNumber) {
        return playerHousesMap.get(player).get(houseNumber);
    }

    public Store getStore(Player player) {
        return playerStoreMap.get(player);
    }

    public Pit getNext(Pit current) {
        int nextIndex = (pitList.indexOf(current) + 1) % pitList.size();
        return pitList.get(nextIndex);
    }

    public House getOppositeHouse(Pit pit) {
        if (pit instanceof House) {
            House house = (House) pit;
            return playerHousesMap.get(house.getOwner().nextPlayer()).get(house.getOppositeHouseNumber());
        } else {
            return null;
        }
    }

    /**
     * Get a list of seeds in each players pits
     * Index 0 of list is the player's store
     * Otherwise the index represents house number
     *
     * @return a Map from player to list of seeds per pits
     */
    public Map<Player, List<Integer>> getState() {
        Map<Player, List<Integer>> seedsInHousesForPlayers = new HashMap<>();
        for (Player player : Player.values()) {
            List<Integer> seedsPerPit = new ArrayList<>();
            seedsPerPit.add(playerStoreMap.get(player).getNumberOfSeeds());
            seedsPerPit.addAll(playerHousesMap.get(player).getSeedsPerHouse());

            seedsInHousesForPlayers.put(player, seedsPerPit);
        }
        return seedsInHousesForPlayers;
    }
}
