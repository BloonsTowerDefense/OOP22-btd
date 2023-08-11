package btd.model.map;

import btd.view.MapPanel;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

/**
 * This class implements the {@link MapManager} interface.
 */
public class MapManagerImpl implements MapManager {
    private static final Logger LOGGER = Logger.getLogger(MapManagerImpl.class.getName());
    private final List<MapElement> mapElementList;
    private int[][] mapNum;
    private final MapLoader mapLoader;
    private Path bloonPath;
    private final String mapName;

    /**
     * Standard constructor of MapManagerImpl.
     *
     * @param mapName the name of the map to manage.
     */
    public MapManagerImpl(final String mapName) {
        this.mapName = mapName;
        this.mapElementList = new ArrayList<>();
        this.mapNum = new int[MapPanel.GAME_COL][MapPanel.GAME_ROW];
        this.mapLoader = new MapLoaderImpl();
        loadMapImage();
        setMap(mapName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics2D graphics2d) {
        IntStream.range(0, MapPanel.GAME_ROW).forEach(currentRow -> {
            IntStream.range(0, MapPanel.GAME_COL).forEach(currentCol -> {
                final int tileNum = this.mapNum[currentCol][currentRow];
                final MapElement mapElement = this.mapElementList.get(tileNum);
                final int x = currentCol * MapPanel.FINAL_SPRITE_SIZE;
                final int y = currentRow * MapPanel.FINAL_SPRITE_SIZE;
                graphics2d.drawImage(mapElement.getImg(), x, y, MapPanel.FINAL_SPRITE_SIZE, MapPanel.FINAL_SPRITE_SIZE,
                        null);
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[][] getMapNum() {
        //return this.mapNum;
        final int rows = mapNum.length;
        final int cols = mapNum[0].length;
        final int[][] copy = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
        System.arraycopy(mapNum[i], 0, copy[i], 0, cols);
        }
        return copy;
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
        final int newX = x / MapPanel.FINAL_SPRITE_SIZE;
        final int newY = y / MapPanel.FINAL_SPRITE_SIZE;
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
            this.mapElementList.add(
                    new MapElementImpl(ImageIO.read(MapManagerImpl.class.getResourceAsStream("/mapSprite/sand.png"))));
            this.mapElementList.add(
                    new MapElementImpl(ImageIO.read(MapManagerImpl.class.getResourceAsStream("/mapSprite/tree.png"))));
            this.mapElementList.add(
                    new MapElementImpl(ImageIO.read(MapManagerImpl.class.getResourceAsStream("/mapSprite/wall.png"))));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException", e);
        }
    }

    private void setMap(final String mapName) {
        final String src = "/map/" + mapName + "/" + mapName + ".txt";
        this.mapNum = this.mapLoader.loadMap(src);
        this.bloonPath = new PathImpl(mapName, false);
    }
}
