package btd.model.map;

import java.util.List;

import btd.utils.Direction;
import btd.utils.Position;

public interface Path {

    public List<Direction> getPath();

    public Position getSpawnPosition();

    public int getPathDistance();

    public int getTileSize();
}
