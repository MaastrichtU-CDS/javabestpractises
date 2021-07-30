package game.main;

import game.logic.board.Board;
import game.logic.player.Player;
import game.logic.player.ai.MinMaxAi;
import game.logic.player.human.HumanPlayer;

import java.io.IOException;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private int size = 3;

    private int currentTurn;

    private void startGame() throws IOException {
        board = new Board(size);
        player1 = new HumanPlayer(1);
        player2 = new MinMaxAi(2);

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        currentTurn = player1.getId();
        board.print();
        while (true) {
            if (currentTurn == player1.getId()) {
                player1.makeMove(board);
                board.print();
                if (board.hasWon(player1)) {
                    System.out.println("Player " + player1.getId() + " has won");
                    break;
                } else if (board.hasDrawn(player1, player2)) {
                    System.out.println("its a draw");
                    break;
                }
                currentTurn = player2.getId();
            }

            if (currentTurn == player2.getId()) {
                player2.makeMove(board);
                board.print();
                if (board.hasWon(player2)) {
                    System.out.println("Player " + player2.getId() + " has won");
                    break;
                } else if (board.hasDrawn(player1, player2)) {
                    System.out.println("its a draw");
                    break;
                }
                currentTurn = player1.getId();
            }
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
