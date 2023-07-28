package btd.view;

import java.awt.Graphics;

/**
 * An interface for any renderable entity of the game.
 *
 */
public interface Renderable {

    /**
     * Renders the entity on the screen.
     * @param g the graphics used for the rendering.
     */
    void render(Graphics g);
}

