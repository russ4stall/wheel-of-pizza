package main.java.gui;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.*;

import main.java.gui.TopGamePanel;

public class Spinner extends JPanel{

    private static final String PICTURE_PATH = "src/main/resources/pizzaSpinner.gif";
    private static final int
            ANIMATION_TIME 		= 25,
            CIRCLE_DEGREES 		= 360,
            DEGREE_INCREMENT 	= 10,
            PIZZA_X_COOR 		= 100,
            PIZZA_Y_COOR 		= 10;

    private ImageIcon icon;
    private JLabel iconLabel;
    private static int rotateDegree = 10;
    private boolean runTimer;
    private java.util.Timer spinLength;
    private javax.swing.Timer spinnerTimer;

    // constructor
    public Spinner() {
        //setBackground(Color.pink);

        icon = new ImageIcon(PICTURE_PATH);
        iconLabel = new JLabel();
        this.setLayout(new BorderLayout());

        // length determines animation duration, timer is time between animation frames
        spinLength = new java.util.Timer();
        spinnerTimer = new javax.swing.Timer( ANIMATION_TIME, new ActionListener() {

            // action performed by Timer
            //determines how much to rotate image from original position
            //resets to 0 after a full turn
            @Override
            public void actionPerformed(ActionEvent e) {

                rotateDegree += DEGREE_INCREMENT;
                if( rotateDegree == CIRCLE_DEGREES){ rotateDegree = 0; }
                repaint();
            }

        });

        //begins timer, repeat to false to stop continual spinning
        spinnerTimer.setRepeats( false );
        spinnerTimer.start();

        //add image to panel


        add(iconLabel);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D )g.create();

        // coordinates for image center
        int cWidth = icon.getIconWidth() / 2;
        int cHeight = icon.getIconHeight() / 2;

        // bounds for image
        Rectangle r = new Rectangle(PIZZA_X_COOR, PIZZA_Y_COOR, icon.getIconWidth()+110, icon.getIconHeight()+110);
        g2.setClip(r);

        // rotate image
        AffineTransform original = g2.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians( rotateDegree ), PIZZA_X_COOR + cWidth, PIZZA_Y_COOR + cHeight);
        g2.setTransform(at);

        //draw
        icon.paintIcon(this, g2, PIZZA_X_COOR, PIZZA_Y_COOR);
        g2.setTransform(original);

        // activate animation
        if(runTimer == true)
            spinnerTimer.start();
    }

    public void spinTheWheel() {

        // debugging
        System.out.println("spinBtn clicked.");

        runTimer = true;
        repaint();
        spinLength.schedule( new SpinLegth() , 2000);


    }

    class SpinLegth extends TimerTask {
        @Override
        public void run() {	runTimer = false; }
    }
} 



