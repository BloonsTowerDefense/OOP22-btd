package btd.model.map;

import java.awt.image.BufferedImage;

/**
 * This class implements the {@link MapElement} interface.
 * It represents an element of the map.
 */
public class MapElementImpl implements MapElement{
    
    private BufferedImage img;
    private boolean canPlaceTower = false;

    /**
     * Standard constructor.
     */
    public MapElementImpl(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImg() {
        return this.img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPlaceTower() {
        return this.canPlaceTower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanPlaceTower(boolean flag) {
        this.canPlaceTower = flag;
    }
    
}
