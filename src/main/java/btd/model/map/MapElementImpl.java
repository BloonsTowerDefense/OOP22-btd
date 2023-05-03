package btd.model.map;

import java.awt.image.BufferedImage;

public class MapElementImpl implements MapElement{
    
    private BufferedImage img;
    private boolean canPlaceTower;

    public MapElementImpl(final BufferedImage img, final boolean flag){
        this.img = img;
        this.canPlaceTower = flag;
    }

    @Override
    public BufferedImage getImg() {
        return this.img;
    }

    @Override
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    @Override
    public boolean canPlaceTower() {
        return this.canPlaceTower;
    }

    @Override
    public void setCanPlaceTower(boolean flag) {
        this.canPlaceTower = flag;
    }
    
}
