package btd.model.entity;

import java.awt.image.BufferedImage;
import java.util.List;

public interface TowerSpriteManager {

    /**
     * Creates a map of tower names and respective paths of resources
     * */
    void towerResourceMap();

    /**
     * Handles the changes of sprites when the tower is upgraded.
     *
     * @param towerName The name of the tower to be upgraded
     * */
    void upgrade(String towerName);

    /**
     * Method to get the sprite/sprites of a tower.
     * If the user need the single tower image it can
     * access the first element of the array.
     *
     * @return Return a list of sprites for a tower,
     * usually the list of the tower when it's shooting
     * */
    List<BufferedImage> getTowerSpriteList();
}
