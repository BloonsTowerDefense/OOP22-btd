package btd.model.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import btd.model.entity.Tower;
import btd.utils.Position;

public class MapManagerImpl implements MapManager{

    private List<MapElement> mapElementList;
    private int[][] mapNum;
    private MapLoader mapLoader;
    private Path bloonPath;
    private Map<Position, Boolean> currentTower;
    //private String mapName;

    public MapManagerImpl(String mapName){
        System.out.println("Sto creando un MapManagerImpl con il seguente mapName: " + mapName);
        this.currentTower = new HashMap<>();
        //this.mapName = mapName;
        this.mapElementList = new ArrayList<>();
        //this.mapPanel = mp;
        this.mapNum = new int[MapPanel.col][MapPanel.row];
        this.mapLoader = new MapLoaderImpl();
        loadMapImage();
        setMap(mapName);
    }

    private void loadMapImage(){
        try {
            //System.out.println("Cerco di caricare le immagini");
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
        System.out.println("APRO LA MAPPA: " + src);
        //this.mapNum = this.mapLoader.loadMap("/map/map01/map01.txt"); //il nome della mappa sarà preso dal game manager
        this.mapNum = this.mapLoader.loadMap(src);
        this.bloonPath = new PathImpl(mapName);
    }

    @Override
    public void draw(Graphics2D graphics2d) {
        int currentCol = 0;
        int currentRow = 0;
        int x = 0;
        int y = 0;
        while(currentCol < MapPanel.col && currentRow < MapPanel.row){
            int tileNum = this.mapNum[currentCol][currentRow];
            //int paintX = currentCol * this.mapPanel.getFinalSpriteSize();
            //int paintY = currentCol * this.mapPanel.getFinalSpriteSize();
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
        //graphics2d.drawImage(this.mapElementList.get(0).getImg(), 0, 0, mapPanel.getFinalSpriteSize(), mapPanel.getFinalSpriteSize(), null);
    }

    @Override
    public int[][] getMapNum() {
        return this.mapNum;
    }

    public Path getBloonPath(){
        return this.bloonPath;
    }

    public String getMapName(){
        //return this.mapName;
        return "";
    }

    public Boolean canPlace(int x, int y){
        int newX = x/48;
        int newY = y/48;
        
        return this.mapNum[newX][newY] == 2;
    }
    
}
