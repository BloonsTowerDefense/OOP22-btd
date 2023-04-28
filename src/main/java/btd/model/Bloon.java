package btd.model;

public interface Bloon extends Entity{
    double getHealth();

    int getMoney();

    boolean hasReachedEnd();

    void hit(int damage);

    void move();

    boolean isDead();
}
