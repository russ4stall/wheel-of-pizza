package main.java.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Russ Forstall
 */
public class PlayerPanel extends JPanel {
    public JLabel nameLbl;
    public JLabel scoreLbl;

    public PlayerPanel() {
        super();
        nameLbl = new JLabel("");
        scoreLbl = new JLabel("");

        setPreferredSize(new Dimension(250, 200));
        setLayout(new FlowLayout());

        add(nameLbl);
        add(scoreLbl);
    }

    public void addBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
    }

    public void removeBorder() {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
