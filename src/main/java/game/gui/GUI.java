package game.gui;

import game.main.Game;

import javax.swing.*;

public class GUI {
    private JFrame frame;
    private JPanel mainPanel;
    private PlayingField field;


    public GUI(Game game) {
        frame = new JFrame("Example game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        field = new PlayingField(game);
        mainPanel.add(field);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}

