package main.java.gui;

import main.java.Game;

import javax.swing.*;
import java.awt.*;
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

    public BottomGamePanel(Game game) {
        this.game = game;

        // Make letter buttons
        consonantButtons = new ArrayList<JButton>();
        vowelButtons = new ArrayList<JButton>();
        for (int i = 0; i < 26; i++) {
            char letter = (char) (i + 65);
            JButton button = new JButton(String.valueOf(letter));
            button.setEnabled(true);
            // Todo: add action event for letter buttons
            //// button.addActionListener(buttonListener);

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
    }
}
