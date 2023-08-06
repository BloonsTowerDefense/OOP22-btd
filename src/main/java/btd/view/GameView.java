package btd.view;

import btd.model.Game;
import btd.model.entity.ShootingTower;
import btd.model.map.MapPanel;
import btd.utils.Position;
import btd.view.menu.ShopMenu;
import btd.view.menu.TowerUpgradeMenu;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final ShopMenu towerShopMenu;
    private final TowerUpgradeMenu towerUpgradeMenu;
    private final MapPanel mapPanel;
    private final Game gameEngine;

    public GameView(final Game gameEngine) {
        setLayout(new BorderLayout());
        this.gameEngine = gameEngine;
        this.towerUpgradeMenu = new TowerUpgradeMenu(new ShootingTower("blackAdam",100,100,new Position(0,0)));
        this.towerShopMenu = new ShopMenu();
        this.mapPanel = new MapPanel(this.gameEngine);

        CardLayout cardLayout = new CardLayout();
        JPanel shopUpgradePanel = new JPanel(cardLayout);
        shopUpgradePanel.setPreferredSize(new Dimension(200,720));
        shopUpgradePanel.add(towerShopMenu,"SHOP");
        shopUpgradePanel.add(towerUpgradeMenu,"UPGRADE");

        cardLayout.show(shopUpgradePanel,"SHOP");

        this.towerShopMenu.getBlackAdam().addActionListener(e -> cardLayout.show(shopUpgradePanel,"UPGRADE"));
        this.towerUpgradeMenu.getUpgradeButton().addActionListener(e -> cardLayout.show(shopUpgradePanel,"SHOP"));

        add(this.mapPanel, BorderLayout.CENTER);
        add(shopUpgradePanel,BorderLayout.EAST);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Aggiorna i componenti del pannello di gioco
        //if(mapPanel.getMapManager() == null){
          //  System.out.println("MapManager null");
        //} else {
            mapPanel.paintComponent(g);
            System.out.println("\n paint gameView");
       // }
        
        //towerPurchaseMenu.render(g);
        //purchasedTowersMenu.render(g);
    }

    /*public TowerPurchaseMenu getTowerPurchaseMenu() {
        return towerPurchaseMenu;
    }

    public PurchasedTowersMenu getPurchasedTowersMenu() {
        return purchasedTowersMenu;
    }
    */

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}
