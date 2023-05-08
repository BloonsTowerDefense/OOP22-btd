package btd.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import btd.utils.Direction;
import btd.utils.Position;

public class PathImpl implements Path{
    
    private List<Direction> path;
    private Position spawnPosition;
	private String source;

    public PathImpl(final String source){
        this.path = new ArrayList<>();
		this.source = source;
        loadPath(this.source);
    }

	@Override
	public List<Direction> getDirections() {
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

	private void loadPath(final String source){
		try {
			InputStream input = getClass().getResourceAsStream(source);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String read = br.readLine();
			String spawnPosition[] = read.split(" ");
			this.spawnPosition.set(Double.parseDouble(spawnPosition[0]), Double.parseDouble(spawnPosition[1]));
			int step = Integer.parseInt(spawnPosition[2]);
			read = br.readLine();
			String path[] = read.split(" ");
			for(int i = 0; i < step; i++){
				this.path.add(decodeDirection(Integer.parseInt(path[i])));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Direction decodeDirection(int in){
		switch(in){
			case 1: return Direction.UP;
			case 2: return Direction.DOWN;
			case 3: return Direction.RIGHT;
			case 4: return Direction.LEFT;
		}
		return null; /*Da rivedere */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public int getTileSize() {
		return 48;
	}
    
}
