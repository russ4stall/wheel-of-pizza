package main.java.gui;

import main.java.Game;
import main.java.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author Russ Forstall
 */
public class MainMenuFrame extends JFrame {
    public MainMenuFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(300,100));
        add(new MainMenuPanel());
    }

    class MainMenuPanel extends JPanel  {
        private final int PORT_NUMBER = 86754;
        private JButton createBtn = new JButton("CREATE GAME");
        private JButton joinBtn = new JButton("JOIN GAME");
        String serverIp;
        String clientIp;

        public MainMenuPanel() {
            super();

            setLayout(new FlowLayout());

            createBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    serverIp = getUserLanIp();

                    // TODO: Display "Waiting for opponent" dialog (also show IP)
                    // TODO: Open socket server and wait for connection from client

                    showGameBoard();
                }
            });

            joinBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //show text input for server ip
                    serverIp = JOptionPane.showInputDialog("Please input the IP address of your opponent");
                    // TODO: Connect to server socket using serverIp
                }
            });

            add(createBtn, BorderLayout.CENTER);
            add(joinBtn, BorderLayout.CENTER);

        }
    }

    /**
     * Create panels and game object and start the game
     */
    public void showGameBoard() {

        Game game = new Game();
        game.getPlayers().add(new Player(1, "Papa John", ""));
        game.getPlayers().add(new Player(2, "Hungry Howie", ""));

        GameBoardFrame gameBoardFrame = new GameBoardFrame(game);

        // Close Main Menu Frame
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        this.setVisible(false);
    }

    public String getUserLanIp() {
        String ip = "";
        try {

            for (final Enumeration<NetworkInterface> interfaces =
                         NetworkInterface.getNetworkInterfaces();
                 interfaces.hasMoreElements();
                    ) {
                final NetworkInterface cur = interfaces.nextElement();

                if (cur.isLoopback()) {
                    continue;
                }

                for (final InterfaceAddress addr : cur.getInterfaceAddresses()) {
                    final InetAddress inet_addr = addr.getAddress();

                    if (!(inet_addr instanceof Inet4Address)) {
                        continue;
                    }

                    ip = inet_addr.getHostAddress();
                }
            }
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        return ip;
    }
}
