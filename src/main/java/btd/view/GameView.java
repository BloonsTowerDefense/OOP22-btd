package btd.view;

import btd.model.Game;
import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.model.entity.Tower;
import btd.model.map.MapPanel;
import btd.utils.Position;
import btd.view.menu.GameOverMenu;
import btd.view.menu.ShopMenu;
import btd.view.menu.StatsMenu;
import btd.view.menu.TowerUpgradeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JPanel {

    private final ShopMenu towerShopMenu;
    private final MapPanel mapPanel;

    private final StatsMenu statsMenu;

    private final Game gameEngine;

    private String towerToPlace = "";

    public GameView(Game gameEngine) {
        this.gameEngine = gameEngine;
        setLayout(new BorderLayout());
        towerShopMenu = new ShopMenu();
        mapPanel = new MapPanel(this.gameEngine);
        statsMenu = new StatsMenu();
        //gameEngine.getRankController().addScore(gameEngine.getGameModel().getMapManager().getMapName(), "Nome", 1028);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.PAGE_AXIS));

        CardLayout cardLayout = new CardLayout();
        JPanel shopUpgradePanel = new JPanel(cardLayout);
        shopUpgradePanel.setPreferredSize(new Dimension(200, 620));
        shopUpgradePanel.add(towerShopMenu, "SHOP");

        cardLayout.show(shopUpgradePanel, "SHOP");

        setupTowerButtons();
        setupMapPanel(cardLayout, shopUpgradePanel);

        eastPanel.add(statsMenu);
        eastPanel.add(shopUpgradePanel);

        add(mapPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
    }

    private void setupTowerButtons() {
        towerShopMenu.getBlackAdam().addActionListener(e -> towerToPlace = "blackAdam");
        towerShopMenu.getVoldelife().addActionListener(e -> towerToPlace = "voldelife");
        towerShopMenu.getDeadColossus().addActionListener(e -> towerToPlace = "deadColossus");
        towerShopMenu.getRangeEnhancer().addActionListener(e -> towerToPlace = "rangeEnhancer");
        towerShopMenu.getPowerEnhancer().addActionListener(e -> towerToPlace = "powerEnhancer");
    }

    private void setupMapPanel(CardLayout cardLayout, JPanel shopUpgradePanel) {
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int spriteX = e.getX();
                int spriteY = e.getY();

                Tower clickedTower = gameEngine.getGameModel().isTower(spriteX, spriteY);

                if (clickedTower != null) {
                    TowerUpgradeMenu upgradeMenu = new TowerUpgradeMenu(clickedTower);
                    shopUpgradePanel.add(upgradeMenu, "UPGRADE");
                    upgradeMenu.getUpgradeButton().addActionListener(event -> cardLayout.show(shopUpgradePanel, "SHOP"));
                    cardLayout.show(shopUpgradePanel, "UPGRADE");
                }

                if (mapPanel.getMapManager().canPlace(spriteX, spriteY) && !towerToPlace.isEmpty()) {
                    gameEngine.getRankController().addScore(gameEngine.getGameModel().getMapManager().getMapName(), "Nome", 1028);
                    System.out.println("Vecchi x e y: " + spriteX + " " + spriteY);
                    int newX = spriteX/48;
                    int newY = spriteY/48;
                    System.out.println("Nuovi x e y: " + newX + " " + newY);
                    Tower tower = createTowerByType(newX*48, newY*48);
                    if (tower != null) {
                        mapPanel.getGameModel().addTower(tower);
                    }
                    towerToPlace = "";
                }
            }
        });
    }

    private Tower createTowerByType(int spriteX, int spriteY) {
        return switch (towerToPlace) {
            case "blackAdam" -> new ShootingTower("blackAdam", 0, 100, new Position(spriteX, spriteY));
            case "voldelife" -> new ShootingTower("voldelife", 5, 100, new Position(spriteX, spriteY));
            case "deadColossus" -> new ShootingTower("deadColossus", 5, 100, new Position(spriteX, spriteY));
            case "rangeEnhancer" -> new HelpingTower("rangeEnhancer", "Range", new Position(spriteX, spriteY));
            case "powerEnhancer" -> new HelpingTower("powerEnhancer", "Power", new Position(spriteX, spriteY));
            default -> null;
        };
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mapPanel.paintComponent(g);
        System.out.println("\n paint gameView");
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setPlayerLife(String life){
        this.statsMenu.setLifeLabel(life);
    }

    public void setPlayerMoney(String money){
        this.statsMenu.setMoneyLabel(money);
    }
}
