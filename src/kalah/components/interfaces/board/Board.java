package kalah.components.interfaces.board;

import kalah.enums.MoveResult;
import kalah.enums.Player;

import java.util.List;
import java.util.Map;

public interface Board {
    Map<Player, List<Integer>> getBoardState();

    MoveResult playMove(Player player, int initialHouse);

    boolean isGameOver(Player player);
}
