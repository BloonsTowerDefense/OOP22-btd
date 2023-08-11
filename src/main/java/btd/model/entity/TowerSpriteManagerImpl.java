package btd.model.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;


/**
 * Implementation of the tower sprite manager. This class handles the set-up of the sprites
 * for a tower and the graphics updates.
 * */
public class TowerSpriteManagerImpl implements TowerSpriteManager {

  private final List<BufferedImage> spriteList;

  private Map<String, String> towerResourceMap;

  /**
   * Builds the sprite manager specifying the tower name.
   *
   * @param towerName The name of the tower.
   * */
  public TowerSpriteManagerImpl(final String towerName) {
    spriteList = new ArrayList<>();
    towerResourceMap();
    setTowerSprites(towerName, 0);
  }


  /**
  * Method used to map the towers to their sprite path .
  **/
  public void towerResourceMap() {
    towerResourceMap = new HashMap<>();
    towerResourceMap.put("blackAdam", "/towers/blackAdam/");
    towerResourceMap.put("deadColossus", "/towers/deadColossus/");
    towerResourceMap.put("voldelife", "/towers/voldelife/");
    towerResourceMap.put("powerEnhancer", "/towers/powerEnhancer/");
    towerResourceMap.put("rangeEnhancer", "/towers/rangeEnhancer/");
  }

  /**
  * Method used to add the sprites of the specified tower in a list.
  *
  * @param towerName The name of the tower to get the sprites.
  * @param upgradeNumber The number of the upgrade to get the corresponding sprites.
  **/
  private void setTowerSprites(final String towerName, final Integer upgradeNumber) {
    spriteList.clear();
    String towerPath = towerResourceMap.get(towerName);
    if (towerPath != null) {
      for (int i = 0; i < 2; i++) {
        try {
          spriteList.add(ImageIO.read(Objects.requireNonNull(getClass()
                  .getResource(towerPath + "Upgrade" + upgradeNumber + "/sprite" + i + ".png"))));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
  * Method used to get a specific list of sprites for a specific upgrade.
  *
  * @param towerName The name of the tower.
  * @param upgradeNumber The number of the upgrade.
  * @return the list of sprites for the specified upgrade.
  **/
  public List<BufferedImage> getUpgradeSprites(final String towerName, final Integer upgradeNumber) {
    List<BufferedImage> sprites = new ArrayList<>();
    String towerPath = towerResourceMap.get(towerName);
    if (towerPath != null) {
      for (int i = 0; i < 2; i++) {
        try {
          sprites.add(ImageIO.read(Objects.requireNonNull(getClass()
                  .getResource(towerPath + "Upgrade" + upgradeNumber + "/sprite" + i + ".png"))));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return sprites;
  }

  /**
   * @{inheritdoc} .
   * */
  @Override
  public void upgrade(final String towerName) {
    setTowerSprites(towerName, 1);
  }

  /**
   * @{inheritdoc} .
   * */
  @Override
  public List<BufferedImage> getTowerSpriteList() {
    return spriteList;
  }
}
