package btd.view;

import btd.model.Game;
import btd.model.map.MapPanel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    //private final TowerPurchaseMenu towerPurchaseMenu;
    //private final PurchasedTowersMenu purchasedTowersMenu;
    private final MapPanel mapPanel;
    private final Game gameEngine;

    public GameView(final Game gameEngine) {
        this.gameEngine = gameEngine;
        //System.out.println("\n\nCostruttore GameView");
        //System.out.print("\n GameView");
        //this.towerPurchaseMenu = new TowerPurchaseMenu();
        //this.purchasedTowersMenu = new PurchasedTowersMenu();
        System.out.println("\n\n\nORA CREO MAPPANEL\n\n\n");
        this.mapPanel = new MapPanel(this.gameEngine);


        //add(this.towerPurchaseMenu, BorderLayout.EAST);
        //add(this.purchasedTowersMenu, BorderLayout.WEST);
        add(this.mapPanel, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Aggiorna i componenti del pannello di gioco
        //if(mapPanel.getMapManager() == null){
          //  System.out.println("MapManager null");
        //} else {
            mapPanel.paintComponent(g);
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
