package btd.model.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * This class implements the {@link MapManager} interface.
 */
public class MapManagerImpl implements MapManager{

    private List<MapElement> mapElementList;
    private int[][] mapNum;
    private MapLoader mapLoader;
    private Path bloonPath;

    /**
     * Standard constructor of MapManagerImpl.
     *
     * @param mapName the name of the map to manage.
     */
    public MapManagerImpl(String mapName){
        this.mapElementList = new ArrayList<>();
        this.mapNum = new int[MapPanel.col][MapPanel.row];
        this.mapLoader = new MapLoaderImpl();
        loadMapImage();
        setMap(mapName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D graphics2d) {
        int currentCol = 0;
        int currentRow = 0;
        int x = 0;
        int y = 0;
        while(currentCol < MapPanel.col && currentRow < MapPanel.row){
            int tileNum = this.mapNum[currentCol][currentRow];
            graphics2d.drawImage(this.mapElementList.get(tileNum).getImg(), x, y, MapPanel.finalSpritesize,  MapPanel.finalSpritesize, null);
            currentCol++;
            x +=  MapPanel.finalSpritesize;
            if(currentCol == MapPanel.col){
                currentCol = 0;
                x = 0;
                currentRow++;
                y +=  MapPanel.finalSpritesize;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[][] getMapNum() {
        return this.mapNum;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Path getBloonPath(){
        return this.bloonPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean canPlace(int x, int y){
        int newX = x/48;
        int newY = y/48;
        return this.mapNum[newX][newY] == 2;
    }

    private void loadMapImage(){
        try {
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(0).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/sand.png")));
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(1).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/tree.png")));
            this.mapElementList.add(new MapElementImpl());
            this.mapElementList.get(2).setImg(ImageIO.read(getClass().getResourceAsStream("/mapSprite/wall.png")));
            this.mapElementList.get(2).setCanPlaceTower(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMap(String mapName){
        String src = "/map/" + mapName + "/" + mapName + ".txt";
        this.mapNum = this.mapLoader.loadMap(src);
        this.bloonPath = new PathImpl(mapName);
    }
    
}
