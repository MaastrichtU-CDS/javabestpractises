package game.logic.player;

import game.logic.board.Board;

import java.io.IOException;

public abstract class Player {
    private int id;
    protected Player opponent;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void makeMove(Board board) throws IOException {
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
