package btd.model.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class MapManagerImpl implements MapManager{

    private List<MapElement> mapElementList;
    private int[][] mapNum;
    private MapLoader mapLoader;
    private MapPanel mapPanel;

    public MapManagerImpl(final MapPanel mp){
        this.mapElementList = new ArrayList<>();
        this.mapPanel = mp;
        this.mapNum = new int[this.mapPanel.getCol()][this.mapPanel.getRow()];
        this.mapLoader = new MapLoaderImpl(this.mapPanel);
        loadMapImage();
        setMap();
    }

    private void loadMapImage(){
        try {
            System.out.println("Cerco di caricare le immagini");
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(0).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/blue.png")));
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(1).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/green.png")));
            this.mapElementList.get(1).setCanPlaceTower(true);
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(2).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/red.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMap(){
        this.mapNum = this.mapLoader.loadMap("/map/map01/map01.txt"); //il nome della mappa sarà preso dal game manager
    }

    @Override
    public void draw(Graphics2D graphics2d) {
        int currentCol = 0;
        int currentRow = 0;
        int x = 0;
        int y = 0;
        while(currentCol < this.mapPanel.getCol() && currentRow < this.mapPanel.getRow()){
            int tileNum = this.mapNum[currentCol][currentRow];
            //int paintX = currentCol * this.mapPanel.getFinalSpriteSize();
            //int paintY = currentCol * this.mapPanel.getFinalSpriteSize();
            graphics2d.drawImage(this.mapElementList.get(tileNum).getImg(), x, y, this.mapPanel.getFinalSpriteSize(), this.mapPanel.getFinalSpriteSize(), null);
            currentCol++;
            x += this.mapPanel.getFinalSpriteSize();
            if(currentCol == this.mapPanel.getCol()){
                currentCol = 0;
                x = 0;
                currentRow++;
                y += this.mapPanel.getFinalSpriteSize();
            }
        }
        //graphics2d.drawImage(this.mapElementList.get(0).getImg(), 0, 0, mapPanel.getFinalSpriteSize(), mapPanel.getFinalSpriteSize(), null);
    }

    @Override
    public int[][] getMapNum() {
        return this.mapNum;
    }
    
}
