package btd.model.entity;

import btd.utils.Position;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Bullet class represents a bullet fired from a tower.
 * Bullets travel from a starting position to a target position, leaving a trail behind them.
 */
public class Bullet {

    private final BufferedImage sprite;

    private final Position startingPosition;

    private Position targetPosition;

    private double elapsedTime = 1;

    /**
     * Constructs a new Bullet object with the specified starting position and sprite.
     *
     * @param startingPosition The starting position of the bullet.
     * @param sprite           The image sprite of the bullet.
     */
    public Bullet(Position startingPosition, BufferedImage sprite) {
        this.startingPosition = startingPosition;
        this.sprite = sprite;
    }
    

    /**
     * Sets the target position for the bullet.
     *
     * @param position The target position.
     */
    public void setTargetPosition(Position position) {
        this.targetPosition = position;
    }

    /**
     * Updates the position of the bullet and draws it along with its trail.
     *
     * @param deltaTime The time interval for the update.
     * @param g         The Graphics object for drawing.
     */
    public void updatePosition(final double deltaTime, final Graphics g) {
        double startX = startingPosition.getX();
        double startY = startingPosition.getY();
        double endX = targetPosition.getX();
        double endY = targetPosition.getY();

        double t = Math.min(1, this.elapsedTime / deltaTime);

        double currentX = startX + (endX - startX) * t;
        double currentY = startY + (endY - startY) * t;

        int trailCount = 4;      // Number of trail segments

        // Draw the bullet trail behind the current position
        for (int i = 0; i < trailCount; i++) {
            double trailX = currentX - i * (endX - startX) * 0.3;
            double trailY = currentY - i * (endY - startY) * 0.3;
            g.drawImage(this.sprite, (int) trailX, (int) trailY, 20, 20, null);
        }

        // Draw the bullet at the current position
        g.drawImage(this.sprite, (int) currentX, (int) currentY, 20, 20, null);

        this.elapsedTime += deltaTime;
    }
}

