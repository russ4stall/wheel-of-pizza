package main.java.game;

/**
 * @author Russ Forstall
 */
public class Puzzle {
    private String category;
    private String phrase;

    public Puzzle(String category, String phrase) {
        this.category = category;
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "category='" + category + '\'' +
                ", phrase='" + phrase + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public String getPhrase() {
        return phrase;
    }
}
