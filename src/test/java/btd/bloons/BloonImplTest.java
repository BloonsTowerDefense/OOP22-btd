package btd.bloons;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.BloonType;
import btd.model.map.Path;
import btd.model.map.PathImpl;
import btd.utils.Direction;
import btd.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BloonImplTest {

    private Bloon bloon;
    private Path path;

    @BeforeEach
    public void setUp() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.RIGHT);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);

        // Create a test path
        path = new PathImpl("test", true);
        for(int i=0; i<directions.size(); i++) {
            path.getDirections().add(directions.get(i));
        }

        bloon = new BloonImpl(BloonType.RED_BLOON, path);
        bloon.setPosition(path.getSpawnPosition().getX(), path.getSpawnPosition().getY());
    }

    @Test
    public void testInitialHealth() {
        assertEquals(BloonType.RED_BLOON.getHealth(), bloon.getHealth());
    }

    @Test
    public void testHit() {
        int damage = 5;
        bloon.hit(damage);
        assertEquals(BloonType.RED_BLOON.getHealth() - damage, bloon.getHealth());
    }

    @Test
    public void testMove() {
        Position initialPosition = bloon.getPosition().get();
        long time = 1000; // Elapsed time for the move
        bloon.move(time);
        Position newPosition = bloon.getPosition().get();

        // Verify that the new position is consistent with the expected direction
        Direction currentDirection = path.getDirections().get(bloon.getCurrentPathIndex());
        double expectedX = initialPosition.getX();
        double expectedY = initialPosition.getY();

        switch (currentDirection) {
            case UP:
                expectedY -= BloonType.RED_BLOON.getSpeed();
                break;
            case DOWN:
                expectedY += BloonType.RED_BLOON.getSpeed();
                break;
            case LEFT:
                expectedX -= BloonType.RED_BLOON.getSpeed();
                break;
            case RIGHT:
                expectedX += BloonType.RED_BLOON.getSpeed();
                break;
            default:
                // Do nothing
                break;
        }

        assertEquals(expectedX, newPosition.getX(), 0.01);
        assertEquals(expectedY, newPosition.getY(), 0.01);
    }


    @Test
    public void testUpdate() {
        assertFalse(bloon.isDead());
        bloon.hit((int)bloon.getHealth());
        bloon.update(0);
        assertTrue(bloon.isDead());
    }

    @Test
    public void testGetType() {
        assertEquals(BloonType.RED_BLOON, bloon.getType());
    }

    @Test
    public void testGetCurrentPathIndex() {
        assertEquals(0, bloon.getCurrentPathIndex());
    }

    @Test
    public void testSetHealth() {
        int newHealth = 50;
        bloon.setHealth(newHealth);
        assertEquals(newHealth, bloon.getHealth());
    }
}

