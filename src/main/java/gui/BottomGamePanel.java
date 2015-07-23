package main.java.gui;

import main.java.Game;
import main.java.game.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author Russ Forstall
 */
public class BottomGamePanel extends JPanel {
    private Game game;
    private JPanel letterButtonsPanel;
    private List<JButton> consonantButtons;
    private List<JButton> vowelButtons;

    public BottomGamePanel(final Game game) {
        super();
        this.game = game;

        // Make letter buttons
        consonantButtons = new ArrayList<JButton>();
        vowelButtons = new ArrayList<JButton>();
        for (int i = 0; i < 26; i++) {
            char letter = (char) (i + 65);
            final JButton button = new JButton(String.valueOf(letter));
            button.setEnabled(true);
            // Todo: add action event for letter buttons
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    game.takeTurn(new Turn(0, "500", button.getText().charAt(0)));
                    //Todo: How do I repaint so it shows updated score?
                    repaint();
                }
            });

            if (letter != 'A' && letter != 'E' && letter != 'I' && letter != 'O' && letter != 'U') {
                consonantButtons.add(button);
            } else {
                vowelButtons.add(button);
            }
        }

        letterButtonsPanel = new JPanel();
        letterButtonsPanel.setPreferredSize(new Dimension(250, 200));
        letterButtonsPanel.setLayout(new GridLayout(6, 5, 2, 2));

        for (JButton button : vowelButtons) {
            letterButtonsPanel.add(button);
        }

        for (JButton button : consonantButtons) {
            letterButtonsPanel.add(button);
        }

        add(letterButtonsPanel);

        JPanel player1Panel = new JPanel();
        player1Panel.setPreferredSize(new Dimension(250, 200));
        player1Panel.setLayout(new FlowLayout());
        player1Panel.add(new JLabel(game.getPlayers().get(0).getName()));
        player1Panel.add(new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore())));

        add(player1Panel);

        JPanel player2Panel = new JPanel();
        player2Panel.setPreferredSize(new Dimension(250, 200));
        player2Panel.setLayout(new FlowLayout());
        player2Panel.add(new JLabel(game.getPlayers().get(1).getName()));
        player2Panel.add(new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore())));
        add(player2Panel)ssss;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        add(letterButtonsPanel);

        JPanel player1Panel = new JPanel();
        player1Panel.setPreferredSize(new Dimension(250, 200));
        player1Panel.setLayout(new FlowLayout());
        player1Panel.add(new JLabel(game.getPlayers().get(0).getName()));
        player1Panel.add(new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore())));

        add(player1Panel);

        JPanel player2Panel = new JPanel();
        player2Panel.setPreferredSize(new Dimension(250, 200));
        player2Panel.setLayout(new FlowLayout());
        player2Panel.add(new JLabel(game.getPlayers().get(1).getName()));
        player2Panel.add(new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore())));
        add(player2Panel);

    }
}
