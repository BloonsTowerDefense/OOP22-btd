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
    BufferedImage getImg();
}
