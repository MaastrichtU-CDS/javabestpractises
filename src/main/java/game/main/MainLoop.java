package game.main;

import game.gui.GUI;
import game.logic.Game;
import game.logic.player.Player;
import game.logic.player.ai.MinMaxAi;
import game.logic.player.human.HumanPlayer;

public class MainLoop {
    private static final Player player1 = new HumanPlayer(1);
    private static final Player player2 = new MinMaxAi(2);
    private static final int size = 3;

    public static void main(String[] args) {
        new GUI(new Game(size, player1, player2));
    }
}
