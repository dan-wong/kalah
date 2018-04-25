package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.board.Board;
import kalah.components.board.BoardPrinter;
import kalah.components.enums.MoveResult;
import kalah.components.player.Player;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
    private static final String PROMPT = "Player P%d's turn - Specify house number or 'q' to quit: ";

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
        Player currentPlayer = Player.ONE;
        Board board = new Board();
        BoardPrinter boardPrinter = new BoardPrinter(board);

        boardPrinter.printIO(io);

        String input = io.readFromKeyboard(String.format(PROMPT, currentPlayer.number())).trim();

        while (!input.equals("q")) {
            try {
                if (checkValidInput(input)) {
                    MoveResult result = board.playMove(currentPlayer, Integer.valueOf(input));

                    //If the player gets another move, do not change currentPlayer to the next player
                    if (result.equals(MoveResult.FINISH)) {
                        currentPlayer = currentPlayer.nextPlayer();
                    } else if (result.equals(MoveResult.EMPTY_HOUSE)) { //If the chosen house is empty, display error message
                        io.println("House is empty. Move again.");
                    }

                    boardPrinter.printIO(io);
                }
            } catch (NumberFormatException e) {
                io.println("Invalid House Number " + input);
            }

            //No possible moves for next player, game over
            if (!board.isMovePossible(currentPlayer)) {
                break;
            }

            input = io.readFromKeyboard(String.format(PROMPT, currentPlayer.number())).trim();
        }

        io.println("Game over");
        boardPrinter.printIO(io);
        if (!input.equals("q")) { //If the game was not ended forcefully, printIO results
            board.printResults(io);
        }
    }

    private boolean checkValidInput(String input) throws NumberFormatException {
        int houseNumber = Integer.valueOf(input);
        return houseNumber > 0 && houseNumber <= Board.NUMBER_OF_HOUSES;
    }
}
