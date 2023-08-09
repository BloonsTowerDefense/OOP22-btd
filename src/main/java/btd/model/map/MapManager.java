package btd.model.map;
import java.awt.Graphics2D;

/**
 * This interface defines methods for managing and interacting with the map.
 */
public interface MapManager {

    /**
     * Draws the map.
     *
     * @param graphics2d the Graphics2D context to use for drawing.
     */
    void draw(Graphics2D graphics2d);

    /**
     * Returns the map data as a two-dimensional int array.
     *
     * @return the map data represented as a two-dimensional int array.
     */
    int[][] getMapNum();

    /**
     * Returns the bloon path of the map.
     *
     * @return bloon path represented as a {@link Path} instance.
     */
    Path getBloonPath();

    /**
     * Checks if a tower can be placed at the specified coordinates.
     *
     * @param x x-coordinate of mouse click.
     * @param y y-coordinate of mouse click.
     * @return true if a tower can be placed at the specified coordinates, otherwise returns false.
     */
    Boolean canPlace(int x, int y);

    String getMapName();
}
