package game.gui;

import game.exception.InvalidSizeException;
import game.main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {
    private JFrame frame;
    private Game game;
    private JTextField sizeField;


    public GUI() throws IOException {
        frame = new JFrame("Example game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createMenu());
        Game game = new Game(3);
        mainPanel.add(new PlayingField(3, game));

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    public GUI(int size) throws IOException {
        frame = new JFrame("Example game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createMenu());
        Game game = new Game(3);
        mainPanel.add(new PlayingField(size, game));

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }


    private JPanel createMenu() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Size");
        JButton start = new JButton("Start");
        start.addActionListener(createButtonListener());
        sizeField = new JTextField(10);
        panel.add(label);
        panel.add(sizeField);
        panel.add(start);
        return panel;
    }

    private ActionListener createButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(sizeField.getText());
                    if (size <= 0) {
                        throw new InvalidSizeException();
                    } else {
                        game = new Game(size);
                        game.startGame();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please fill in a valid size" + " Error");
                }
            }
        };
    }

}

