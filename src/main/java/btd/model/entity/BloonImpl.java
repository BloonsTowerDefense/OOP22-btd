package btd.model.entity;

import btd.model.map.Path;
import btd.utils.Direction;

public class BloonImpl extends EntityImpl implements Bloon{

    private int health;
    private double speed;
    private int damage;
    private final int money;
    private Path path;
    private int currentPathIndex;
    private Direction currentDirection;

    public BloonImpl(final String name, final double speed, final int health, final int money, final Path path) {
        super(name);
        this.health = health;
        this.speed = speed;
        this.money = money;
        this.currentPathIndex = 0;
        this.path = path;
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
        health -= amount;
    }

    @Override
    public void move(final long time) {
        final Direction direction = path.getDirections().get(this.currentPathIndex);
        double actualSpeed = speed * time / 1000;
        double x = this.getPosition().get().getX();
        double y = this.getPosition().get().getY();
        switch (currentDirection){
            case UP:
                y+= path.getTileSize();
                break;
            case DOWN:
                y-= path.getTileSize();
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
            this.currentDirection = this.getGameMap().getPath().getDirections(this.currentPathIndex);
            this.currentPathIndex++;
        }
        this.move(time);
    }


    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
