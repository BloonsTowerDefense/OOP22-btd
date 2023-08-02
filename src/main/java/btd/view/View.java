package btd.view;

import btd.model.Game;
import btd.model.GameModel;
import btd.model.map.MapPanel;
import btd.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private final JLayeredPane mainPanel;
    private final MainMenu menuPanel;
    private final GameView gameView;

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public View(final Game gameEngine) {
        this.mainPanel = new JLayeredPane();
        this.menuPanel = new MainMenu();
        this.gameView = new GameView(gameEngine);

        this.mainPanel.add(menuPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(gameView, JLayeredPane.PALETTE_LAYER);

        this.menuPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.gameView.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void renderMenu() {
        mainPanel.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
        mainPanel.setLayer(gameView, JLayeredPane.PALETTE_LAYER);
        menuPanel.setVisible(true);
        gameView.setVisible(false);
        menuPanel.requestFocus();
    }

    public void renderGame() {
        mainPanel.setLayer(menuPanel, JLayeredPane.PALETTE_LAYER);
        mainPanel.setLayer(gameView, JLayeredPane.DEFAULT_LAYER);
        menuPanel.setVisible(false);
        gameView.setVisible(true);
        gameView.requestFocus();
    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);
    }
}
