package btd.model.entity;

import btd.model.map.Path;

public interface Bloon extends Entity{
    double getHealth();

    int getMoney();

    boolean hasReachedEnd();

    void hit(int damage);

    void move(long time);

    void update(long time);

    boolean isDead();

    void setPath(Path path);
}
