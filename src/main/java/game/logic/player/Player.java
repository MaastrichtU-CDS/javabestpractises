package game.logic.player;

import game.logic.board.Board;

public abstract class Player {
    private int id;
    protected Player opponent;
    private boolean ai;

    public Player(int id, boolean ai) {
        this.id = id;
        this.ai = ai;
    }

    public int getId() {
        return id;
    }

    public void makeMove(Board board) {
    }

    public boolean isAi() {
        return ai;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
