package game.logic.board;

import game.logic.player.Player;
import game.logic.player.ai.RandomAi;
import junit.framework.TestCase;
import org.junit.Test;

public class BoardTest extends TestCase {

    @Test
    public void testHasWon() {
        Board board = new Board(3);
        Player player = new RandomAi(1);
        board.makeMove(player, 2, 0);
        board.makeMove(player, 1, 1);
        board.makeMove(player, 0, 2);
        assertTrue(board.hasWon(player));
    }

}