package main.java.game;

/**
 * @author Russ Forstall
 */
public class Turn {
    private int playerIndex;
    private String spinResult;
    private char guess;

    public Turn(int playerIndex, String spinValue, char guess) {
        this.playerIndex = playerIndex;
        this.spinResult = spinValue;
        this.guess = guess;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public String getSpinResult() {
        return spinResult;
    }

    public char getGuess() {
        return guess;
    }
}
