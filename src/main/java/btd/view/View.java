package btd.view;

import btd.model.Game;
import btd.model.GameModel;
import btd.model.map.MapPanel;
import btd.view.menu.MainMenu;
import btd.view.menu.StartingMenu;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private final JLayeredPane mainPanel;
    private final StartingMenu menuPanel;
    private final GameView gameView;
    private Game gameEngine;
    //private final JFrame frame;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 720;

    public View(Game gameEngine) {
        this.gameEngine = gameEngine;
        //this.frame = new JFrame();
        this.mainPanel = new JLayeredPane();
        //System.out.print("\n 1 view");
        this.menuPanel = new StartingMenu(this.gameEngine);
        //System.out.print("\n 2 view");
        this.gameView = new GameView(this.gameEngine);
        //System.out.print("\n inizo view");
        this.mainPanel.add(menuPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(gameView, JLayeredPane.PALETTE_LAYER);

        this.menuPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.gameView.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setSize(new Dimension(1200,720));
        this.setVisible(true);
        //System.out.print("fine view");
    }

    public void renderMenu() {
        this.mainPanel.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.setLayer(gameView, JLayeredPane.PALETTE_LAYER);
        this.menuPanel.setVisible(true);
        this.gameView.setVisible(false);
        this.menuPanel.requestFocus();
        this.menuPanel.repaint();
    }

    public void renderGame() {
        this.mainPanel.setLayer(menuPanel, JLayeredPane.PALETTE_LAYER);
        this.mainPanel.setLayer(gameView, JLayeredPane.DEFAULT_LAYER);
        this.menuPanel.setVisible(false);
        this.gameView.setVisible(true);
        this.gameView.requestFocus();
        this.gameView.repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);
    }

    public void setGameEngine(Game gameEngine){
        this.gameEngine = gameEngine;
    }

    public GameView getGameView(){
        return this.gameView;
    }
}
