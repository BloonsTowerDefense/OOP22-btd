package btd.model.map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import btd.model.GameModel;
import btd.model.entity.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import btd.utils.Position;
import btd.model.Game;

import btd.model.LevelImpl;
import btd.model.Wave;
import btd.view.BloonViewImpl;
import btd.view.ItemType;
import btd.view.Resources;

import java.util.ArrayList;
import java.util.Objects;

public class MapPanel extends JPanel {

    private final static int originalSpriteSize = 16; //16 px originali
    private final static int scale = 3;
    public final static int finalSpritesize = originalSpriteSize * scale; //48px finali
    public final static int col = 25; //25 colonne per avere la lunghezza 1200px
    public final static int row = 15; //15 righe per avere l'altezza 720px
    private final int screenWidth = this.finalSpritesize * this.col;
    private final int screenHeight = this.finalSpritesize * this.row;

    private MapManager mapManager;
    private Thread gameThread;
    //private List<Bloon> bloons;
    //private List<Tower> towers;
    private long lastUpdateTime;
    private Game game;
    private LevelImpl level;
    private BloonViewImpl bloonView;

    public MapPanel(Game game) {
        this.setPreferredSize(new Dimension(1200, 720));
        this.setDoubleBuffered(true);
        this.game = game;
        this.mapManager = game.getGameModel().getMapManager();
        if(this.mapManager == null){
            System.out.println("Dentro costruttore di MapPanel il mapManager è null");
        }
        lastUpdateTime = System.currentTimeMillis();
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setColor(Color.black);
        if(this.mapManager == null){
            System.out.println("MapManager è null");
            System.exit(0);
        } //else {
            this.mapManager.draw(graphics2d);
        //}
        drawBloon(graphics);
        //Subtract the X=16 width of the wall sprite and the Y=50 height of the tower sprite
        this.game.getGameModel().getTowers().forEach(tower -> graphics.drawImage(tower.getTowerSprite(), (int) tower.getPosition().get().getX(), (int) tower.getPosition().get().getY(), null));
        update(graphics);
        System.out.println("\n paint mapPanel");
    }

    private void drawBloon(Graphics g) {
        //System.out.println("SONO DRAWBLOON");
        System.out.print(this.game.getGameModel().getAliveBloons());
        this.game.getGameModel().getAliveBloons().forEach(f -> {
            final Position position = f.getPosition().get();
            System.out.println("POSIZIONE: " + position);
            final int x = (int) position.getX();
            final int y = (int) position.getY();
            switch (f.getType().name()){
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

        for (Tower tower : this.game.getGameModel().getTowers()) {
            if (tower instanceof ShootingTower shootingTower) {
                List<Bloon> bloonsInRange = findBloonsInRange(shootingTower);

                // Hit Bloon with the greatest currentPathIndex
                if (!bloonsInRange.isEmpty()) {
                    Bloon targetBloon = findTargetBloon(bloonsInRange);
                    System.out.println("TOWER POSITION: "+tower.getPosition().get().getX());
                    System.out.println("BLOON POSITION :"+targetBloon.getPosition().get().getX()+" BLOON HEALTH :"+targetBloon.getHealth());
                    BufferedImage bulletImage = null;
                    try {
                        bulletImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/towers/bullet.png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Bullet bullet = new Bullet(tower.getPosition().get(),bulletImage);
                    bullet.setTargetPosition(targetBloon.getPosition().get());
                    bullet.updatePosition(1,graphics);
                    targetBloon.hit(((ShootingTower) tower).getPower());
                    System.out.println("BLOON POSITION :"+targetBloon.getPosition().get().getX()+"BLOON HEALTH AFTER HIT :"+targetBloon.getHealth());
                }
            }
        }
    }
    private List<Bloon> findBloonsInRange(ShootingTower tower) {
        List<Bloon> bloonsInRange = new ArrayList<>();
        for (Bloon bloon : this.game.getGameModel().getAliveBloons()) {
            if(bloonInRange(bloon,tower)){
                bloonsInRange.add(bloon);
            }
        }
        return bloonsInRange;
    }

    private boolean bloonInRange(Bloon bloon,ShootingTower shootingTower){
        return shootingTower.getPosition().get().getX() - bloon.getPosition().get().getX() <= shootingTower.getHittingRange().getX()
                && shootingTower.getPosition().get().getY() - bloon.getPosition().get().getY() <= shootingTower.getHittingRange().getY();
    }

    private Bloon findTargetBloon(List<Bloon> bloons) {
        Bloon targetBloon = null;
        int maxCurrentPathIndex = -1;

        // Find bloon with the greatest currentPathIndex
        for (Bloon bloon : bloons) {
            if (bloon.getCurrentPathIndex() > maxCurrentPathIndex) {
                maxCurrentPathIndex = bloon.getCurrentPathIndex();
                targetBloon = bloon;
            }
        }

        return targetBloon;
    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }

    public int getFinalSpriteSize(){
        return this.finalSpritesize;
    }

    public MapManager getMapManager(){
        return this.mapManager;
    }

    public void setNewMapManager(MapManager newMapManager){
        this.mapManager = newMapManager;
    }

    public GameModel getGameModel(){
        return this.game.getGameModel();
    }
}
