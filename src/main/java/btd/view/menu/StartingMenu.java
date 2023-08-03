package btd.view.menu;

import javax.swing.*;
import java.awt.*;

public class StartingMenu extends JPanel {

    private final CardLayout cardLayout;
    private final MainMenu mainMenu;
    private final DifficultyMenu difficultyMenu;

    public StartingMenu() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        mainMenu = new MainMenu();
        difficultyMenu = new DifficultyMenu();

        add(mainMenu, "MAIN");
        add(difficultyMenu, "DIFFICULTY");

        mainMenu.getPlayButton().addActionListener(e -> cardLayout.show(this, "DIFFICULTY"));

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
}
