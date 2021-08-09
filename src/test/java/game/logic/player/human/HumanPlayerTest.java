package game.logic.player.human;

import game.logic.board.Board;
import junit.framework.TestCase;
import org.junit.Test;

public class HumanPlayerTest extends TestCase {

    @Test
    public void testMakeMove() {
        HumanPlayer player = new HumanPlayer(1);
        Board board = new Board(3);
        player.makeMove(board, 0, 0);
        assertEquals(board.getValue(0, 0), player.getId());
    }
}