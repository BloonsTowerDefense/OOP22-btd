package btd.model.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoaderImpl implements MapLoader {

    @Override
    public int[][] loadMap(String mapName) {
        try {
            InputStream in = getClass().getResourceAsStream(mapName); //da rivedere
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            /*
             * Implemetare quando saprò la dimensione della mappa
             */
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
