package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;

import main.java.Game;


public class LetterBoard extends JPanel {

    private static final int
            BOX_WIDE = 30,
            BOX_HIGH = 30,
            SPACING = 2,
            FONT = 25,
            CHARACTERS_WIDE = 12 * BOX_WIDE,
            CHARACTERS_HIGH = 4 * BOX_HIGH;

    private Game game;
    private TopGamePanel bottomGamePanel;
    private String puzzlePhrase;
    private JLabel categoryLabel = new JLabel();

    public LetterBoard (Game g, TopGamePanel b){
        super();
        //setBackground(Color.green);
        bottomGamePanel = b;
        game = g;
        puzzlePhrase = game.getPuzzle().getPhrase();
        categoryLabel.setText(game.getPuzzle().getCategory());
        add(categoryLabel);

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


            // Draw letter in this box if it has been revealed
            // set to true to draw all letters as of now

            if (bottomGamePanel.isInList(phrase.charAt(i))) {
            //   if (true) {

                g.setColor(Color.BLACK);
                g.setFont(new Font("Bitstream Vera Sans Mono", Font.PLAIN, FONT));
                drawLetter(g, ("" + phrase.charAt(i)).toUpperCase(), row, col);
            }
        }
    }

    private void paintLetterBox(Graphics g, int row, int col, boolean b) {

        if(b == true)
            g.setColor(Color.YELLOW.darker());
        else
            g.setColor(Color.RED.darker());

        g.fillRect(
                (getWidth() - CHARACTERS_WIDE) / 2 + col * (BOX_WIDE + SPACING),
                (getHeight() - CHARACTERS_HIGH) / 3 + row * (BOX_HIGH + SPACING),
                BOX_WIDE,
                BOX_HIGH);
    }

    private void drawLetter(Graphics g, String str, int row, int col) {
        g.drawString( str,
                (getWidth() - CHARACTERS_WIDE) / 2 + col * (BOX_WIDE + SPACING) + BOX_WIDE / 8,
                (getHeight() - CHARACTERS_HIGH) / 3 + (row + 1) * (BOX_HIGH + SPACING) - BOX_HIGH / 6);
    }

    public void setPuzzlePhrase(String puzzlePhrase) {
        this.puzzlePhrase = puzzlePhrase;
    }
}
