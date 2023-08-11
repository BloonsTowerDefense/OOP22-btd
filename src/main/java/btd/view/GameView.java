package btd.view;

import btd.controller.Game;
import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.model.entity.Tower;
import btd.utils.Position;
import btd.utils.SoundManager;
import btd.view.menu.ShopMenu;
import btd.view.menu.StatsMenu;
import btd.view.menu.TowerUpgradeMenu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The GameView class represents the graphical user interface for the game.
 * It displays the game map, tower shop, tower upgrade menu, and player stats.
 * Players can interact with the game by placing towers and upgrading them.
 */
public class GameView extends JPanel {

  private final ShopMenu towerShopMenu;
  private final MapPanel mapPanel;

  private final StatsMenu statsMenu;

  private final Game gameEngine;

  private String towerToPlace = "";

  /**
   * Constructs a new GameView with the specified game engine.
   *
   * @param gameEngine The game engine controlling the game logic.
   */
  public GameView(final Game gameEngine) {
    this.gameEngine = gameEngine;
    setLayout(new BorderLayout());
    towerShopMenu = new ShopMenu();
    mapPanel = new MapPanel(this.gameEngine);
    statsMenu = new StatsMenu();

    JPanel eastPanel = new JPanel();
    eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));

    CardLayout cardLayout = new CardLayout();
    JPanel shopUpgradePanel = new JPanel(cardLayout);
    shopUpgradePanel.add(towerShopMenu, "SHOP");
    shopUpgradePanel.setPreferredSize(new Dimension(200, 720));

    cardLayout.show(shopUpgradePanel, "SHOP");

    setupTowerButtons();
    setupMapPanel(cardLayout, shopUpgradePanel);

    eastPanel.add(statsMenu);
    eastPanel.add(shopUpgradePanel);

    add(mapPanel, BorderLayout.CENTER);
    add(eastPanel, BorderLayout.EAST);
  }

  /**
   * Sets up the tower buttons in the tower shop menu.
   */
  private void setupTowerButtons() {
    towerShopMenu.getBlackAdam().addActionListener(e -> towerToPlace = "blackAdam");
    towerShopMenu.getVoldelife().addActionListener(e -> towerToPlace = "voldelife");
    towerShopMenu.getDeadColossus().addActionListener(e -> towerToPlace = "deadColossus");
    towerShopMenu.getRangeEnhancer().addActionListener(e -> towerToPlace = "rangeEnhancer");
    towerShopMenu.getPowerEnhancer().addActionListener(e -> towerToPlace = "powerEnhancer");
  }

  /**
   * Sets up the map panel and handles mouse clicks for tower placement and upgrades.
   *
   * @param cardLayout         The CardLayout used for switching between menus.
   * @param shopUpgradePanel   The panel containing the tower shop and upgrade menus.
   */
  private void setupMapPanel(final CardLayout cardLayout, final JPanel shopUpgradePanel) {
    mapPanel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {

            int spriteX = e.getX();
            int spriteY = e.getY();

            Tower clickedTower = gameEngine.getGameModel().checkIfIsTower(spriteX, spriteY);

            if (clickedTower != null) {
              TowerUpgradeMenu upgradeMenu = new TowerUpgradeMenu(clickedTower);
              shopUpgradePanel.add(upgradeMenu, "UPGRADE");
              upgradeMenu
                  .getUpgradeButton()
                  .addActionListener(event -> cardLayout.show(shopUpgradePanel, "SHOP"));
              cardLayout.show(shopUpgradePanel, "UPGRADE");
              JLabel upgradePrice =
                  new JLabel(clickedTower.getUpgradePrice().toString() + " coins");
              upgradePrice.setFont(new Font("Arial", Font.BOLD, 15));
              upgradePrice.setHorizontalAlignment(SwingConstants.CENTER);
              upgradeMenu.getUpgradeButton().add(BorderLayout.SOUTH, upgradePrice);
              upgradeMenu
                  .getUpgradeButton()
                  .addActionListener(
                      e1 -> {
                        if (gameEngine.getGameModel().getPlayer().getCoins()
                            >= clickedTower.getUpgradePrice()) {
                          gameEngine
                              .getGameModel()
                              .getPlayer()
                              .setCoins(
                                  gameEngine.getGameModel().getPlayer().getCoins()
                                      - clickedTower.getUpgradePrice());
                          clickedTower.update();
                          SoundManager.getInstance()
                              .playSound(SoundManager.SoundType.BUTTON, false);
                        }
                      });
            }

            if (mapPanel.getMapManager().canPlace(spriteX, spriteY) && !towerToPlace.isEmpty()) {
              // gameEngine.getRankController().addScore(gameEngine.getGameModel().getMapManager().getMapName(), "Nome", 1028);
              int newX = spriteX / 48;
              int newY = spriteY / 48;
              Tower tower = createTowerByType(newX * 48, newY * 48);
              if (tower != null) {
                mapPanel.getGameModel().addTower(tower);
              }
              towerToPlace = "";
            }
          }
        });
  }

  /**
   * Creates a tower of the specified type at the given position.
   *
   * @param spriteX The x-coordinate of the tower's position.
   * @param spriteY The y-coordinate of the tower's position.
   * @return The created tower, or null if the tower cannot be created.
   */
  private Tower createTowerByType(final int spriteX, final int spriteY) {
    Tower tower =
        switch (towerToPlace) {
          case "blackAdam" -> new ShootingTower(
              "blackAdam", 1, 100, new Position(spriteX, spriteY));
          case "voldelife" -> new ShootingTower(
              "voldelife", 3, 200, new Position(spriteX, spriteY));
          case "deadColossus" -> new ShootingTower(
              "deadColossus", 5, 300, new Position(spriteX, spriteY));
          case "rangeEnhancer" -> new HelpingTower(
              "rangeEnhancer", "Range", 400, new Position(spriteX, spriteY));
          case "powerEnhancer" -> new HelpingTower(
              "powerEnhancer", "Power", 600, new Position(spriteX, spriteY));
          default -> null;
        };

    return tower != null && this.statsMenu.getMoney() >= tower.getPrice() ? tower : null;
  }

    /**
     * Paints the game view.
     *
     * @param g The graphics object used for painting.
     */
  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    mapPanel.paintComponent(g);
  }

  /**
   * Returns the map panel used in the game view.
   *
   * @return The map panel.
   */
  public MapPanel getMapPanel() {
    return mapPanel;
  }

  /**
   * Sets the player's remaining life.
   *
   * @param life The new value for the player's life.
   */
  public void setPlayerLife(final String life) {
    this.statsMenu.setLifeLabel(life);
  }

  /**
   * Sets the player's available money.
   *
   * @param money The new value for the player's money.
   */
  public void setPlayerMoney(final String money) {
    this.statsMenu.setMoneyLabel(money);
  }
}
