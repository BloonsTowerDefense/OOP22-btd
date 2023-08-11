package btd.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * A utility class for managing and providing access to game resources, such as textures for different items.
 * The Resources class uses lazy initialization and provides a singleton instance for resource management.
 */
public final class Resources {

    private final Map<ItemType, BufferedImage> textures = new HashMap<>();

    /**
     * Private inner class responsible for lazy initialization of the Resources singleton instance.
     */
    private static class LazyRes {
        private static final Resources SINGLETON = new Resources();
    }

    /**
     * Returns the singleton instance of the Resources class.
     *
     * @return The singleton instance of the Resources class.
     */
    public static Resources getRes() {
        return LazyRes.SINGLETON;
    }

    /**
     * Private constructor for initializing resources.
     */
    private Resources() {
        try {
            textures.put(ItemType.RED_BLOON, ImageIO.read(getClass().getResourceAsStream("/bloonsSprites/red_bloon.png")));
            textures.put(ItemType.BLUE_BLOON, ImageIO.read(getClass().getResourceAsStream("/bloonsSprites/blue_bloon.png")));
            textures.put(ItemType.BLACK_BLOON, ImageIO.read(getClass().getResourceAsStream("/bloonsSprites/black_bloon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the buffered image associated with the specified item type.
     *
     * @param item The ItemType for which the texture is requested.
     * @return The buffered image texture for the specified item type.
     */
    public BufferedImage getTextures(final ItemType item) {
        return textures.get(item);
    }
}


