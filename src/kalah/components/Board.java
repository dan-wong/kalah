package kalah.components;

import com.qualitascorpus.testsupport.IO;
import kalah.components.pits.House;
import kalah.components.pits.Pit;
import kalah.components.pits.PitId;
import kalah.components.pits.Store;
import kalah.exceptions.IllegalMoveException;

import java.util.HashMap;
import java.util.Map;

public class Board {
    public static final int NUMBER_OF_HOUSES = 6;

    private Map<PitId, House> houses;
    private PitCircularList pitList;
    private Store playerOneStore;
    private Store playerTwoStore;

    public Board() {
        houses = new HashMap<>();
        pitList = new PitCircularList();

        setupBoard();
    }

    public void playMove(Player player, int initialHouse) throws IllegalMoveException {
        Pit currentPit = pitList.get(houses.get(new PitId(player, initialHouse)));

        int seeds = currentPit.pickup(player);
        while (seeds != 0) {
            currentPit = pitList.getNext(currentPit);
            seeds = currentPit.sow(player, seeds);
        }
    }

    /**
     * Sets up the board anti-clockwise starting with Player One's store
     */
    private void setupBoard() {
        //Player One's Store
        playerOneStore = new Store(Player.TWO);
        pitList.add(playerOneStore);

        //Player Two's Houses
        generateHouses(Player.TWO);

        //Player Two's Store
        playerTwoStore = new Store(Player.TWO);
        pitList.add(playerTwoStore);

        //Player One's Houses
        generateHouses(Player.ONE);
    }

    private void generateHouses(Player player) {
        for (int i = 1; i <= NUMBER_OF_HOUSES; i++) {
            House house = new House(player);
            houses.put(new PitId(player, i), house);
            pitList.add(house);
        }
    }

    public void printBoard(IO io) {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",
                houses.get(new PitId(Player.TWO, 6)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 5)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 4)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 3)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 2)).getNumberOfSeeds(),
                houses.get(new PitId(Player.TWO, 1)).getNumberOfSeeds(),
                playerOneStore.getNumberOfSeeds()));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",
                playerTwoStore.getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 1)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 2)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 3)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 4)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 5)).getNumberOfSeeds(),
                houses.get(new PitId(Player.ONE, 6)).getNumberOfSeeds()));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }
}
