package btd.model.map;

import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.MouseAdapter;
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

import java.util.ArrayList;

public class MapPanel extends JPanel implements Runnable{
    
    private final int originalSpriteSize = 16; //16 px originali
    private final int scale = 3;
    private final int finalSpritesize = this.originalSpriteSize * this.scale; //48px finali
    private final int col = 25; //25 colonne per avere la lunghezza 1200px
    private final int row = 15; //15 righe per avere l'altezza 720px
    private final int screenWidth = this.finalSpritesize * this.col;
    private final int screenHeight = this.finalSpritesize * this.row;

    private MapManager mapManager;
    private Thread gameThread;
    private List<Bloon> bloons;
    private List<Tower> towers;
    private long lastUpdateTime;
    private Game game;
    private LevelImpl level;

    public MapPanel(){
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.game = game;
        this.mapManager = new MapManagerImpl(this);
        lastUpdateTime = System.currentTimeMillis();
        // Add a mouse listener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);;
                ShootingTower tower = new ShootingTower("Tower", 100, 100, new Position(e.getX(), e.getY()));
                towers.add(tower);
            }
        });
        this.level = new LevelImpl("facile"); // normale
        this.bloons = new ArrayList<>(); // Initialize the bloons list
    }
    
    public void startGameThread(){
        this.gameThread = new Thread(this);
        Wave initialWave = level.getWave();
        System.out.println(initialWave);
        if (initialWave != null) {
            new Thread(() -> {
                for (Bloon bloon : initialWave.getBloons()) {
                    try {
                        Thread.sleep(600); // 1 second delay
                        bloons.add(bloon);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/4; //60
        double nextDraw = System.nanoTime() + interval;
        while(gameThread != null){
            //System.out.println("Is running");
            update();
            repaint();
            try {
                double rest = nextDraw - System.nanoTime();
                rest = ((rest/1000000) > 0 ? rest/1000000 : 0);
                Thread.sleep((long) rest);
                nextDraw += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;
        graphics2d.setColor(Color.black);
        this.mapManager.draw(graphics2d);
        for (Bloon bloon : bloons) {
            bloon.draw(graphics2d);
        }
        //graphics2d.fillRect(100, 100, this.finalSpritesize, this.finalSpritesize);
        graphics2d.dispose();
    }

    public void update(){
        for (Bloon bloon : bloons) {
            bloon.update(System.currentTimeMillis() - lastUpdateTime);
        }
        lastUpdateTime = System.currentTimeMillis();
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

}
