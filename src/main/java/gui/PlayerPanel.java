package main.java.gui;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Russ Forstall
 */
public class PlayerPanel extends JPanel {
    public JLabel nameLbl;
    public JLabel scoreLbl;

    public PlayerPanel(String imagePath) {
        super();
        nameLbl = new JLabel("");
        scoreLbl = new JLabel("");

        setPreferredSize(new Dimension(250, 200));
        setLayout(new FlowLayout());

        add(nameLbl);
        add(scoreLbl);

        BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image);

        add(new JLabel(icon));

    }

    public void addBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
    }

    public void removeBorder() {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
