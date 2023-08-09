package btd.view.menu;

import btd.controller.score.RankController;
import btd.model.Game;
import btd.model.map.MapPanel;
import btd.model.score.RankModel;
import btd.utils.SoundManager;
import btd.view.GameCondition;
import btd.view.score.RankView;

import javax.swing.*;
import java.awt.*;

public class StartingMenu extends JPanel {

    private final CardLayout cardLayout;
    private final MainMenu mainMenu;
    private final DifficultyMenu difficultyMenu;

    private final RankView rankView;
    private Game gameEngine;

    public StartingMenu(Game gameEngine) {
        this.gameEngine = gameEngine;
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.mainMenu = new MainMenu();
        this.difficultyMenu = new DifficultyMenu();
        this.rankView = new RankView(this.gameEngine.getRankController());

        add(mainMenu, "MAIN");
        add(difficultyMenu, "DIFFICULTY");
        add(rankView, "LEADERBOARD");

        mainMenu.getPlayButton().addActionListener(e -> {
            cardLayout.show(this, "DIFFICULTY");
            SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
        });
        mainMenu.getLeaderboardButton().addActionListener(e -> {
            SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
            this.rankView.paintPanel();
            cardLayout.show(this, "LEADERBOARD");
        });
        rankView.getBackButton().addActionListener(e -> {
            cardLayout.show(this,"MAIN");
            SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
            this.rankView.resetPanel();
        });
        difficultyMenu.getBackButton().addActionListener(e -> {
            cardLayout.show(this,"MAIN");
            SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
        });
        difficultyMenu.getStartButton().addActionListener(e -> {
            System.out.println("\n\n\n\nTASTO PLAY PREMUTO\n\n\n");
            SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
            MapPanel tmp = this.gameEngine.getView().getGameView().getMapPanel();
            System.out.println("Diff: " + this.getDifficulty() + "\n\nMapName " + this.getMapName());
            this.gameEngine.getGameModel().initGame(this.getDifficulty(),this.getMapName());
            tmp.setNewMapManager(this.gameEngine.getGameModel().getMapManager());
            this.gameEngine.setGameCondition(GameCondition.PLAY) ;
        });

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
