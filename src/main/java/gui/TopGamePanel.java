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
    private String spinResult = "0";
    private JButton spinBtn;
    private BottomGamePanel bottomGamePanel;

    public TopGamePanel(final Game game) {
        super();
        this.game = game;

        final JLabel spinResultLbl = new JLabel("");
        spinBtn = new JButton("SPIN");
        spinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (spinPizzaWheel()) {
                    setEnabledSpinBtn(false);
                    bottomGamePanel.enableUnusedConsonantButtons();
                    bottomGamePanel.setEnabledForAllVowelButtons(false);
                } else {
                    game.turnOver();
                    if (game.getCurrentPlayersScore() >= WheelOfPizza.VOWEL_COST) {
                        bottomGamePanel.setEnabledForAllVowelButtons(true);
                    }
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
        if (s.equals(WheelOfPizza.BANKRUPT)) {
            game.getCurrentPlayer().setScore(0);
            bottomGamePanel.updateScoreLabels();
        }

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

    public void setEnabledSpinBtn(boolean b) {
        spinBtn.setEnabled(b);
    }

    public BottomGamePanel getBottomGamePanel() {
        return bottomGamePanel;
    }

    public void setBottomGamePanel(BottomGamePanel bottomGamePanel) {
        this.bottomGamePanel = bottomGamePanel;
    }
}

