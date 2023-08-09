package btd.model.map;

/**
 * This interface defines methods for loading map data from a file.
 */
public interface MapLoader {

    /**
     * Loads map data from a specified String rappresenting map name and returns it 
     * as a two-dimensional int array.
     *
     * @param mapName the name of the map to be loaded.
     * @return a two-dimensional int array representing the loaded map data.
     */
    int[][] loadMap(String mapName);
}
