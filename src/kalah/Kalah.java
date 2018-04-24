package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board;
import kalah.components.MoveResult;
import kalah.components.Player;
import kalah.components.exceptions.IllegalMoveException;

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

        board.printBoard(io);

        String input = io.readFromKeyboard(String.format(PROMPT, currentPlayer.number())).trim();

        while (!input.equals("q")) {
            try {
                if (checkValidInput(input)) {
                    MoveResult result = board.playMove(currentPlayer, Integer.valueOf(input));
                    board.printBoard(io);

                    //If the player gets another move, do not change currentPlayer to the next player
                    if (result.equals(MoveResult.FINISH)) {
                        currentPlayer = currentPlayer.nextPlayer();
                    }
                }
            } catch (NumberFormatException e) {
                io.println("ERROR: Invalid House Number " + input);
            } catch (IllegalMoveException e) {
                io.println(e.getMessage());
                board.printBoard(io);
            }

            if (!board.isMovePossible(currentPlayer)) {
                io.println("Game over");
                board.printBoard(io);
                board.printResults(io);
                return;
            }

            input = io.readFromKeyboard(String.format(PROMPT, currentPlayer.number())).trim();
        }
        io.println("Game over");
        board.printBoard(io);
    }

    private boolean checkValidInput(String input) throws NumberFormatException {
        int houseNumber = Integer.valueOf(input);
        return houseNumber > 0 && houseNumber <= Board.NUMBER_OF_HOUSES;
    }
}
