package btd.model.entity;

import btd.utils.Position;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The interface of the towers.
 * */

public interface Tower extends Entity {

  /**
  * Method used to check if the tower can be upgraded.
  *
  *@param playerMoney User's current game money.
  *@return true if user's money are major or equals to tower's price.
  */
  boolean upgradable(Integer playerMoney);

  /**
  * Method used to upgrade the tower if it's upgradable.
  *.
  **/
  void update();

  /**
  * Method used to get the price of the current tower.
  *
  * @return returns the tower's price.
  **/
  Integer getPrice();


  /**
  * Method used to get the upgrade price of the current tower.
  *
  * @return returns the tower's upgrade price.
  **/
  Integer getUpgradePrice();

  /**
  * Method used to get the hitting/operational range for the tower.
  *
  * @return return a Position.
  **/
  Position getHittingRange();

  /**
  * Method used to get tower's sprites.
  *
  * @return returns a list with tower's sprites.
  **/
  BufferedImage getTowerSprite();

  /**
  * Method used to get the sprite manager of each tower.
  *
  * @return returns a TowerSpriteManager.
  **/
  TowerSpriteManager getTowerSpriteManager();

  /**
  * Method used to set the position of the tower in the map.
  *
  * @param x the parameter that determines the X-AXIS position in the map.
  * @param y the parameter that determines the Y-AXIS position in the map.
  **/
  void setPosition(double x, double y);
}
