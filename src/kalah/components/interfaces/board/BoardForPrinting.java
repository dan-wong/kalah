package kalah.components.interfaces.board;

import kalah.enums.Player;

import java.util.List;
import java.util.Map;

public interface BoardForPrinting {
    Map<Player, List<Integer>> getBoardState();
}
