package kalah.components.board;

import kalah.components.enums.MoveResult;
import kalah.components.pits.House;
import kalah.components.pits.Pit;
import kalah.components.pits.PitCircularList;
import kalah.components.pits.Store;
import kalah.components.player.Player;

import java.util.List;
import java.util.Map;

public class Board {
    public static final int NUMBER_OF_HOUSES = 6;

    private PitCircularList pitCircularList;

    public Board() {
        pitCircularList = new PitCircularList();
        setupBoard();
    }

    /**
     * Play a move on the board
     *
     * @param player       - player making the move
     * @param initialHouse - the chosen initial house for the move
     * @return MoveResult - FINISH if the turn is over, ANOTHER_MOVE if the player gets another move
     */
    public MoveResult playMove(Player player, int initialHouse) {
        Pit currentPit = pitCircularList.get(player, initialHouse);

        int seeds = currentPit.pickup();
        if (seeds == 0) {
            return MoveResult.EMPTY_HOUSE;
        }
        while (seeds != 0) {
            currentPit = pitCircularList.getNext(currentPit);
            seeds = currentPit.sow(player, seeds);
        }

        //If the final pit is the current players store, they get another turn
        if (currentPit.equals(pitCircularList.getStore(player))) {
            return MoveResult.ANOTHER_MOVE;
        }

        //Capture detection, current pit is owned by the player and the pit is "empty" (0 before sowing, 1 after sowing)
        if (currentPit.getOwner().equals(player) && currentPit.getNumberOfSeeds() == 1) {
            Pit oppositePit = pitCircularList.getOppositeHouse(currentPit);
            if (oppositePit != null && oppositePit.getNumberOfSeeds() > 0) {
                int seedsCaptured = oppositePit.pickup();
                seedsCaptured += currentPit.pickup();
                pitCircularList.getStore(player).deposit(seedsCaptured);
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
        Map<Player, Integer> seedsInPlayForPlayers = pitCircularList.getSeedsInPlayForPlayers();
        return seedsInPlayForPlayers.get(player) != 0;
    }


    public Map<Player, List<Integer>> getBoardState() {
        return pitCircularList.getState();
    }

    /**
     * Sets up the board anti-clockwise starting with Player One's store, Player Two's houses,
     * Player Two's store, Player One's houses
     */
    private void setupBoard() {
        pitCircularList.add(new Store(Player.ONE));
        generateHouses(Player.TWO);
        pitCircularList.add(new Store(Player.TWO));
        generateHouses(Player.ONE);
    }

    private void generateHouses(Player player) {
        for (int i = 1; i <= NUMBER_OF_HOUSES; i++) {
            pitCircularList.add(new House(player, i));
        }
    }
}
