package btd.view.menu;

import btd.model.Game;
import btd.view.GameCondition;

import javax.swing.*;
import java.awt.*;

public class StartingMenu extends JPanel {

    private final CardLayout cardLayout;
    private final MainMenu mainMenu;
    private final DifficultyMenu difficultyMenu;
    private Game gameEngine;

    public StartingMenu(Game gameEngine) {
        this.gameEngine = gameEngine;
        System.out.println("\nCostruttore starting menu");
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        System.out.println("\nCreo main menu");
        mainMenu = new MainMenu();
        difficultyMenu = new DifficultyMenu();

        add(mainMenu, "MAIN");
        add(difficultyMenu, "DIFFICULTY");

        mainMenu.getPlayButton().addActionListener(e -> cardLayout.show(this, "DIFFICULTY"));
        difficultyMenu.getStartButton().addActionListener(e -> this.gameEngine.setGameCondition(GameCondition.PLAY));


        setPreferredSize(new Dimension(1200, 720));
    }

    public JButton getPlayButton() {
        return difficultyMenu.getStartButton();
    }

    public String getMapName() {
        return difficultyMenu.getMap();
    }

    public String getDifficulty() {
        return difficultyMenu.getDifficulty();
    }

    public void paint(final Graphics g) {
        super.paint(g);
    }
}
