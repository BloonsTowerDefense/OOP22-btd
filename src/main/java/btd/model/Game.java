package btd.model;

import javax.swing.*;
import java.awt.*;
import btd.view.menu.MainMenu;
import btd.model.map.MapPanel;

public class Game implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;
    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JFrame frame;

    public void start() {
        this.player = new Player();
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();

        // Initialize UI components
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        mainMenu = new MainMenu();
        mapPanel = new MapPanel(this);
        contentPane.add(mainMenu, "MENU");
        contentPane.add(mapPanel, "MAP");

        JButton playButton = mainMenu.getPlayButton();
        playButton.addActionListener(actionEvent -> {
            System.out.println("Start");

            cardLayout.show(contentPane, "MAP");

            mapPanel.startGameThread();
        });

        JButton exitButton = mainMenu.getExitButton();
        exitButton.addActionListener(actionEvent -> {
            System.exit(0);
        });

        frame = new JFrame("Bloons Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
    }

    private void init() {
        // componenti del gioco finestra risorse ecc
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
