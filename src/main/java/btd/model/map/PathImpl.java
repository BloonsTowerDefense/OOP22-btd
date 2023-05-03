package btd.model.map;

import java.util.List;

import btd.utils.Direction;
import btd.utils.Position;

public class PathImpl implements Path{
    
    private List<Direction> path;
    private Position spawnPosition;

    public PathImpl(final List<Direction> path, final Position spawnPosition){
        this.path = path;
        this.spawnPosition = spawnPosition;
    }

	@Override
	public List<Direction> getPath() {
		return this.path;
	}

	@Override
	public Position getSpawnPosition() {
		return this.spawnPosition;
	}

	@Override
	public int getPathDistance() {
		return this.path.size();
	}
    
}
