package btd.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class MapLoaderImpl implements MapLoader {

    @Override
    public int[][] loadMap(String mapName) {
        int[][] ret = new int[MapPanel.col][MapPanel.row];
        try (InputStream input = getClass().getResourceAsStream(mapName);
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(input))) {

            IntStream.range(0, MapPanel.row).forEach(row -> {
                try {
                    String line = buffReader.readLine();
                    String[] numbers = line.split(" ");
                    IntStream.range(0, MapPanel.col).forEach(col -> {
                        int currentNum = Integer.parseInt(numbers[col]);
                        ret[col][row] = currentNum;
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
