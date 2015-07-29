package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import main.java.Game;


public class LetterBoard extends JPanel {

    private static final int
            SQUARE_WIDTH = 30,
            SQUARE_HEIGHT = 30,
            SPACE_WIDTH = 2,
            PUZZLE_WIDTH = 12 * SQUARE_WIDTH,
            PUZZLE_HEIGHT = 4 * SQUARE_HEIGHT,
            FONT_SIZE = 25;

    private Game game;
    private TopGamePanel bottomGamePanel;
    private String puzzlePhrase;

    public LetterBoard (Game g, TopGamePanel b){
        super();
        setBackground(Color.green);
        bottomGamePanel = b;
        game = g;
        puzzlePhrase = game.getPuzzle().getPhrase();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set this to see text/box output
        String phrase = puzzlePhrase;

        // Draw each letter box
        for (int i = 0; i < phrase.length(); ++i) {
            int row = i / 12, col = i % 12;

            paintLetterBox(g, row, col, phrase.charAt(i) == ' ');

            //debug **********
            System.out.print(phrase.charAt(i) + "_");

            //System.out.print(phrase.charAt(i) + "_");


            // Draw letter in this box if it has been revealed
            // set to true to draw all letters as of now

            if (bottomGamePanel.isInList(phrase.charAt(i))) {
            //   if (true) {

                g.setColor(Color.BLACK);
                g.setFont(new Font("Bitstream Vera Sans Mono", Font.PLAIN, FONT_SIZE));
                drawLetter(g, ("" + phrase.charAt(i)).toUpperCase(), row, col);
            }
        }
    }

    private void paintLetterBox(Graphics g, int row, int col, boolean b) {

        //g.setColor(b ? Color.GREEN.darker() : Color.WHITE);
        if(b == true)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.WHITE);

        g.fillRect((getWidth() - PUZZLE_WIDTH) / 2 + col
                * (SQUARE_WIDTH + SPACE_WIDTH), (getHeight() - PUZZLE_HEIGHT) / 3
                + row * (SQUARE_HEIGHT + SPACE_WIDTH), SQUARE_WIDTH, SQUARE_HEIGHT);
    }

    private void drawLetter(Graphics g, String str, int row, int col) {
        g.drawString(str, (getWidth() - PUZZLE_WIDTH) / 2 + col
                        * (SQUARE_WIDTH + SPACE_WIDTH) + SQUARE_WIDTH / 8,
                (getHeight() - PUZZLE_HEIGHT) / 3 + (row + 1)
                        * (SQUARE_HEIGHT + SPACE_WIDTH) - SQUARE_HEIGHT / 6);
    }
}
