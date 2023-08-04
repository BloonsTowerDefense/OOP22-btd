package btd.model.entity;

import btd.model.map.Path;
import btd.model.map.PathImpl;
import btd.utils.Direction;
import java.awt.Graphics2D;
import java.awt.Color;

import btd.utils.ImagePath;
import btd.view.ImageLoader;
import java.awt.Image;

import btd.utils.Position;
import java.util.List;

import java.awt.*;

public class BloonImpl extends EntityImpl implements Bloon{

    private int health;
    private double speed;
    private int damage;
    private final int money;
    private Path path;
    private int currentPathIndex;
    private Direction currentDirection;
    private boolean alive;

    private BloonType type;
    private Image image;

    public BloonImpl(final BloonType type, Path path) {
        super(type.name());
        this.type = type;
        this.health = type.getHealth();
        this.speed = type.getSpeed();
        this.money = type.getMoney();
        this.currentPathIndex = 0;
        this.alive = true;
        this.image = ImageLoader.loadImageFromFile(ImagePath.RED_BLOON);
//        this.image = ImageLoader.loadImage(BloonImpl.class, ImagePath.RED_BLOON);

        this.path = path;
        if (!path.getDirections().isEmpty()) {
            this.currentDirection = path.getDirections().get(0);
        }

    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public boolean hasReachedEnd() {
        return this.currentPathIndex >= path.getDirections().size();
    }

    @Override
    public void hit(int amount) {
        this.health -= amount;
        if (this.isDead()) {
            this.alive = false;
        }
    }

    @Override
    public void move(final long time) {
        final Direction direction = path.getDirections().get(this.currentPathIndex);
        double actualSpeed = speed * (time / 1000);
        double x = this.getPosition().get().getX();
        double y = this.getPosition().get().getY();
        double tileSize = 0;
        switch (currentDirection){
            case UP:
                y-= path.getTileSize();
                break;
            case DOWN:
                y+= path.getTileSize();
                break;
            case LEFT:
                x-= path.getTileSize();
                break;
            case RIGHT:
                x+= path.getTileSize();
                break;
            default:
                break;
        }
        this.setPosition(x,y);
    }

    @Override
    public void update(long time) {
        if(!this.hasReachedEnd()){
            this.currentDirection = this.path.getDirections().get(this.currentPathIndex);
            this.currentPathIndex++;
        }
        if(this.hasReachedEnd()){return;}
        this.move(time);
    }


    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public BloonType getType(){
        return this.type;
    }

    @Override
    public void draw(Graphics2D g) {
        if (this.image != null) {
            g.drawImage(this.image, (int) this.getX(), (int) this.getY(), null);
        } else {
            // Fallback to drawing a red circle if the image couldn't be loaded
            g.setColor(Color.RED);
            g.fillOval((int) this.getX(), (int) this.getY(), 48, 48);
        }
    }
}
