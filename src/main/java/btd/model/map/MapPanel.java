package btd.model.map;

import javax.swing.JPanel;
import java.awt.*;

public class MapPanel extends JPanel {
    
    private final int originalSpriteSize = 16; //16 px originali
    private final int scale = 3;
    private final int finalSpritesize = this.originalSpriteSize * this.scale; //48px finali
    private final int col = 25; //25 colonne per avere la lunghezza 1200px
    private final int row = 20; //20 righe per avere la lunghezza 960px
    private final int screenWidth = this.finalSpritesize * this.col;
    private final int screenHeight = this.finalSpritesize * this.row;

    private MapManager mapManager;

    public MapPanel(){
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setDoubleBuffered(true);
        this.mapManager = new MapManagerImpl(this);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;
        this.mapManager.draw();
        graphics2d.dispose();
    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }
}
