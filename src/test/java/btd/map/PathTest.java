package btd.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import btd.model.map.PathImpl;
import btd.utils.Direction;

public class PathTest {
    
    List<Direction> test;

    @BeforeEach
    public void setUp() {
        this.test = new ArrayList<>();
        this.test.add(Direction.UP);
        this.test.add(Direction.DOWN);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.LEFT);
        this.test.add(Direction.DOWN);
        this.test.add(Direction.DOWN);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.RIGHT);
        this.test.add(Direction.LEFT);
        this.test.add(Direction.LEFT);
        this.test.add(Direction.UP);
        this.test.add(Direction.UP);
        this.test.add(Direction.LEFT);
    }

    @Test
    public void testPathImpl1() {

        PathImpl path = new PathImpl("", true);

        assertNotNull(path.getSpawnPosition());
        assertNotNull(path.getDirections());

        List<Direction> directions = path.getDirections();
        assertTrue(directions.size() > 0);

        for (Direction direction : directions) {
            assertNotNull(direction);
        }

        for (int i = 0; i < this.test.size(); i++) {
            assertEquals(this.test.get(i), path.getDirections().get(i));
        }
    }

    @Test
    public void testPathImpl2() {

        PathImpl path = new PathImpl("", true);

        List<Direction> directions = path.getDirections();
    
        for (int i = 0; i < this.test.size(); i++) {
            assertEquals(this.test.get(i), directions.get(i));
        }
    }
}
