package btd.model.map;

import java.awt.image.BufferedImage;

public interface MapElement {
    public BufferedImage getImg();
    public void setImg(BufferedImage img);
    public boolean canPlaceTower();
    public void setCanPlaceTower(boolean flag);
}
