package btd.view;

import btd.utils.ImagePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static Image loadImageFromFile(final ImagePath imagePath) {
        try {
            String path = imagePath.getImagePath();
            URL url = ImageLoader.class.getResource(path);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
