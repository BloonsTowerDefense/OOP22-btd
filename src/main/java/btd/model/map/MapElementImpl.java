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
        //this.img = img;
        this.img = clone(img);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImg() {
        //return this.img;
        return clone(this.img);
    }

    private BufferedImage clone(final BufferedImage bufferImage) {
        final ColorModel colorModel = bufferImage.getColorModel();
        final java.awt.image.WritableRaster raster = bufferImage.copyData(null);
        final boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
