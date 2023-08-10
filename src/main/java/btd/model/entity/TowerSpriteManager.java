package btd.model.entity;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The interface of the tower sprite manager. This class handles the set-up of the sprites
 * for a tower and the graphics updates.
 * */
public interface TowerSpriteManager {

  /**
  * Creates a map of tower names and respective paths of resources.
  **/
  void towerResourceMap();

  /**
  * Handles the changes of sprites when the tower is upgraded.
  *
  * @param towerName The name of the tower to be upgraded
  **/
  void upgrade(String towerName);

  /**
  * Method to get the current sprites of a tower.
  * If the user need the single tower image it can
  * access the first element of the array.
  *
  * @return Return a list of sprites for a tower,
  *     usually the list of the tower when it's shooting.
  **/
  List<BufferedImage> getTowerSpriteList();

  /**
  * Method used to get the specific sprites for a tower.
  *
  * @param towerName The name of the tower.
  * @param upgradeNumber The number of the upgrade.
  *
  * @return Return a list of buffered images
  **/
  List<BufferedImage> getUpgradeSprites(String towerName, Integer upgradeNumber);

}
