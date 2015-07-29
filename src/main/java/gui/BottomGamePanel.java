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
    private static final String HOW_TO_PLAY_TEXT = "Just like wheel of fortune but cooler... and with pizza.";
    private Game game;
    private TopGamePanel topGamePanel;
    private JPanel letterButtonsPanel;
    private List<JButton> consonantButtons;
    private List<JButton> vowelButtons;
    private List<Character> usedLetters;
    private PlayerPanel p1Panel;
    private PlayerPanel p2Panel;

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

                    resetBoardForTurn();
                }
            });

            if (letter != 'A' && letter != 'E' && letter != 'I' && letter != 'O' && letter != 'U') {
                consonantButtons.add(button);
            } else {
                vowelButtons.add(button);
            }
        }

        letterButtonsPanel = new JPanel();
        letterButtonsPanel.setPreferredSize(new Dimension(250, 180));
        letterButtonsPanel.setLayout(new GridLayout(6, 5, 2, 2));

        for (JButton button : vowelButtons) {
            letterButtonsPanel.add(button);
        }

        for (JButton button : consonantButtons) {
            letterButtonsPanel.add(button);
        }

        // SOLVE PUZZLE FUNCTIONALITY
        JButton solveBtn = new JButton("Solve Puzzle");
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userGuess = JOptionPane.showInputDialog("Solve the puzzle");
                userGuess = userGuess.replaceAll("\\s+","");

                String puzzleWord = game.getPuzzle().getPhrase().replaceAll("\\s+","");

                if (puzzleWord.toUpperCase().equals(userGuess.toUpperCase())){
                    //player wins!
                    JOptionPane.showMessageDialog(null, "CORRECT! " + game.getCurrentPlayer().getName() +
                            " wins " + game.getCurrentPlayer().getScore() + " pizza points! \n " +
                            "Click OK to start a new game.");
                    game.newGame();
                    topGamePanel.letterBoard.setPuzzlePhrase(game.getPuzzle().getPhrase());
                    usedLetters.clear();
                    resetBoardForTurn();

                }
                else {
                    // wrong, next persons turn
                    game.turnOver();
                    JOptionPane.showMessageDialog(null, "WRONG! \n" + game.getCurrentPlayer().getName() + "'s turn.");

                    resetBoardForTurn();
                }
            }
        });

        JButton howToPlayBtn = new JButton("How To Play");
        howToPlayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, HOW_TO_PLAY_TEXT);
            }
        });

        JPanel btnPanel = new JPanel(new GridLayout(2,1));
        btnPanel.add(howToPlayBtn);

        btnPanel.add(solveBtn);

        add(btnPanel);

        add(letterButtonsPanel);

        p1Panel = new PlayerPanel();
        p1Panel.nameLbl.setText(game.getPlayers().get(0).getName());
        p1Panel.scoreLbl.setText("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore()));
        add(p1Panel);

        p2Panel = new PlayerPanel();
        p2Panel.nameLbl.setText(game.getPlayers().get(1).getName());
        p2Panel.scoreLbl.setText("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore()));
        add(p2Panel);

    }

    public void updateScoreLabels() {
        p1Panel.scoreLbl.setText("SCORE: " + String.valueOf(game.getPlayers().get(0).getScore()));
        p2Panel.scoreLbl.setText("SCORE: " + String.valueOf(game.getPlayers().get(1).getScore()));
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

    public void outlineCurrentPlayer() {
        if (game.getWhoseTurnItIs() == 0) {
            p1Panel.addBorder();
            p2Panel.removeBorder();
        } else {
            p2Panel.addBorder();
            p1Panel.removeBorder();
        }
    }

    // added functions
    public Boolean isInList(char c) {

        Boolean b = false;
        for (Character character : usedLetters) {
            if(c == character)
                b = true;
        }
        return b;

    }

    public void resetBoardForTurn() {
        setEnabledForAllLetterButtons(false);
        // If score >= 500 allow user to buy vowel
        if (game.getCurrentPlayersScore() >= WheelOfPizza.VOWEL_COST) {
            enableUnusedVowelButtons();
        }
        topGamePanel.setEnabledSpinBtn(true);
        updateScoreLabels();
        outlineCurrentPlayer();
        topGamePanel.letterBoard.repaint();
    }

}
