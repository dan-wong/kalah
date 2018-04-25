package kalah.components.board;

import com.qualitascorpus.testsupport.IO;
import kalah.components.player.Player;

import java.util.List;
import java.util.Map;

public class BoardPrinter {
    private Board board;

    public BoardPrinter(Board board) {
        this.board = board;
    }

    public void printIO(IO io) {
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
}
