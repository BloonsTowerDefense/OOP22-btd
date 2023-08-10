package btd.model.map;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import btd.model.GameModel;
import btd.model.entity.Bullet;
import btd.utils.Position;
import btd.model.Game;

import btd.view.ItemType;
import btd.view.Resources;

/**
 * This class rapresents a JPanel that displays the game map and the entity in the map, like
 * bloons, tower and bullets. It extends {@link JPanel}.
 */
public class MapPanel extends JPanel {

    /**
     * The original dimension of map's sprite.
     */
    private static final int ORIGINAL_SPRITE_SIZE = 16;
    /**
     * Scale to multiply map's sprite.
     */
    private static final int SCALE = 3;
    /**
     * The final dimension for map's sprite.
     */
    public static final int FINAL_SPRITE_SIZE = ORIGINAL_SPRITE_SIZE * SCALE;
    /**
     * Number of column of the map.
     */
    public static final int GAME_COL = 25;
    /**
     * Number of row of the map.
     */
    public static final int GAME_ROW = 15;
    /**
     * Final dimension of screen width, 1200px.
     */
    public static final int SCREEN_WIDTH = FINAL_SPRITE_SIZE * GAME_COL;
    /**
     * Final dimension of screen height, 720px.
     */
    public static final int SCREEN_HEIGHT = FINAL_SPRITE_SIZE * GAME_ROW;

    private transient MapManager mapManager;
    private transient Game game;

    /**
     * Standard constructor of MapPanel with the specified Game instance.
     *
     * @param game Game instance.
     */
    public MapPanel(final Game game) {
        this.setPreferredSize(new Dimension(MapPanel.SCREEN_WIDTH, MapPanel.SCREEN_HEIGHT));
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
        Graphics2D graphics2d = (Graphics2D) graphics.create();
        graphics2d.setColor(Color.black);
        this.mapManager.draw(graphics2d);
        drawBloon(graphics);
        this.game.getGameModel().getTowers().forEach(tower -> graphics.drawImage(tower.getTowerSprite(),
                (int) tower.getPosition().get().getX(), (int) tower.getPosition().get().getY(), null));
        this.game.getGameModel().towerShoot();
        this.game.getGameModel().towerHelp();
        this.game.getView().getGameView().setPlayerMoney(String.valueOf(this.game.getGameModel().getPlayer().getCoins()));
        this.game.getView().getGameView().setPlayerLife(String.valueOf(this.game.getGameModel().getPlayer().getHealth()));
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
            for (Bullet bullet : bullets) {
                bullet.updatePosition(1, graphics);
            }
        }
    }

    /**
     * Draws the bloons on the panel.
     *
     * @param g The Graphics object to draw on.
     */
    private void drawBloon(final Graphics g) {
        // System.out.println("SONO DRAWBLOON");
        System.out.print(this.game.getGameModel().getAliveBloons());
        this.game.getGameModel().getAliveBloons().forEach(f -> {
            final Position position = f.getPosition().get();
            System.out.println("POSIZIONE: " + position);
            final int x = (int) position.getX();
            final int y = (int) position.getY();
            switch (f.getType().name()) {
                case "RED_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.RED_BLOON), x, y, MapPanel.FINAL_SPRITE_SIZE,
                            MapPanel.FINAL_SPRITE_SIZE, null);
                    break;
                case "BLUE_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLUE_BLOON), x, y, MapPanel.FINAL_SPRITE_SIZE,
                            MapPanel.FINAL_SPRITE_SIZE, null);
                    break;
                case "BLACK_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLACK_BLOON), x, y, MapPanel.FINAL_SPRITE_SIZE,
                            MapPanel.FINAL_SPRITE_SIZE, null);
                    break;
                default:
                    break;
            }
        });
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
