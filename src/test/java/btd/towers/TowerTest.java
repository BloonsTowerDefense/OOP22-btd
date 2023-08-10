package btd.towers;

import static org.junit.jupiter.api.Assertions.*;

import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TowerTest {

    private ShootingTower shootingTower;
    private HelpingTower helpingTower;

    @BeforeEach
    public void setUp() {
        shootingTower = new ShootingTower("blackAdam", 2, 100, new Position(100,100));
        helpingTower = new HelpingTower("powerEnhancer", "Range", 100, new Position(100, 100));
    }

    @Test
    public void testUpgrade() {
        Integer initialPower = shootingTower.getPower();
        Position initialRange = shootingTower.getHittingRange();

        shootingTower.update();

        assertEquals(initialPower + 2, shootingTower.getPower());
        assertEquals(initialRange.getX() + 5, shootingTower.getHittingRange().getX());
        assertEquals(initialRange.getY() + 5, shootingTower.getHittingRange().getY());

        Position initialRange1 = helpingTower.getHittingRange();
        Integer initialFunctionFactor = helpingTower.getFunctionFactor();

        helpingTower.update();

        assertEquals(initialRange1.getX() + 10, helpingTower.getHittingRange().getX());
        assertEquals(initialRange1.getY() + 10, helpingTower.getHittingRange().getY());
        assertEquals(initialFunctionFactor + 5, helpingTower.getFunctionFactor());
    }


    @Test
    public void testUpgradePrice() {
        assertEquals(shootingTower.getPrice() + 50, shootingTower.getUpgradePrice());
    }

    @Test
    public void testUpgradable() {
        assertTrue(shootingTower.upgradable(200));
        assertFalse(shootingTower.upgradable(50));
    }

    @Test
    public void testPosition() {
        shootingTower.setPosition(10.0, 20.0);
        assertEquals(10.0, shootingTower.getPosition().get().getX());
        assertEquals(20.0, shootingTower.getPosition().get().getY());

        helpingTower.setPosition(15.0, 25.0);
        assertEquals(15.0, helpingTower.getPosition().get().getX());
        assertEquals(25.0, helpingTower.getPosition().get().getY());
    }

    @Test
    public void testHittingRange() {
        shootingTower.setHittingRange(8.0, 15.0);
        assertEquals(8.0, shootingTower.getHittingRange().getX());
        assertEquals(15.0, shootingTower.getHittingRange().getY());

        Position hittingRange = helpingTower.getHittingRange();
        assertEquals(10, hittingRange.getX());
        assertEquals(10, hittingRange.getY());
    }

    @Test
    public void testPower() {
        shootingTower.setPower(15);
        assertEquals(15, shootingTower.getPower());
    }

    @Test
    public void testTowerSpriteManager() {
        assertNotNull(shootingTower.getTowerSpriteManager());
        assertNotNull(helpingTower.getTowerSpriteManager());
    }
}

