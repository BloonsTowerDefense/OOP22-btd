package btd.view;

import btd.utils.ImagePath;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static ImageLoader instance = new ImageLoader();

    public static Image loadImage(Class<?> className, String imageName) {
        return instance.load(className, imageName);
    }

    private Map<String, Image> images;

    private ImageLoader() {
        images = new HashMap<String, Image>();
    }

    private Image load(Class<?> resourceLocation, String imageName) {
        if (!images.containsKey(imageName)) {
            Image image = Toolkit.getDefaultToolkit().getImage(resourceLocation.getResource(imageName));
            images.put(imageName, image);
        }
        return images.get(imageName);
    }


    public static Image loadImageFromFile(ImagePath imagePath) {
        return instance.loadFromFile(imagePath);
    }

    private Image loadFromFile(ImagePath imagePath) {
        if (!images.containsKey(imagePath.toString())) {
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath.toString()));
            images.put(imagePath.toString(), image);
        }
        return images.get(imagePath.toString());
    }
}
