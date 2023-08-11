package btd.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import btd.controller.Game;
import btd.view.menu.GameOverMenu;
import btd.view.menu.StartingMenu;


/**
 * This class represents the graphical user interface of the game.
 * It displays different panels such as the starting menu, game view, and game over menu.
 */
public class View extends JFrame {
    private static final long serialVersionUID = 102824L;
    private final JLayeredPane mainPanel;
    private final StartingMenu menuPanel;

    private final GameOverMenu gameOverMenu;

    private final GameView gameView;
    private Game gameEngine;
    //private final JFrame frame;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 720;

    /**
     * Standard constructor for View with a specified game engine.
     *
     * @param gameEngine game engine associated with this view.
     */
    public View(final Game gameEngine) {
        this.gameEngine = gameEngine;
        this.mainPanel = new JLayeredPane();
        this.menuPanel = new StartingMenu(this.gameEngine);

        this.gameOverMenu = new GameOverMenu();
        this.gameOverMenu.getSaveScore().addActionListener(e -> {
            final String userName = this.gameOverMenu.getPlayerName();
            if (!userName.isEmpty()) {
                this.gameEngine.getGameModel().getRankController().addScore(
                        this.gameEngine.getGameModel().getMapManager().getMapName(), this.gameOverMenu.getPlayerName(), 
                        this.gameEngine.getGameModel().getPlayer().getScore());
                this.gameEngine.restartGame();
            }
        });
        this.gameView = new GameView(this.gameEngine);
        this.mainPanel.add(menuPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(gameView, JLayeredPane.PALETTE_LAYER);
        this.mainPanel.add(gameOverMenu, JLayeredPane.MODAL_LAYER);

        this.menuPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.gameView.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.gameOverMenu.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setVisible(true);
    }

    /**
     * Renders the starting menu, making it visible and hiding other panels.
     */
    public void renderMenu() {
        this.mainPanel.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.setLayer(gameView, JLayeredPane.PALETTE_LAYER);
        this.mainPanel.setLayer(gameOverMenu, JLayeredPane.MODAL_LAYER);
        this.menuPanel.setVisible(true);
        this.gameView.setVisible(false);
        this.gameOverMenu.setVisible(false);
        this.menuPanel.requestFocus();
        this.menuPanel.repaint();
    }

     /**
     * Renders the game view, making it visible and hiding other panels.
     */
    public void renderGame() {
        this.mainPanel.setLayer(menuPanel, JLayeredPane.PALETTE_LAYER);
        this.mainPanel.setLayer(gameView, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.setLayer(gameOverMenu, JLayeredPane.MODAL_LAYER);
        this.menuPanel.setVisible(false);
        this.gameOverMenu.setVisible(false);
        this.gameView.setVisible(true);
        this.gameView.requestFocus();
        this.gameView.repaint();
    }

    /**
     * Renders the game over menu, making it visible and hiding other panels.
     */
    public void renderGameOver() {
        this.mainPanel.setLayer(menuPanel, JLayeredPane.PALETTE_LAYER);
        this.mainPanel.setLayer(gameView, JLayeredPane.MODAL_LAYER);
        this.mainPanel.setLayer(gameOverMenu, JLayeredPane.DEFAULT_LAYER);
        this.menuPanel.setVisible(false);
        this.gameView.setVisible(false);
        this.gameOverMenu.setVisible(true);
        this.gameOverMenu.requestFocusForPlayerName();
        this.gameOverMenu.repaint();
    }

    /**
     * Sets the game engine associated with this view.
     *
     * @param gameEngine game engine to set.
     */
    public void setGameEngine(final Game gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Gets the game view panel.
     *
     * @return GameView panel.
     */
    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Restarts the view by disposing of the current window.
     */
    public void restart() {
        this.dispose();
    }
}
