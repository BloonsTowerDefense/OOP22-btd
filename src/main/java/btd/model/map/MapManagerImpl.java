package btd.model.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

/**
 * This class implements the {@link MapManager} interface.
 */
public class MapManagerImpl implements MapManager {

    private List<MapElement> mapElementList;
    private int[][] mapNum;
    private MapLoader mapLoader;
    private Path bloonPath;
    private String mapName;

    /**
     * Standard constructor of MapManagerImpl.
     *
     * @param mapName the name of the map to manage.
     */
    public MapManagerImpl(final String mapName) {
        this.mapName = mapName;
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
    public void draw(final Graphics2D graphics2d) {
        IntStream.range(0, MapPanel.row).forEach(currentRow -> {
            IntStream.range(0, MapPanel.col).forEach(currentCol -> {
                int tileNum = this.mapNum[currentCol][currentRow];
                MapElement mapElement = this.mapElementList.get(tileNum);
                int x = currentCol * MapPanel.finalSpritesize;
                int y = currentRow * MapPanel.finalSpritesize;
                graphics2d.drawImage(mapElement.getImg(), x, y, MapPanel.finalSpritesize, MapPanel.finalSpritesize,
                        null);
            });
        });
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
    public Path getBloonPath() {
        return this.bloonPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean canPlace(final int x, final int y) {
        int newX = x / MapPanel.finalSpritesize;
        int newY = y / MapPanel.finalSpritesize;
        return this.mapNum[newX][newY] == 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMapName() {
        return this.mapName;
    }

    private void loadMapImage() {
        try {
            this.mapElementList.add(new MapElementImpl(ImageIO.read(getClass().getResourceAsStream("/mapSprite/sand.png"))));
            this.mapElementList.add(new MapElementImpl(ImageIO.read(getClass().getResourceAsStream("/mapSprite/tree.png"))));
            this.mapElementList.add(new MapElementImpl(ImageIO.read(getClass().getResourceAsStream("/mapSprite/wall.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMap(final String mapName) {
        String src = "/map/" + mapName + "/" + mapName + ".txt";
        this.mapNum = this.mapLoader.loadMap(src);
        this.bloonPath = new PathImpl(mapName, false);
    }
}
