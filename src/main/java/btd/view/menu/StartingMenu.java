package btd.view.menu;

import btd.controller.Game;
import btd.model.map.MapPanel;
import btd.utils.SoundManager;
import btd.view.GameCondition;
import btd.view.score.RankView;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The StartingMenu class represents a graphical user interface panel for the starting menu of the
 * game. This panel provides options to navigate to different sections of the game, such as choosing
 * difficulty, viewing leaderboards, and starting the game.
 */
public class StartingMenu extends JPanel {

  private final CardLayout cardLayout;
  private final DifficultyMenu difficultyMenu;
  private final RankView rankView;
  private final Game gameEngine;

  /**
   * Constructs a StartingMenu object. Initializes the panel layout, main menu, difficulty menu, and
   * leaderboard view.
   *
   * @param gameEngine the game engine controller
   */
  public StartingMenu(Game gameEngine) {
    this.gameEngine = gameEngine;
    cardLayout = new CardLayout();
    setLayout(cardLayout);
    MainMenu mainMenu = new MainMenu();
    this.difficultyMenu = new DifficultyMenu();
    this.rankView = new RankView(this.gameEngine.getRankController());

    add(mainMenu, "MAIN");
    add(difficultyMenu, "DIFFICULTY");
    add(rankView, "LEADERBOARD");

    mainMenu
        .getPlayButton()
        .addActionListener(
            e -> {
              cardLayout.show(this, "DIFFICULTY");
              SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
            });
    mainMenu
        .getLeaderboardButton()
        .addActionListener(
            e -> {
              SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
              this.rankView.paintPanel();
              cardLayout.show(this, "LEADERBOARD");
            });
    mainMenu.getExitButton().addActionListener(e -> System.exit(0));

    rankView
        .getBackButton()
        .addActionListener(
            e -> {
              cardLayout.show(this, "MAIN");
              SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
              this.rankView.resetPanel();
            });
    difficultyMenu
        .getBackButton()
        .addActionListener(
            e -> {
              cardLayout.show(this, "MAIN");
              SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
            });
    difficultyMenu
        .getStartButton()
        .addActionListener(
            e -> {
              SoundManager.getInstance().playSound(SoundManager.SoundType.BUTTON, false);
              MapPanel tmp = this.gameEngine.getView().getGameView().getMapPanel();
              this.gameEngine.getGameModel().initGame(this.getDifficulty(), this.getMapName());
              tmp.setNewMapManager(this.gameEngine.getGameModel().getMapManager());
              this.gameEngine.setGameCondition(GameCondition.PLAY);
            });

    setPreferredSize(new Dimension(1200, 720));
  }

  /**
   * Retrieves the selected map name from the difficulty menu.
   *
   * @return the selected map name
   */
  public String getMapName() {
    return difficultyMenu.getMap();
  }

  /**
   * Retrieves the selected difficulty level from the difficulty menu.
   *
   * @return the selected difficulty level
   */
  public String getDifficulty() {
    return difficultyMenu.getDifficulty();
  }

  @Override
  public void paint(final Graphics g) {
    super.paint(g);
  }
}
