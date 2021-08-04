package game.logic;

import game.logic.board.Board;
import game.logic.player.Player;
import game.logic.player.human.HumanPlayer;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private int size;

    private Player currentPlayer;

    public Game(int size, Player player1, Player player2) {
        this.size = size;
        board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;

        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);

        currentPlayer = player1;

        if (player1.isAi()) {
            makeMove(0, 0);
            switchPlayer();
        }
    }

    public boolean isValidMove(int x, int y) {
        return board.isValidMove(x, y);
    }

    public String makeMove(int x, int y) {
        String status = null;
        if (isValidMove(x, y)) {
            if (!currentPlayer.isAi()) {
                ((HumanPlayer) currentPlayer).makeMove(board, x, y);
                status = checkGameStatus();
                if (status != null) {
                    return status;
                }
                switchPlayer();
            }
            if (currentPlayer.isAi()) {
                currentPlayer.makeMove(board);
                status = checkGameStatus();
                switchPlayer();
            }
        }
        return status;
    }

    private String checkGameStatus() {
        if (board.hasWon(currentPlayer)) {
            return "Player " + currentPlayer.getId() + " has won";
        } else if (board.hasDrawn(player1, player2)) {
            return "It's a draw!";
        } else {
            return null;
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public String getCellValue(int x, int y) {
        if (board.getValue(x, y) == player1.getId()) {
            return "X";
        } else if (board.getValue(x, y) == player2.getId()) {
            return "O";
        } else {
            return "";
        }
    }

    public int getSize() {
        return size;
    }

}