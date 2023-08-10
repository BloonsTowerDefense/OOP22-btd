package btd.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * This class implements {@link MapLoader} interface.
 */
public class MapLoaderImpl implements MapLoader { 

    private static final Logger LOGGER = Logger.getLogger(MapLoaderImpl.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    public final int[][] loadMap(final String mapName) {
        int[][] ret = new int[MapPanel.GAME_COL][MapPanel.GAME_ROW];
        try (InputStream input = MapLoaderImpl.class.getResourceAsStream(mapName)) {
            if (input == null) {
                throw new IllegalArgumentException("Map file not found: " + mapName);
            }
            try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                IntStream.range(0, MapPanel.GAME_ROW).forEach(row -> {
                    try {
                        final String line = buffReader.readLine();
                        if (line == null) {
                            throw new IOException("Line is null");
                        }
                        final String[] numbers = line.split(" ");
                        if (numbers.length != MapPanel.GAME_COL) {
                            throw new IllegalArgumentException("Incorrect number of values in row: " + line);
                        }
                        IntStream.range(0, MapPanel.GAME_COL).forEach(col -> {
                            final int currentNum = Integer.parseInt(numbers[col]);
                            ret[col][row] = currentNum;
                        });
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Exception", e);
                    }
                });
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception", e);
        }
        return ret;
    }
}
