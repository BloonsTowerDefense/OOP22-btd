package btd.model;

import javax.swing.*;
import java.awt.*;
import btd.view.menu.MainMenu;
import btd.model.map.MapPanel;
import btd.model.entity.Bloon;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;
    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JFrame frame;
    private LevelImpl level;
    private List<Bloon> bloons = new CopyOnWriteArrayList<>(); // CopyOnWriteArrayList per evitare errori di Concurrent Modification durante update di bloon.

    private long lastUpdateTime;

    public void start() {
        this.player = new Player();
        this.level = new LevelImpl("facile");
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

            startGameThread();
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
        double interval = 1000000000/4; //60
        double nextDraw = System.nanoTime() + interval;
        while(gameThread != null){
            //System.out.println("Is running");
            update();
            repaint();
            try {
                double rest = nextDraw - System.nanoTime();
                rest = ((rest/1000000) > 0 ? rest/1000000 : 0);
                Thread.sleep((long) rest);
                nextDraw += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        // componenti del gioco finestra risorse ecc
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        Wave initialWave = level.getWave();
        System.out.println(initialWave);
        if (initialWave != null) {
            new Thread(() -> {
                for (Bloon bloon : initialWave.getBloons()) {
                    try {
                        Thread.sleep(600); // 600 millisecond delay between each spawn
                        bloons.add(bloon);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mapPanel.setBloons(bloons); // Pass the list of bloons to mapPanel
            }).start();
        }
        gameThread.start();
        lastUpdateTime = System.currentTimeMillis();
    }


    public void update() {
        for (Bloon bloon : bloons) {
            bloon.update(System.currentTimeMillis() - lastUpdateTime);
        }
        lastUpdateTime = System.currentTimeMillis();
    }


    private void repaint() {
        // Redraw
        if (mapPanel != null) {
            mapPanel.repaint();
        }
    }
}
