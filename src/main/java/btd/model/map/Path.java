package btd.model.map;

import java.util.List;

import btd.utils.Direction;
import btd.utils.Position;

/**
 * This interface represents a path that every bloon follows in the game.
 */
public interface Path {

    /**
     * Returns a list of directions that bloons follow on the map.
     *
     * @return a list of directions {@link Direction}.
     */
    List<Direction> getDirections();

    /**
     * Returns the spawn position of bloons at the beginning of the path.
     *
     * @return spawn position as a {@link Position}.
     */
    Position getSpawnPosition();

    /**
     * Returns the distance of the path as the number of steps.
     *
     * @return distance of the path.
     */
    int getPathDistance();

    /**
     * Returns the size of each tile.
     *
     * @return the size of each tile.
     */
    int getTileSize();
}
