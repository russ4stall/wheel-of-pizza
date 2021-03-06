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
    private int whoseTurnItIs;

    public Game() {
        wop = new WheelOfPizza();
        puzzle = wop.getRandomPuzzle();
        System.out.println(puzzle);
        players = new ArrayList<Player>();
    }

    public static void main(String[] args) {
        MainMenuFrame mainMenuFrame = new MainMenuFrame("Wheel Of Pizza");
        //mainMenuFrame.setVisible(true);
        mainMenuFrame.showGameBoard();
    }

    public void newGame(){
        puzzle = wop.getRandomPuzzle();
        for (Player player : players) {
            player.setScore(0);
        }
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

        // If vowel
        if (turn.getGuess() == 'A' || turn.getGuess() == 'E' || turn.getGuess() == 'I' || turn.getGuess() == 'O' || turn.getGuess() == 'U') {
            getCurrentPlayer().addToScore(-500);

            return (guessOccurrences > 0);
        }

        // If at least one occurrence, add score and return true
        if (guessOccurrences > 0) {
            int addScore = guessOccurrences * Integer.valueOf(turn.getSpinResult());
            getCurrentPlayer().addToScore(addScore);
            return true;
        }

        // If no occurrences of guess, return false;
        return false;
    }

    public void start() {


    }

    public WheelOfPizza getWop() {
        return wop;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getWhoseTurnItIs() {
        return whoseTurnItIs;
    }

    public void setWhoseTurnItIs(int whoseTurnItIs) {
        this.whoseTurnItIs = whoseTurnItIs;
    }

    public int turnOver() {
        whoseTurnItIs++;
        if (whoseTurnItIs > players.size()-1) {
            whoseTurnItIs = 0;
        }
        return whoseTurnItIs;
    }

    public int getCurrentPlayersScore() {
        return players.get(whoseTurnItIs).getScore();
    }

    public Player getCurrentPlayer() {
        return players.get(whoseTurnItIs);
    }
}
