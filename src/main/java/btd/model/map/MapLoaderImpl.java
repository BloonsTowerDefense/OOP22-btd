package btd.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoaderImpl implements MapLoader {

    private MapPanel mapPanel;

    public MapLoaderImpl(final MapPanel mp){
        this.mapPanel = mp;
    }

    @Override
    public int[][] loadMap(String mapName) {
        int ret[][] = new int[this.mapPanel.getCol()][this.mapPanel.getRow()];
        try {
            InputStream input = getClass().getResourceAsStream(mapName); //da rivedere
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));
            int currentCol = 0;
            int currentRow = 0;
            while(currentCol < this.mapPanel.getCol() && currentRow < this.mapPanel.getRow()){
                String read = buffReader.readLine();
                while(currentCol < this.mapPanel.getCol()){
                    String numbers[] = read.split(" ");
                    int currentNum = Integer.parseInt(numbers[currentCol]);
                    ret[currentCol][currentRow] = currentNum;
                    currentCol++;
                }
                if(currentCol == this.mapPanel.getCol()){
                    currentCol = 0;
                    currentRow++;
                }
            }
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
