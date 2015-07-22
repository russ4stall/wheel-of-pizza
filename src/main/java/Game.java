package main.java;

import main.java.game.Player;
import main.java.game.Puzzle;
import main.java.game.Turn;
import main.java.game.WheelOfPizza;
import main.java.gui.MainMenuFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Russ Forstall
 */
public class Game {
    private WheelOfPizza wop;
    private Puzzle puzzle;
    private List<Player> players;

    public Game() {
        wop = new WheelOfPizza();
        puzzle = wop.getRandomPuzzle();
        players = new ArrayList<Player>();
    }

    public static void main(String[] args) {
        MainMenuFrame mainMenuFrame = new MainMenuFrame("Wheel Of Pizza");
        mainMenuFrame.setVisible(true);
    }

    /**
     * Handles a Player's guess
     * @param turn
     * @return True if guess was correct and user gets to go again.
     */
    public boolean takeTurn(Turn turn) {
        // Get number of times the guess appears in the puzzle
        int guessOccurrences = 0;
        for( int i=0; i < puzzle.getPhrase().length(); i++ ) {
            if( puzzle.getPhrase().charAt(i) == turn.getGuess() ) {
                guessOccurrences++;
            }
        }

        // If at least one occurrence, add score and return true
        if (guessOccurrences > 0) {
            int addScore = guessOccurrences * Integer.valueOf(turn.getSpinValue());
            players.get(turn.getPlayerIndex()).addToScore(addScore);
            return true;
        }

        // If no occurrences of guess, return false;
        return false;
    }

    public void start() {


    }
}
