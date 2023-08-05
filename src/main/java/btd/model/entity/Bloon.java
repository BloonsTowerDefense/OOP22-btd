package btd.model.entity;

import btd.model.map.Path;

import java.awt.*;

import java.awt.Graphics2D; // Import Graphics2D

public interface Bloon extends Entity{
    double getHealth();

    int getMoney();

    boolean hasReachedEnd();

    void hit(int damage);

    void move(long time);

    void update(long time);

    boolean isDead();

    void setPath(Path path);

    public BloonType getType();

    //void draw(Graphics2D g);
}
