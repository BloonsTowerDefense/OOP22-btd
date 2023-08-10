package btd.model.entity;

import btd.model.map.Path;
import btd.utils.Direction;


/**
 * Implementation of a Bloon in the game.
 * Bloons are enemies that move along a specified path and can be hit and defeated by towers.
 */
public class BloonImpl extends EntityImpl implements Bloon {

    private int health;
    private final double speed;
    private int damage;
    private final int money;
    private Path path;
    private int currentPathIndex;
    private Direction currentDirection;
    private boolean alive;

    private BloonType type;
    private double remainingDistance;

    /**
     * Constructs a BloonImpl object with the specified BloonType and path.
     *
     * @param type The type of the bloon.
     * @param path The path that the bloon follows.
     */
    public BloonImpl(final BloonType type, final Path path) {
        super(type.name());
        this.type = type;
        this.health = type.getHealth();
        this.speed = type.getSpeed();
        this.money = type.getMoney();
        this.currentPathIndex = 0;
        this.alive = true;
        this.path = path;
        if (!path.getDirections().isEmpty()) {
            this.currentDirection = path.getDirections().get(0);
        }
        this.remainingDistance = this.path.getTileSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasReachedEnd() {
        return this.currentPathIndex == path.getDirections().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(final int amount) {
        this.health -= amount;
        if (health <= 0) {
            this.alive = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final long time) {
        if (currentPathIndex < this.path.getDirections().size()) {
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
            this.setPosition(x, y);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long time) {
        if (!this.hasReachedEnd()) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return !alive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPath(final Path path) {
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BloonType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPathIndex() {
        return this.currentPathIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final int health) {
        this.health = health;
    }
}
