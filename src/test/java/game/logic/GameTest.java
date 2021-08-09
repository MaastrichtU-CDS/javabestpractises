package game.logic;

import game.logic.player.Player;
import game.logic.player.ai.AlphaBetaAi;
import game.logic.player.ai.RandomAi;
import junit.framework.TestCase;
import org.junit.Test;

public class GameTest extends TestCase {

    @Test
    public void testGameLoop() {
        // test different sized games with random AIs
        Player player1 = new RandomAi(1);
        Player player2 = new RandomAi(2);
        for (int i = 3; i < 10; i++) {
            Game game = new Game(i, player1, player2);
            assertTrue(game.isFinished());
        }

        // test different sized games with alphabeta AIs
        player1 = new AlphaBetaAi(1);
        player2 = new AlphaBetaAi(2);
        for (int i = 3; i < 6; i++) {
            Game game = new Game(i, player1, player2);
            assertTrue(game.isFinished());
        }
    }

}