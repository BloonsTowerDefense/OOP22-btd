package btd.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import btd.utils.Direction;
import btd.utils.Position;

/**
 * This class implements the {@link Path} interface.
 */
public class PathImpl implements Path {

    private static final Logger LOGGER = Logger.getLogger(PathImpl.class.getName());
    private  final List<Direction> path;
    private Position spawnPosition;
    //private String source;

    /**
     * Standard constructor of this class.
     *
     * @param source source from which to load path information.
     * @param test used to set test mode on, in this way it loads a standard file.
     */
    public PathImpl(final String source, final Boolean test) {
        this.path = new ArrayList<>();
        //this.source = source;
        loadPath(source, test);
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
        return new Position(this.spawnPosition.getX(), this.spawnPosition.getY());
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
        return MapPanel.FINAL_SPRITE_SIZE;
    }

    private void loadPath(final String source, final Boolean test) {
        String realSource;
        if (test) {
            realSource = "/testResources/bloonsPath.txt";
        } else {
            realSource = "/map/" + source + "/bloonsPath.txt";
        }
        try (InputStream input = PathImpl.class.getResourceAsStream(realSource);
                BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            if (input != null) {
                String read = br.readLine();
                if (read != null) {
                    final String[] spawnPosition = read.split(" ");
                    this.spawnPosition = new Position(Double.parseDouble(spawnPosition[0]),
                            Double.parseDouble(spawnPosition[1]));
                    final int step = Integer.parseInt(spawnPosition[2]);
                    read = br.readLine();
                    if (read != null) {
                        final String[] path = read.split(" ");
                        for (int i = 0; i < step; i++) {
                            this.path.add(decodeDirection(Integer.parseInt(path[i])));
                        }
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException", e);
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
