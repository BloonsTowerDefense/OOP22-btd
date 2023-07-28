package btd.view;

import btd.utils.Position;

import java.awt.*;

public class BloonViewImpl implements BloonView{

    private Position position;
    private Image sprite;
    private final int height;
    private final int width;

    public BloonViewImpl(final Position pos, final int height, final int width, final Image img){
        this.position = pos;
        this.height = height;
        this.width = width;
        this.sprite = img;
    }
    @Override
    public void updatePos(final Position pos) {
        this.position = pos;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.sprite, (int)this.position.getX(), (int)this.position.getY(), this.height, this.width, null);
    }
}
