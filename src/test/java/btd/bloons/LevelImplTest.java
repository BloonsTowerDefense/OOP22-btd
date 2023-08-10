package btd.bloons;

import btd.model.LevelImpl;
import btd.model.Wave;
import btd.model.map.Path;
import btd.model.map.PathImpl;
import btd.utils.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelImplTest {

    private LevelImpl level;
    private Path path;

    @BeforeEach
    public void setUp() {
        // Create a test path
        path = new PathImpl("test", true);
        path.getDirections().add(Direction.RIGHT);
        path.getDirections().add(Direction.DOWN);
        path.getDirections().add(Direction.LEFT);
        // Initialize the level
        level = new LevelImpl("facile", path);
    }

    @Test
    public void testGenerateWaves() {
        for (int waveNum = 1; waveNum <= 5; waveNum++) {
            System.out.println("Wave " + waveNum);
            Wave wave = level.getWave();
            assertNotNull(wave);

            // Kill the bloons
            for (var bloon : wave.getBloons()) {
                bloon.hit((int)bloon.getHealth());
                assertTrue(bloon.isDead());
            }

            // Finish the wave
            level.waveFinished();
        }
    }
}


