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
    private final TowerUpgradeMenu towerUpgradeMenu;
    private final MapPanel mapPanel;
    private final Game gameEngine;

    private String towerToPlace;

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

        this.towerShopMenu.getBlackAdam().addActionListener(e -> towerToPlace = "blackAdam");
        this.towerShopMenu.getVoldelife().addActionListener(e -> towerToPlace = "voldelife");
        this.towerShopMenu.getDeadColossus().addActionListener(e -> towerToPlace = "deadColossus");
        this.towerShopMenu.getRangeEnhancer().addActionListener(e -> towerToPlace = "rangeEnhancer");
        this.towerShopMenu.getPowerEnhancer().addActionListener(e -> towerToPlace = "powerEnhancer");

        this.mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int spriteX = e.getX();
                int spriteY = e.getY();

                if(mapPanel.getMapManager().canPlace(spriteX,spriteY) && !towerToPlace.isEmpty()){
                    Tower tower;
                    switch (towerToPlace){
                        case "blackAdam" -> tower = new ShootingTower("blackAdam",100,100,new Position(spriteX,spriteY));
                        case "voldelife" -> tower = new ShootingTower("voldelife",100,100,new Position(spriteX,spriteY));
                        case "deadColossus" -> tower = new ShootingTower("deadColossus",100,100,new Position(spriteX,spriteY));
                        case "rangeEnhancer" -> tower = new HelpingTower("rangeEnhancer","Range",new Position(spriteX,spriteY));
                        case "powerEnhancer" -> tower = new HelpingTower("powerEnhancer","Power",new Position(spriteX,spriteY));
                        default -> tower = null;
                    }
                    mapPanel.getGameModel().addTower(tower);
                }
            }
        });

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
