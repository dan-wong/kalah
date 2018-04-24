package kalah.components;

import com.qualitascorpus.testsupport.IO;
import kalah.components.exceptions.IllegalMoveException;
import kalah.components.pits.House;
import kalah.components.pits.Pit;
import kalah.components.pits.Store;

import java.util.Map;

public class Board {
    public static final int NUMBER_OF_HOUSES = 6;

    private PitCircularList pitList;

    public Board() {
        pitList = new PitCircularList();

        setupBoard();
    }

    /**
     * Play a move on the board
     *
     * @param player       - player making the move
     * @param initialHouse - the chosen initial house for the move
     * @return MoveResult - FINISH if the turn is over, ANOTHER_MOVE if the player gets another move
     */
    public MoveResult playMove(Player player, int initialHouse) throws IllegalMoveException {
        Pit currentPit = pitList.get(player, initialHouse);

        int seeds = currentPit.pickup();
        if (seeds == 0) {
            throw new IllegalMoveException("House is empty. Move again.");
        }
        while (seeds != 0) {
            currentPit = pitList.getNext(currentPit);
            seeds = currentPit.sow(player, seeds);
        }

        //If the final pit is the current players store, they get another turn
        if (currentPit.equals(pitList.getStore(player))) {
            return MoveResult.ANOTHER_MOVE;
        }

        //Capture detection, current pit is owned by the player and the pit is "empty" (0 before sowing, 1 after sowing)
        if (currentPit.getOwner().equals(player) && currentPit.getNumberOfSeeds() == 1) {
            Pit oppositePit = pitList.getOppositeHouse(currentPit);
            if (oppositePit.getNumberOfSeeds() > 0) {
                int seedsCaptured = oppositePit.pickup();
                seedsCaptured += currentPit.pickup();
                pitList.getStore(player).deposit(seedsCaptured);
            }
        }

        return MoveResult.FINISH;
    }

    /**
     * Determine if a move is possible for the player
     * i.e. A move is not possible if there are no seeds in the houses of the player
     *
     * @param player - Player to determine if a move is possible
     * @return true if possible, false if not
     */
    public boolean isMovePossible(Player player) {
        Map<Player, Integer> seedsInPlayForPlayers = pitList.getSeedsInPlayForPlayers();
        return seedsInPlayForPlayers.get(player) != 0;
    }

    /**
     * Sets up the board anti-clockwise starting with Player One's store
     */
    private void setupBoard() {
        //Player One's Store
        Store playerOneStore = new Store(Player.ONE);
        pitList.add(playerOneStore);

        //Player Two's Houses
        generateHouses(Player.TWO);

        //Player Two's Store
        Store playerTwoStore = new Store(Player.TWO);
        pitList.add(playerTwoStore);

        //Player One's Houses
        generateHouses(Player.ONE);
    }

    private void generateHouses(Player player) {
        for (int i = 1; i <= NUMBER_OF_HOUSES; i++) {
            House house = new House(player, i);
            pitList.add(house);
        }
    }

    public void printBoard(IO io) {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",
                pitList.get(Player.TWO, 6).getNumberOfSeeds(),
                pitList.get(Player.TWO, 5).getNumberOfSeeds(),
                pitList.get(Player.TWO, 4).getNumberOfSeeds(),
                pitList.get(Player.TWO, 3).getNumberOfSeeds(),
                pitList.get(Player.TWO, 2).getNumberOfSeeds(),
                pitList.get(Player.TWO, 1).getNumberOfSeeds(),
                pitList.getStore(Player.ONE).getNumberOfSeeds()));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",
                pitList.getStore(Player.TWO).getNumberOfSeeds(),
                pitList.get(Player.ONE, 1).getNumberOfSeeds(),
                pitList.get(Player.ONE, 2).getNumberOfSeeds(),
                pitList.get(Player.ONE, 3).getNumberOfSeeds(),
                pitList.get(Player.ONE, 4).getNumberOfSeeds(),
                pitList.get(Player.ONE, 5).getNumberOfSeeds(),
                pitList.get(Player.ONE, 6).getNumberOfSeeds()));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void printResults(IO io) {
        Map<Player, Integer> seedsForPlayers = pitList.getSumOfSeedsForPlayers();
        for (Player player : Player.values()) {
            io.println(String.format("\tplayer %d:%d", player.number(), seedsForPlayers.get(player)));
        }

        if (seedsForPlayers.get(Player.ONE) > seedsForPlayers.get(Player.TWO)) {
            io.println("Player 1 wins!");
        } else {
            io.println("Player 2 wins!");
        }
    }
}
