package btd.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public final class Resources {

    private final Map<ItemType, BufferedImage> textures = new HashMap<>();
    public static class LazyRes {
        private static final Resources SINGLETON = new Resources();
    }

    public static Resources getRes() {
        return LazyRes.SINGLETON;
    }

    private Resources() {
        try {
            textures.put(ItemType.RED_BLOON,
                    ImageIO.read(Resources.class.getClassLoader().getResourceAsStream("/bloonsSprites/red_bloon.png")));
            textures.put(ItemType.BLUE_BLOON,
                    ImageIO.read(Resources.class.getClassLoader().getResourceAsStream("/bloonsSprites/blue_bloon.png")));
            textures.put(ItemType.BLACK_BLOON,
                    ImageIO.read(Resources.class.getClassLoader().getResourceAsStream("/bloonsSprites/black_bloon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTextures(final ItemType item) {
        return textures.get(item);
    }

}
