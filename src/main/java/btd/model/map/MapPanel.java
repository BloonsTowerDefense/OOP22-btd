package btd.model.map;

import javax.swing.JPanel;
import java.awt.*;

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

    public MapPanel(){
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.mapManager = new MapManagerImpl(this);
    }
    
    public void startGameThread(){
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/60;
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
        //graphics2d.fillRect(100, 100, this.finalSpritesize, this.finalSpritesize);
        graphics2d.dispose();
    }

    public void update(){

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
