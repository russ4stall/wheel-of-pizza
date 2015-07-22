package main.java.game;

/**
 * @author Russ Forstall
 */
public class Turn {
    private int playerIndex;
    private String spinValue;
    private char guess;

    public Turn(int playerIndex, String spinValue, char guess) {
        this.playerIndex = playerIndex;
        this.spinValue = spinValue;
        this.guess = guess;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public String getSpinValue() {
        return spinValue;
    }

    public char getGuess() {
        return guess;
    }
}
