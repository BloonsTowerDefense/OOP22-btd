package btd.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import btd.model.map.MapLoaderImpl;
import btd.model.map.MapPanel;

public class MapLoaderTest {
    
    private int[][] expectedMap = new int[MapPanel.GAME_COL][MapPanel.GAME_ROW];
    
    @BeforeEach
    public void setUp() {
        expectedMap[1][1] = 1;
        for(int i = 0; i < MapPanel.GAME_COL; i++){
            for(int j = 0; j < MapPanel.GAME_ROW; j++){
                expectedMap[i][j] = 1;
            }
        }
    }

    @Test
    public void testLoadMap() {
        MapLoaderImpl mapLoader = new MapLoaderImpl();
        int[][] actualMap = mapLoader.loadMap("/testResources/mapTest01.txt");

        assertNotNull(actualMap);
        assertEquals(expectedMap.length, actualMap.length);
        for (int i = 0; i < expectedMap.length; i++) {
            assertArrayEquals(expectedMap[i], actualMap[i]);
        }
        //assertEquals(expectedMap[1][1], actualMap[1][1]);
    }
}
