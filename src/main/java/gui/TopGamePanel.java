package main.java.gui;

import main.java.Game;
import main.java.game.WheelOfPizza;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Russ Forstall
 */
public class TopGamePanel extends JPanel {
    private Game game;
    private String spinResult;

    public TopGamePanel(final Game game) {
        super();
        this.game = game;

        final JLabel spinResultLbl = new JLabel("");
        final JButton spinBtn = new JButton("SPIN");
        spinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (spinPizzaWheel()) {
                    // TODO: player needs to choose consonant
                } else {
                    game.turnOver();
                }

                spinResultLbl.setText(spinResult);
            }
        });

        add(spinBtn);
        add(spinResultLbl);

    }

    /**
     * Sets spinResult with random spinValue
     * @return False if players loses turn
     */
    private boolean spinPizzaWheel() {
        String s = game.getWop().getRandomSpinResult();
        spinResult = s;
        return !(s.equals(WheelOfPizza.LOSE_A_TURN) || s.equals(WheelOfPizza.BANKRUPT));
    }

    public String getSpinResult() {
        return spinResult;
    }

    public int getSpinValue() {
        if (spinResult.equals(WheelOfPizza.LOSE_A_TURN) || spinResult.equals(WheelOfPizza.BANKRUPT)) {
            return 0;
        }
        return Integer.valueOf(spinResult);
    }
}

