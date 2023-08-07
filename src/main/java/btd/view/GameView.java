package btd.view;

import btd.model.Game;
import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.model.entity.Tower;
import btd.model.map.MapPanel;
import btd.utils.Position;
import btd.view.menu.ShopMenu;
import btd.view.menu.TowerUpgradeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JPanel {

    private final ShopMenu towerShopMenu;
    private final MapPanel mapPanel;
    private final Game gameEngine;

    private String towerToPlace = "";

    public GameView(Game gameEngine) {
        this.gameEngine = gameEngine;
        setLayout(new BorderLayout());
        towerShopMenu = new ShopMenu();
        mapPanel = new MapPanel(this.gameEngine);

        CardLayout cardLayout = new CardLayout();
        JPanel shopUpgradePanel = new JPanel(cardLayout);
        shopUpgradePanel.setPreferredSize(new Dimension(200, 720));
        shopUpgradePanel.add(towerShopMenu, "SHOP");

        cardLayout.show(shopUpgradePanel, "SHOP");

        setupTowerButtons();
        setupMapPanel(cardLayout, shopUpgradePanel);

        add(mapPanel, BorderLayout.CENTER);
        add(shopUpgradePanel, BorderLayout.EAST);
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
                    Tower tower = createTowerByType(spriteX, spriteY);
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
            case "blackAdam" -> new ShootingTower("blackAdam", 100, 100, new Position(spriteX, spriteY));
            case "voldelife" -> new ShootingTower("voldelife", 100, 100, new Position(spriteX, spriteY));
            case "deadColossus" -> new ShootingTower("deadColossus", 100, 100, new Position(spriteX, spriteY));
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
}
