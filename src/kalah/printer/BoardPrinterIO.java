package kalah.printer;

import com.qualitascorpus.testsupport.IO;
import kalah.components.impl.board.KalahBoard;
import kalah.components.interfaces.board.Board;
import kalah.enums.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardPrinterIO implements BoardPrinter {
    private final Board board;
    private final IO io;

    public BoardPrinterIO(Board board, IO io) {
        this.board = board;
        this.io = io;
    }

    @Override
    public void printBoard() {
        Map<Player, List<Integer>> boardState = board.getBoardState();

        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",
                boardState.get(Player.TWO).get(6),
                boardState.get(Player.TWO).get(5),
                boardState.get(Player.TWO).get(4),
                boardState.get(Player.TWO).get(3),
                boardState.get(Player.TWO).get(2),
                boardState.get(Player.TWO).get(1),
                boardState.get(Player.ONE).get(0))); //Player One Store
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",
                boardState.get(Player.TWO).get(0), //Player Two Store
                boardState.get(Player.ONE).get(1),
                boardState.get(Player.ONE).get(2),
                boardState.get(Player.ONE).get(3),
                boardState.get(Player.ONE).get(4),
                boardState.get(Player.ONE).get(5),
                boardState.get(Player.ONE).get(6)));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    @Override
    public void printResults() {
        Map<Player, List<Integer>> boardState = board.getBoardState();
        Map<Player, Integer> playerSums = new HashMap<>();

        for (Player player : Player.values()) {
            playerSums.put(player, 0);
            for (int i = 0; i <= KalahBoard.NUMBER_OF_HOUSES; i++) {
                playerSums.put(player, playerSums.get(player) + boardState.get(player).get(i));
            }
            io.println(String.format("\tplayer %d:%d", player.number(), playerSums.get(player)));
        }

        int ties = 0;
        Map.Entry<Player, Integer> maxEntry = null;
        for (Map.Entry<Player, Integer> entry : playerSums.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
                ties = 0;
            } else if (entry.getValue().equals(maxEntry.getValue())) {
                ties++;
            }
        }

        if (ties == 0) {
            io.println(String.format("Player %d wins!", maxEntry.getKey().number()));
        } else {
            io.println("A tie!");
        }
    }
}
