package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Russ Forstall
 */
public class MainMenuFrame extends JFrame {
    public MainMenuFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(300,100));
        add(new MainMenuPanel());


    }

    class MainMenuPanel extends JPanel {
        private JButton createBtn = new JButton("CREATE GAME");
        private JButton joinBtn = new JButton("JOIN GAME");
        String ipAddress;

        public MainMenuPanel() {
            super();
            setLayout(new FlowLayout());

            createBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    // Set up socket server
                    // Display Ip and wait for opponent (client)
                }
            });

            joinBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //show text input for server ip
                    ipAddress = JOptionPane.showInputDialog("Please input the IP address of your opponent");
                    //
                }
            });

            add(createBtn, BorderLayout.CENTER);
            add(joinBtn, BorderLayout.CENTER);

        }
    }
}
