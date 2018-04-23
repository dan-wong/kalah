package kalah.components;

import kalah.components.pits.House;
import kalah.components.pits.Pit;
import kalah.components.pits.PitId;
import kalah.components.pits.Store;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Board {
    public static final int NUMBER_OF_HOUSES = 6;

    private Map<PitId, House> houses;
    private Queue<Pit> pits;
    private Store playerOneStore;
    private Store playerTwoStore;

    public Board() {
        houses = new HashMap<>();
        pits = new ArrayDeque<>();

        setupBoard();
    }

    /**
     * Sets up the board anti-clockwise starting with Player One's store
     */
    private void setupBoard() {
        //Player One's Store
        playerOneStore = new Store(Player.TWO);
        pits.add(playerOneStore);

        //Player Two's Houses
        generateHouses(Player.TWO);

        //Player Two's Store
        playerTwoStore = new Store(Player.TWO);
        pits.add(playerTwoStore);

        //Player One's Houses
        generateHouses(Player.ONE);
    }

    private void generateHouses(Player player) {
        for (int i = 1; i <= NUMBER_OF_HOUSES; i++) {
            House house = new House(player);
            houses.put(new PitId(player, i), house);
            pits.add(house);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+----+-------+-------+-------+-------+-------+-------+----+\n");
        sb.append(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |\n",
                houses.get(new PitId(Player.TWO, 6)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 5)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 4)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 3)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 2)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 1)).getNumberOfSeeds(),
                playerOneStore.getNumberOfSeeds()));
        sb.append("|    |-------+-------+-------+-------+-------+-------|    |\n");
        sb.append(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |\n",
                playerTwoStore.getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 1)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 2)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 3)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 4)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 5)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 6)).getNumberOfSeeds()));
        sb.append("+----+-------+-------+-------+-------+-------+-------+----+\n");

        return sb.toString();
    }
}
