package game.logic.player.human;

import game.logic.board.Board;
import game.logic.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class HumanPlayer extends Player {
    public HumanPlayer(int id) {
        super(id);
    }

    public void makeMove(Board board) throws IOException {
        boolean foundValidMove = false;
        int x = -1;
        int y = -1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!foundValidMove) {
            while (x < 0 || x >= board.getSize()) {
                System.out.println("Select a valid X coordinate");
                String xC = reader.readLine();
                x = parseInt(xC);
            }
            while (y < 0 || y >= board.getSize()) {
                System.out.println("Select a valid X coordinate");
                String yC = reader.readLine();
                y = parseInt(yC);
            }
            if (board.isValidMove(x, y)) {
                foundValidMove = true;
            } else {
                System.out.println("This move is invalid");
                x = -1;
                y = -1;
            }
        }
        board.makeMove(this, x, y);
    }
}
