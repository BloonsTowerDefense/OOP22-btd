package btd.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import btd.utils.Direction;
import btd.utils.Position;

/**
 * This class implements the {@link Path} interface.
 */
public class PathImpl implements Path {
    
    private List<Direction> path;
    private Position spawnPosition;
    private String source;

	/**
     * Standard constructor of this class.
     *
     * @param source source from which to load path information.
     */
    public PathImpl(final String source, Boolean test) {
        this.path = new ArrayList<>();
		this.source = source;
        loadPath(this.source, test);
		//System.out.println("Ho letto il seguente path: " + this.path.toString());
    }

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Direction> getDirections() {
		//return this.path;
		return new ArrayList<>(this.path);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Position getSpawnPosition() {
		return this.spawnPosition;
		//return new Position(this.spawnPosition.getX(), this.spawnPosition.getX());
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public int getPathDistance() {
		return this.path.size();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public int getTileSize() {
		return MapPanel.finalSpritesize;
	}

	private void loadPath(final String source, final Boolean test) {
        try {
            String realSource;
            if (test) {
                realSource = "/testResources/bloonsPath.txt";
            } else {
                realSource = "/map/" + source + "/bloonsPath.txt";
            }
            InputStream input = getClass().getResourceAsStream(realSource);
            if (input != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(input,  "UTF-8"));
                String read = br.readLine();
                if (read != null) {
                    String[] spawnPosition = read.split(" ");
                    this.spawnPosition = new Position(Double.parseDouble(spawnPosition[0]),
                            Double.parseDouble(spawnPosition[1]));
                    int step = Integer.parseInt(spawnPosition[2]);
                    read = br.readLine();
                    if (read != null) {
                        String[] path = read.split(" ");
                        for (int i = 0; i < step; i++) {
                            this.path.add(decodeDirection(Integer.parseInt(path[i])));
                        }
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private Direction decodeDirection(final int in) {
		switch (in) {
			case 1: return Direction.UP;
			case 2: return Direction.DOWN;
			case 3: return Direction.RIGHT;
			case 4: return Direction.LEFT;
			default: return null;
		}
	}
}
