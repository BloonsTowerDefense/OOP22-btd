package btd.model.map;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 * This class implements the {@link MapElement} interface.
 * It represents an element of the map.
 */
public class MapElementImpl implements MapElement {

    private final BufferedImage img;

    /**
     * Standard constructor.
     * 
     * @param img image to be saved and then displayed.
     */
    public MapElementImpl(final BufferedImage img) {
        this.img = img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImg() {
        return this.img;
        //return clone(this.img);
    }

    public static BufferedImage clone(BufferedImage bufferImage) {
        ColorModel colorModel = bufferImage.getColorModel();
        java.awt.image.WritableRaster raster = bufferImage.copyData(null);
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
