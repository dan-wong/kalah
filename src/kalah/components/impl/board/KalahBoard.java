package kalah.components.impl.board;

import kalah.components.impl.pits.House;
import kalah.components.impl.pits.Pit;
import kalah.components.impl.pits.PitCircularList;
import kalah.components.impl.pits.Store;
import kalah.components.interfaces.board.Board;
import kalah.enums.MoveResult;
import kalah.enums.Player;

import java.util.List;
import java.util.Map;

public class KalahBoard implements Board {
    public static final int NUMBER_OF_HOUSES = 6;

    private PitCircularList pitCircularList;

    public KalahBoard() {
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
     * Determine if it is game over
     * i.e. A move is not possible if there are no seeds in the houses of the player
     *
     * @param player - Player to determine if a move is possible
     * @return true if game over, false if not
     */
    public boolean isGameOver(Player player) {
        List<Integer> seedsInPlayForPlayer = pitCircularList.getState().get(player);
        for (int i = 0; i < seedsInPlayForPlayer.size(); i++) {
            if (i != 0 && seedsInPlayForPlayer.get(i) > 0) {
                return true;
            }
        }
        return false;
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
