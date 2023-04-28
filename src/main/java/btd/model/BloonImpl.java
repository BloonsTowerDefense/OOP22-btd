package btd.model;

import btd.utils.Position;

import java.util.List;

public class BloonImpl extends EntityImpl implements Bloon{

    private String name;
    private int health;
    private int speed;
    private int damage;
    private List<Position> path;
    private int currentPathIndex;

    public BloonImpl(final String name, int speed, int health) {
        super(name);
        this.health = health;
        this.speed = speed;
    }


    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public int getMoney() {
        return 0;
    }

    @Override
    public boolean hasReachedEnd() {
        return false;
    }

    @Override
    public void hit(int amount) {
        health -= amount;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
