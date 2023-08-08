package btd.model.map;

import java.awt.image.BufferedImage;

/**
 * This interface represents an element of the game map.
 * It defines methods for getting and setting the image associated and determining if a tower can be placed on it.
 */
public interface MapElement {

    /**
     * Returns the image associated with the map element.
     *
     * @return an image representing the map element.
     */
    public BufferedImage getImg();


    /**
     * Sets the image for the map element.
     *
     * @param img image to set for the element.
     */
    public void setImg(BufferedImage img);

    /**
     * Checks if a tower can be placed on the map element.
     *
     * @return true if a tower can be placed on the element otherwise returns false.
     */
    public boolean canPlaceTower();

    /**
     * Sets if a tower can be placed on the map element.
     *
     * @param flag pass true if a tower can be placed, false if it can't.
     */
    public void setCanPlaceTower(boolean flag);
}
