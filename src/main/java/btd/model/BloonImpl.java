package btd.model;

import btd.utils.Position;

import java.util.List;

public class BloonImpl extends EntityImpl implements Bloon{

    private String name;
    private int health;
    private double speed;
    private int damage;
    private final int money;
    private Path path;
    private int currentPathIndex;
    private double distanceTraveled;

    public BloonImpl(final String name, final double speed, final int health, final int money) {
        super(name);
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.money=money;
        this.currentPathIndex = 0;
        this.distanceTraveled = 0;
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
        return currentPathIndex == path.getPath().size();
    }

    @Override
    public void hit(int amount) {
        health -= amount;
    }

    @Override
    public void move(final long time) {
        if(hasReachedEnd()){
            return;
        }

    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
