package btd.view;

import btd.utils.Position;

public interface BloonView extends Renderable{

    /**
     * Keeps updated the graphical position of the bloon.
     * Render to call to draw the image.
     * @param pos the new position.
     */
    void updatePos(Position pos);

}
