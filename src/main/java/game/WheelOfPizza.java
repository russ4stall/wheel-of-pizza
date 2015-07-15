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
    private static final String PUZZLE_PATH = "src/main/resources/puzzles.txt";
    private List<Puzzle> puzzles;

    public WheelOfPizza() {
        puzzles = readPuzzlesFromFile(PUZZLE_PATH);
    }

    public Puzzle getRandomPuzzle() {
        Random random = new Random();
        return puzzles.get(random.nextInt(puzzles.size()));
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
