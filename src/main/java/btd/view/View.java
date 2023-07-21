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
    private final MapPanel gamePanel;
    private final Game gameEngine;

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public ViewImpl(final Game gameEngine) {
        this.gameEngine = gameEngine;
        this.mainPanel = new JLayeredPane();
        this.menuPanel = new MainMenu();
        this.gamePanel = new MapPanel();

        Dimension windowSize  = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        mainPanel.setSize(windowSize);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
    }


}
