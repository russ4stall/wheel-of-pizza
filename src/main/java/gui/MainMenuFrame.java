package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        GameBoardFrame gameBoardFrame = new GameBoardFrame();
        TopGamePanel topGamePanel = new TopGamePanel();
        BottomGamePanel bottomGamePanel = new BottomGamePanel();
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
