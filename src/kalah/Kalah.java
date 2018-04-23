package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board;
import kalah.components.Player;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
    private static final String PROMPT = "Player %d's turn - Specify house number or 'q' to quit: ";

    private Player currentTurn = Player.ONE;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
        String input = io.readFromKeyboard(String.format(PROMPT, currentTurn.number())).trim();

        if (input.equals("q")) { //QUIT
            return;
        }
    }

    private boolean checkValidInput(String input) {
        try {
            int houseNumber = Integer.valueOf(input);
            if (houseNumber > 0 && houseNumber <= Board.NUMBER_OF_HOUSES) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }

        return false;
    }
}
