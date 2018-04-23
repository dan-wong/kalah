package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board;
import kalah.components.Player;
import kalah.exceptions.IllegalMoveException;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
    private static final String PROMPT = "Player %d's turn - Specify house number or 'q' to quit: ";

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
                    board.playMove(currentPlayer, Integer.valueOf(input));
                    board.printBoard(io);
                    currentPlayer = currentPlayer.nextPlayer();
                }
            } catch (NumberFormatException e) {
                io.println("ERROR: Invalid House Number " + input);
            } catch (IllegalMoveException e) {
                io.println("ERROR: " + e.getMessage());
            }

            input = io.readFromKeyboard(String.format(PROMPT, currentPlayer.number())).trim();
        }
    }

    private boolean checkValidInput(String input) throws NumberFormatException {
        int houseNumber = Integer.valueOf(input);
        return houseNumber > 0 && houseNumber <= Board.NUMBER_OF_HOUSES;
    }
}
