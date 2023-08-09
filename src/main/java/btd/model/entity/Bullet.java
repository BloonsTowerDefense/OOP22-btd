package btd.model.entity;

import btd.utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private BufferedImage sprite;

    private Position startingPosition;

    private Position targetPosition;

    private double elapsedTime = 1;

    public Bullet(Position startingPosition, BufferedImage sprite){
        this.startingPosition = startingPosition;
        this.sprite = sprite;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setTargetPosition(Position position){
        this.targetPosition = position;
    }

    public void setStartingPosition(Position position){
        this.startingPosition = position;
    }

    public void updatePosition(double deltaTime, Graphics g) {
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
