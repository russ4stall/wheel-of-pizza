package main.java.gui;

import main.java.Game;
import main.java.game.Player;
import main.java.game.Turn;
import main.java.game.WheelOfPizza;

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
    private TopGamePanel topGamePanel;
    private JPanel letterButtonsPanel;
    private List<JButton> consonantButtons;
    private List<JButton> vowelButtons;
    private List<Character> usedLetters;
    private JLabel p1Score;
    private JLabel p2Score;

    public BottomGamePanel(final Game game, TopGamePanel tgp) {
        super();
        this.game = game;
        this.topGamePanel = tgp;

        // initialize usedLetters list
        usedLetters = new ArrayList<Character>();


        // Make letter buttons
        consonantButtons = new ArrayList<JButton>();
        vowelButtons = new ArrayList<JButton>();
        for (int i = 0; i < 26; i++) {
            char letter = (char) (i + 65);
            final JButton button = new JButton(String.valueOf(letter));
            button.setEnabled(false);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    usedLetters.add(button.getText().charAt(0));

                    Turn turn = new  Turn(game.getWhoseTurnItIs(), topGamePanel.getSpinResult(), button.getText().charAt(0));

                    if (!game.takeTurn(turn)) {
                        game.turnOver();
                    }

                    setEnabledForAllLetterButtons(false);
                    // If score >= 500 allow user to buy vowel
                    if (game.getCurrentPlayersScore() >= WheelOfPizza.VOWEL_COST) {
                        enableUnusedVowelButtons();
                    }
                    topGamePanel.setEnabledSpinBtn(true);
                    updateScoreLabels();
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
        p1Score = new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore()));
        player1Panel.add(p1Score);
        add(player1Panel);

        JPanel player2Panel = new JPanel();
        player2Panel.setPreferredSize(new Dimension(250, 200));
        player2Panel.setLayout(new FlowLayout());
        player2Panel.add(new JLabel(game.getPlayers().get(1).getName()));
        p2Score = new JLabel("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore()));
        player2Panel.add(p2Score);
        add(player2Panel);
    }

    public void updateScoreLabels() {
        p1Score.setText("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore()));
        p2Score.setText("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore()));
    }

    public void setEnabledForAllConsanantButtons(boolean b) {
        for (JButton button : consonantButtons) {
            button.setEnabled(b);
        }
    }

    public void enableUnusedConsonantButtons() {
        setEnabledForAllConsanantButtons(true);
        for (JButton button : consonantButtons) {
            for (Character character : usedLetters) {
                if (button.getText().equals(character.toString())) {
                    button.setEnabled(false);
                }
            }
        }
    }

    public void enableUnusedVowelButtons() {
        setEnabledForAllVowelButtons(true);
        for (JButton button : vowelButtons) {
            for (Character character : usedLetters) {
                if (button.getText().equals(character.toString())) {
                    button.setEnabled(false);
                }
            }
        }
    }

    public void setEnabledForAllVowelButtons(boolean b) {
        for (JButton button : vowelButtons) {
            button.setEnabled(b);
        }
    }

    public void setEnabledForAllLetterButtons(boolean b) {
        setEnabledForAllConsanantButtons(b);
        setEnabledForAllVowelButtons(b);
    }


}
