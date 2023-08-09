package btd.model.entity;

import btd.model.map.Path;
import btd.utils.Direction;

import java.awt.Image;

/**
 * Implementation of a Bloon in the game.
 * Bloons are enemies that move along a specified path and can be hit and defeated by towers.
 */
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
    private double remainingDistance;

    /**
     * Constructs a BloonImpl object with the specified BloonType and path.
     *
     * @param type The type of the bloon.
     * @param path The path that the bloon follows.
     */
    public BloonImpl(final BloonType type, Path path) {
        super(type.name());
        this.type = type;
        this.health = type.getHealth();
        this.speed = type.getSpeed();
        this.money = type.getMoney();
        this.currentPathIndex = 0;
        this.alive = true;
        this.image = this.type.getImage();
//        this.image = ImageLoader.loadImage(BloonImpl.class, ImagePath.RED_BLOON);

        this.path = path;
        if (!path.getDirections().isEmpty()) {
            this.currentDirection = path.getDirections().get(0);
        }
        this.remainingDistance = this.path.getTileSize();
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
        return this.currentPathIndex == path.getDirections().size();
    }

    @Override
    public void hit(int amount) {
        this.health -= amount;
        if (health <= 0) {
            this.alive = false;
        }
    }

    @Override
    public void move(final long time) {
        if(currentPathIndex < this.path.getDirections().size()) {
            this.currentDirection = this.path.getDirections().get(this.currentPathIndex);
            double actualSpeed = speed;
            double x = this.getPosition().get().getX();
            double y = this.getPosition().get().getY();

            if (remainingDistance <= actualSpeed) {
                // Move remainingDistance and update currentPathIndex
                actualSpeed = remainingDistance;
                remainingDistance = path.getTileSize();
                currentPathIndex++;
            } else {
                remainingDistance -= actualSpeed;
            }
            System.out.println("\n speed: " + this.speed + " actualSpeed: " + actualSpeed);
            switch (currentDirection) {
                case UP:
                    y -= actualSpeed;
                    break;
                case DOWN:
                    y += actualSpeed;
                    break;
                case LEFT:
                    x -= actualSpeed;
                    break;
                case RIGHT:
                    x += actualSpeed;
                    break;
                default:
                    break;
            }
            System.out.println("\nposizione: " + "X: " + this.getX() + " Y: " + this.getY() + " TileSize: " + this.path.getTileSize());
            this.setPosition(x,y);
        }

    }

    @Override
    public void update(long time) {
        if(!this.hasReachedEnd()){
            if (this.currentPathIndex < this.path.getDirections().size()) {
                /*this.currentDirection = this.path.getDirections().get(this.currentPathIndex);
                System.out.println("\n" + this.currentDirection);
                System.out.println("\n pathIndex: " + this.currentPathIndex);
                System.out.println(this.path.getDirections().size());
                this.currentPathIndex++;*/
                this.move(time);
            } else {
                // Gestisci qui la situazione in cui currentPathIndex >= path.getDirections().size()
                this.alive = false;
            }
        } else {
            this.alive = false;
        }
        System.out.println("\nBloon position: " + this.getPosition());
    }

    @Override
    public boolean isDead() {
        return !alive;
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
    public int getCurrentPathIndex(){
        return this.currentPathIndex;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
