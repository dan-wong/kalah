package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board;
import kalah.components.MoveResult;
import kalah.components.Player;

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

                    switch (result) {
                        case ANOTHER_MOVE:
                            break;
                        case GAME_OVER:
                            return;
                        default:
                            currentPlayer = currentPlayer.nextPlayer();
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                io.println("ERROR: Invalid House Number " + input);
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
