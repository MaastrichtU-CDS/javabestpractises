package game.logic.player.ai;

import game.logic.board.Board;
import game.logic.player.Player;

import java.util.Random;

public class RandomAi extends Player {
    public RandomAi(int id) {
        super(id);
    }

    public void makeMove(Board board) {
        boolean foundValidMove = false;
        Random random = new Random();
        int x = -1;
        int y = -1;
        while (!foundValidMove) {
            x = random.nextInt(board.getSize());
            y = random.nextInt(board.getSize());
            if (board.isValidMove(x, y)) {
                foundValidMove = true;
            }
        }
        board.makeMove(this, x, y);
    }
}
