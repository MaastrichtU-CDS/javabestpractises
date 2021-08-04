package game.logic.player.ai;

import game.logic.board.Board;
import game.logic.player.Player;

public class MinMaxAi extends Player {
    private final int maxDepth = 10;

    public MinMaxAi(int id) {
        super(id, true);
    }

    public void makeMove(Board board) {

        int bestX = -1;
        int bestY = -1;
        int value = -3;
        int size = board.getSize();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!board.isValidMove(x, y)) {
                    continue;
                }
                Board copy = board.copy();
                copy.makeMove(this, x, y);
                int temp = alphabeta(copy, maxDepth - 1, -3, 3, false);

                if (temp > value) {
                    value = temp;
                    bestX = x;
                    bestY = y;
                }
            }
        }
        board.makeMove(this, bestX, bestY);
    }


    private int alphabeta(Board board, int depth, int alpha, int beta, boolean max) {
        if (board.hasWon(this)) {
            return 1;
        } else if (board.hasWon(opponent)) {
            return -1;
        } else if (board.hasDrawn(this, opponent)) {
            return 0;
        } else if (board.noMoveleft()) {
            return 0;
        } else if (depth > 1) {
            if (max) {
                int size = board.getSize();
                int value = -2;
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        if (!board.isValidMove(x, y)) {
                            continue;
                        }
                        Board copy = board.copy();
                        copy.makeMove(this, x, y);
                        int temp = alphabeta(copy, depth - 1, alpha, beta, false);
                        if (temp > value) {
                            value = temp;
                            if (value >= beta) {
                                break;
                            }
                        }
                        if (value > alpha) {
                            alpha = value;
                        }
                    }
                }
                return value;
            } else {
                int size = board.getSize();
                int value = 2;
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        if (!board.isValidMove(x, y)) {
                            continue;
                        }
                        Board copy = board.copy();
                        copy.makeMove(opponent, x, y);
                        int temp = alphabeta(copy, depth - 1, alpha, beta, true);
                        if (temp < value) {
                            value = temp;
                            if (value <= alpha) {
                                break;
                            }
                        }
                        if (value < beta) {
                            beta = value;
                        }
                    }
                }
                return value;
            }
        }
        return 0;
    }
}
