package btd.model.entity;

public interface Bloon extends Entity{
    double getHealth();

    int getMoney();

    boolean hasReachedEnd();

    void hit(int damage);

    void move(long time);

    boolean isDead();
}
