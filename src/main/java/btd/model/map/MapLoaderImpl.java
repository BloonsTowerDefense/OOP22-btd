package btd.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class implements the {@link MapLoader} interface.
 * It provides functionality to load map data from a file.
 */
public class MapLoaderImpl implements MapLoader {

    /**
     * Standard constructor.
     */
    public MapLoaderImpl(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[][] loadMap(String mapName) {
        int ret[][] = new int[MapPanel.col][MapPanel.row];
        try {
            InputStream input = getClass().getResourceAsStream(mapName);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));
            int currentCol = 0;
            int currentRow = 0;
            while(currentCol < MapPanel.col && currentRow < MapPanel.row){
                String read = buffReader.readLine();
                while(currentCol < MapPanel.col){
                    String numbers[] = read.split(" ");
                    int currentNum = Integer.parseInt(numbers[currentCol]);
                    ret[currentCol][currentRow] = currentNum;
                    currentCol++;
                }
                if(currentCol == MapPanel.col){
                    currentCol = 0;
                    currentRow++;
                }
            }
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
