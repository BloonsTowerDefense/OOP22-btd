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
        g.drawImage(this.sprite,(int) startX,(int) startY,null);
        double endX = targetPosition.getX();
        double endY = targetPosition.getY();

        // Calculate the current position based on linear interpolation
        double t = Math.min(1, this.elapsedTime/deltaTime);  // Ensure t doesn't exceed 1
        double currentX = startX + (endX - startX) * t;
        double currentY = startY + (endY - startY) * t;

        // Update the bullet's position
        setStartingPosition(new Position(currentX, currentY));
        g.drawImage(this.sprite,(int) this.startingPosition.getX(),(int) this.startingPosition.getY(),null);
        // Update the elapsed time
        this.elapsedTime += deltaTime;
    }
}
