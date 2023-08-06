package btd.model.map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.List;
import btd.model.entity.Bloon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import btd.model.entity.Tower;
import btd.model.entity.ShootingTower;
import btd.utils.Position;
import btd.model.Game;

import btd.model.LevelImpl;
import btd.model.Wave;
import btd.view.BloonViewImpl;
import btd.view.ItemType;
import btd.view.Resources;

import java.util.ArrayList;

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
        // Add a mouse listener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);;
                //ShootingTower tower = new ShootingTower("Tower", 100, 100, new Position(e.getX(), e.getY()));
                //towers.add(tower);
            }
        });

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
                    g.drawImage(Resources.getRes().getTextures(ItemType.RED_BLOON), x, y, null);
                    break;
                case "BLUE_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLUE_BLOON), x, y, null);
                    break;
                case "BLACK_BLOON":
                    g.drawImage(Resources.getRes().getTextures(ItemType.BLACK_BLOON), x, y, null);
                    break;
                default:
                    break;
            }
        });
    }

    public void update(){
        System.out.println("SONO UPDATE");
        /*for (Bloon bloon : bloons) {
            bloon.update(System.currentTimeMillis() - lastUpdateTime);
        }
        lastUpdateTime = System.currentTimeMillis();*/
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

}
