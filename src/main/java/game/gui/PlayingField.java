package game.gui;

import game.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayingField extends JPanel {
    private int size = 3;
    private Game game;
    private JButton[][] field;

    public PlayingField(int size, Game game) {
        this.size = size;
        this.setLayout(new GridLayout(size, size));
        field = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                field[i][j] = button;
                button.addActionListener(createButtonListener());
                this.add(button);
            }
        }
        this.game = game;
    }

    private ActionListener createButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j] == e.getSource()) {
                            //   if (game.isValidMove(i, j)) {
                            JButton button = (JButton) e.getSource();
                            button.setText(String.valueOf(game.getCurrentTurn()));
                            //game.makeMove(i, j);
                            //          }
                        }
                    }
                }
            }
        };
    }
}
