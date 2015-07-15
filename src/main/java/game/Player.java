package main.java.game;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Russ Forstall
 */
public class Player {
    private int id;
    private String name;
    private int score;
    private Set<Character> usedLetters;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.usedLetters = new HashSet<Character>();
    }

    public void takeTurn() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Character> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(Set<Character> usedLetters) {
        this.usedLetters = usedLetters;
    }
}
