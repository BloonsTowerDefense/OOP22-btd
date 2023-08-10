package btd.towers;

import static org.junit.jupiter.api.Assertions.*;

import btd.model.entity.HelpingTower;
import btd.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelpingTowerTest {

    private HelpingTower helpingTower;

    @BeforeEach
    public void setUp() {
        helpingTower = new HelpingTower("rangeEnhancer", "Range", 100, new Position(100, 100));
    }

    @Test
    public void testUpdate() {
        Position initialRange = helpingTower.getHittingRange();
        Integer initialFunctionFactor = helpingTower.getFunctionFactor();

        helpingTower.update();

        assertEquals(initialRange.getX() + 10, helpingTower.getHittingRange().getX());
        assertEquals(initialRange.getY() + 10, helpingTower.getHittingRange().getY());
        assertEquals(initialFunctionFactor + 5, helpingTower.getFunctionFactor());
    }

    @Test
    public void testPosition() {
        helpingTower.setPosition(15.0, 25.0);
        assertEquals(15.0, helpingTower.getPosition().get().getX());
        assertEquals(25.0, helpingTower.getPosition().get().getY());
    }

    @Test
    public void testHittingRange() {
        Position hittingRange = helpingTower.getHittingRange();
        assertEquals(10, hittingRange.getX());
        assertEquals(10, hittingRange.getY());
    }

    @Test
    public void testTowerSpriteManager() {
        assertNotNull(helpingTower.getTowerSpriteManager());
    }

    @Test
    public void testUpgradable() {
        assertFalse(helpingTower.upgradable(200));
    }
}

