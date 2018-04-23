import com.qualitascorpus.testsupport.MockIO;
import junit.framework.TestCase;
import kalah.Kalah;

public class TestKalah extends TestCase {
    private MockIO _mockIO;

    public TestKalah() {
    }

    public void setUp() {
        this._mockIO = new MockIO();
    }

    public void tearDown() {
        this._mockIO.finished();
    }

    public void testQuit() {
        this.playGame("/test/quit.txt");
    }

    public void testSimpleStart() {
        this.playGame("/test/simple_start.txt");
    }

    public void testP1Continue() {
        this.playGame("/test/p1_continue.txt");
    }

    public void testSimpleTwoMoves() {
        this.playGame("/test/simple_two_moves.txt");
    }

    public void testSingleWrap() {
        this.playGame("/test/single_wrap.txt");
    }

    public void testContinueWrap() {
        this.playGame("/test/continue_wrap.txt");
    }

    public void testCapture() {
        this.playGame("/test/capture.txt");
    }

    public void testEmptyHouseCapture() {
        this.playGame("/test/endinempty.txt");
    }

    public void testFullGame1() {
        this.playGame("/test/full_game1.txt");
    }

    public void testFullGame2() {
        this.playGame("/test/full_game2.txt");
    }

    public void testFullGameStore() {
        this.playGame("/test/full_game_store.txt");
    }

    public void testFullGameEmpty() {
        this.playGame("/test/full_game_empty.txt");
    }

    public void testFullGameTie() {
        this.playGame("/test/full_game_tie.txt");
    }

    public void testFullGameEmptyHouses() {
        this.playGame("/test/full_game_empty_houses.txt");
    }

    public void testUseEmpty() {
        this.playGame("/test/useempty.txt");
    }

    public void testWrapped() {
        this.playGame("/test/wrapped.txt");
    }

    public void testDoubleWrap() {
        this.playGame("/test/double_wrap.txt");
    }

    public void testExactLapCapture() {
        this.playGame("/test/exactlapcapture.txt");
    }

    public void testExactLapEmptyCapture() {
        this.playGame("/test/exactlapemptycapture.txt");
    }

    private void playGame(String var1) {
        this._mockIO.setExpected(var1);
        (new Kalah()).play(this._mockIO);
    }
}
