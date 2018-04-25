package kalah.components.player;

import kalah.components.pits.House;

import java.util.*;

public class PlayerHouses {
    private Player player;
    private Map<Integer, House> playerHousesMap;

    public PlayerHouses(Player player) {
        this.player = player;
        playerHousesMap = new HashMap<>();
    }

    public void put(House house) {
        playerHousesMap.put(house.getHouseNumber(), house);
    }

    public House get(int houseNumber) {
        return playerHousesMap.get(houseNumber);
    }

    public int getSumOfSeedsInHouses() {
        int sum = 0;
        for (House house : playerHousesMap.values()) {
            sum += house.getNumberOfSeeds();
        }
        return sum;
    }

    public List<Integer> getSeedsPerHouse() {
        List<Integer> seedsList = new ArrayList<>();
        for (House house : playerHousesMap.values()) {
            seedsList.add(house.getNumberOfSeeds());
        }
        return seedsList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, playerHousesMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerHouses) {
            PlayerHouses other = (PlayerHouses) obj;
            return player.equals(other.player) && playerHousesMap.equals(other.playerHousesMap);
        }
        return false;
    }
}
