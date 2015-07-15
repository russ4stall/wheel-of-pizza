package main.java;

import main.java.game.Player;
import main.java.game.Puzzle;
import main.java.game.WheelOfPizza;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Russ Forstall
 */
public class Game {
    private Puzzle puzzle;
    private List<Player> players;

    public Game() {
        players = new ArrayList<Player>();
    }

    public static void main(String[] args) {
        //Create players
        Player p1 = new Player(1, "Russ");
        Player p2 = new Player(2, "Jesse");

        WheelOfPizza wop = new WheelOfPizza();
        Game game = new Game();
        game.puzzle = wop.getRandomPuzzle();
        game.players.add(p1);
        game.players.add(p2);

        game.start();

        System.out.println(game.puzzle);
    }

    public void start() {


    }
}
