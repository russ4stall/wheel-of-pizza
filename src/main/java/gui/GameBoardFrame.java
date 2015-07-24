package main.java.gui;

import main.java.Game;

import javax.swing.*;
import java.awt.*;

/**
 * @author Russ Forstall
 */
public class GameBoardFrame extends JFrame {

    public GameBoardFrame(Game game) throws HeadlessException {
        super("Wheel Of Pizza");

        TopGamePanel topGamePanel = new TopGamePanel(game);
        topGamePanel.setPreferredSize(new Dimension(900, 300));
        BottomGamePanel bottomGamePanel = new BottomGamePanel(game, topGamePanel);
        topGamePanel.setBottomGamePanel(bottomGamePanel);

        setLayout(new FlowLayout());
        add(topGamePanel);
        add(bottomGamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
