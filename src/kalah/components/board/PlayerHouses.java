package kalah.components.board;

import kalah.components.pits.House;

import java.util.*;

public class PlayerHouses {
    private Map<Integer, House> playerHousesMap;

    public PlayerHouses() {
        playerHousesMap = new HashMap<>();
    }

    public void put(House house) {
        playerHousesMap.put(house.getHouseNumber(), house);
    }

    public House get(int houseNumber) {
        return playerHousesMap.get(houseNumber);
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
        return Objects.hash(playerHousesMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerHouses) {
            PlayerHouses other = (PlayerHouses) obj;
            return playerHousesMap.equals(other.playerHousesMap);
        }
        return false;
    }
}
