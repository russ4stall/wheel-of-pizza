package main.java.game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class has helper methods for the game that isn't round specific.
 *
 * @author Russ Forstall
 */
public class WheelOfPizza {
    public static final String BANKRUPT = "BANKRUPT";
    public static final String LOSE_A_TURN = "LOSE A TURN";
    private static final String PUZZLE_PATH = "src/main/resources/puzzles.txt";
    private List<Puzzle> puzzles;
    private String[] spinValues;

    public WheelOfPizza() {
        initializePuzzleList();
        initializeSpinValues();
    }

    //Returns a random puzzle from list,
    // and removes it so it's not repeated during the same game instance
    public Puzzle getRandomPuzzle() {
        Random random = new Random();
        int i = random.nextInt(puzzles.size());
        Puzzle puzzle = puzzles.get(i);
        puzzles.remove(i);
        return puzzle;
    }

    private void initializeSpinValues() {
        spinValues = new String[] {"2500", "300", "300", "300", "300", "300", "300",
                "600", "500", BANKRUPT, "550", "400", "900", "500", "900", BANKRUPT,
                "600", "400", LOSE_A_TURN, "800", "350", "450", "700", "600"};
    }

    private void initializePuzzleList() {
        puzzles = readPuzzlesFromFile(PUZZLE_PATH);
    }

    private List<Puzzle> readPuzzlesFromFile(String filePath) {
        String line = "";
        List<Puzzle> puzzles = new ArrayList<Puzzle>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                Puzzle puzzle = new Puzzle(line, bufferedReader.readLine());
                bufferedReader.readLine();
                //System.out.println(puzzle);
                puzzles.add(puzzle);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");
        }

        return puzzles;
    }


}
