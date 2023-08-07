package btd.model.entity;

import btd.model.entity.Entity;
import btd.utils.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface Tower extends Entity {

    /**
     * Method used to check if the tower can be upgraded
     *
     *@param playerMoney User's current game money
     *@return true if user's money are major or equals to tower's price
    */
    boolean upgradable(Integer playerMoney);

    /**
     * Method used to upgrade the tower if it's upgradable
     *
     * @throws IOException if the image of the tower is not loaded correctly or is missing
     * */
    void update();

    /**
     * Method used to sell the tower
     *
     * @return returns the money earned by selling the tower
     * */
    Integer sell();

    /**
     * Method used to get the price of the current tower
     *
     * @return returns the tower's price
     * */
    Integer getPrice();


    /**
     * Method used to get tower's sprites
     *
     * @return returns a list with tower's sprites
     * */
    BufferedImage getTowerSprite();

    /**
     * Method used to get the sprite manager of each tower
     *
     * @return returns a TowerSpriteManager
     * */
    TowerSpriteManager getTowerSpriteManager();


    /**
     * Method used to set the position of the tower in the map
     *
     * @param x the parameter that determines the X-AXIS position in the map
     * @param y the parameter that determines the Y-AXIS position in the map
     * */
    void setPosition(double x, double y);
}
