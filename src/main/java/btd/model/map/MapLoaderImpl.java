package btd.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoaderImpl implements MapLoader {

    public MapLoaderImpl(){
        System.out.println("Costruttore MAPLOADERIMPL");
    }

    @Override
    public int[][] loadMap(String mapName) {
        int ret[][] = new int[MapPanel.col][MapPanel.row];
        try {
            System.out.println("SONO LOADMAP: " + mapName);
            InputStream input = getClass().getResourceAsStream(mapName); //da rivedere
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
            System.out.println("MapLoader ho finito di leggere la mappa");
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
}
