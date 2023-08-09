package btd.model.map;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import btd.model.GameModel;
import btd.model.entity.*;


import btd.utils.Position;
import btd.model.Game;

import btd.view.ItemType;
import btd.view.Resources;

/**
 * This class rapresents a JPanel that displays the game map and the entity in the map, like
 * bloons, tower and bullets. It extends {@link JPanel}.
 */
public class MapPanel extends JPanel {

    private final static int originalSpriteSize = 16; //16 px originali
    private final static int scale = 3;
    public final static int finalSpritesize = originalSpriteSize * scale; //48px finali
    public final static int col = 25; //25 colonne per avere la lunghezza 1200px
    public final static int row = 15; //15 righe per avere l'altezza 720px
    public static final int screenWidth = finalSpritesize * col;
    public static final int screenHeight = finalSpritesize * row;

    private transient MapManager mapManager;
    private transient Game game;

    /**
     * Standard constructor of MapPanel with the specified Game instance.
     *
     * @param game Game instance.
     */
    public MapPanel(final Game game) {
        this.setPreferredSize(new Dimension(MapPanel.screenWidth, MapPanel.screenHeight));
        this.setDoubleBuffered(true);
        this.game = game;
        this.mapManager = game.getGameModel().getMapManager();
    }

    /**
     * Paints the components on the panel.
     *
     * @param graphics Graphics object to paint element on.
     */
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setColor(Color.black);
        this.mapManager.draw(graphics2d);
        drawBloon(graphics);
        this.game.getGameModel().getTowers().forEach(tower -> graphics.drawImage(tower.getTowerSprite(), (int) tower.getPosition().get().getX(), (int) tower.getPosition().get().getY(), null));
        this.game.getGameModel().towerShoot();
        this.game.getGameModel().towerHelp();
        drawBullet(graphics);
    }

    /**
     * Draws the bullets on the panel.
     *
     * @param graphics Graphics object to paint bullets on.
     */
    private void drawBullet(final Graphics graphics) {
        List<Bullet> bullets = this.game.getGameModel().getBullets();
        if (bullets != null && !bullets.isEmpty()) {
            for (Bullet bullet: bullets) {
                bullet.updatePosition(1,graphics);
            }
        }
    }

    /**
     * Draws the bloons on the panel.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawBloon(final Graphics g) {
        //System.out.println("SONO DRAWBLOON");
        System.out.print(this.game.getGameModel().getAliveBloons());
        this.game.getGameModel().getAliveBloons().forEach(f -> {
            final Position position = f.getPosition().get();
            System.out.println("POSIZIONE: " + position);
            final int x = (int) position.getX();
            final int y = (int) position.getY();
            switch (f.getType().name()) {
                case "RED_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.RED_BLOON), x, y, 48, 48, null);
                    break;
                case "BLUE_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLUE_BLOON), x, y, 48, 48,  null);
                    break;
                case "BLACK_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLACK_BLOON), x, y,  48, 48,  null);
                    break;
                default:
                    break;
            }
        });
    }

    public void update(Graphics graphics) {
        System.out.println("SONO UPDATE");
        /*for (Bloon bloon : bloons) {
            bloon.update(System.currentTimeMillis() - lastUpdateTime);
        }
        lastUpdateTime = System.currentTimeMillis();*/

    }

    /**
     * Returns the {@link MapManager} associated with the panel.
     *
     * @return the MapManager instance.
     */
    public MapManager getMapManager() {
        return this.mapManager;
    }

     /**
     * Sets a new {@link MapManager} for the panel.
     *
     * @param newMapManager the new MapManager instance.
     */
    public void setNewMapManager(final MapManager newMapManager) {
        this.mapManager = newMapManager;
    }

    /**
     * Returns the {@link GameModel} associated with the panel.
     *
     * @return The GameModel instance.
     */
    public GameModel getGameModel() {
        return this.game.getGameModel();
    }
}
