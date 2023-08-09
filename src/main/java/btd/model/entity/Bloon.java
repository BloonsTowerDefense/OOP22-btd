package btd.model.entity;

import btd.model.map.Path;


/**
 * Represents a bloon entity in the game.
 * Bloons are enemies that move along a specified path and can be hit and defeated by towers.
 */
public interface Bloon extends Entity {

    /**
     * Get the health of the bloon.
     *
     * @return The health of the bloon.
     */
    double getHealth();

    /**
     * Returns the amount of money the player receives when the bloon dies.
     *
     * @return The amount of money earned by killing the bloon.
     */
    int getMoney();

    /**
     * Checks if the bloon has reached the end of the path.
     *
     * @return {@code true} if the bloon has reached the end, {@code false} otherwise.
     */
    boolean hasReachedEnd();

    /**
     * Inflicts damage on the bloon.
     *
     * @param damage The amount of damage to be inflicted.
     */
    void hit(int damage);

    /**
     * Move the bloon.
     *
     * @param time The elapsed time since the last movement update.
     */
    void move(long time);

    /**
     * Updates the state of the bloon based on the given time.
     *
     * @param time The elapsed time since the last update.
     */
    void update(long time);

    /**
     * Checks if the bloon is dead.
     *
     * @return {@code true} if the bloon is dead, {@code false} otherwise.
     */
    boolean isDead();

    /**
     * Sets the path that the bloon will follow.
     *
     * @param path The path to be set for the bloon.
     */
    void setPath(Path path);

    /**
     * Returns the type of the bloon.
     *
     * @return The type of the bloon.
     */
    BloonType getType();

    /**
     * Returns the current path index of the bloon's movement along the path.
     *
     * @return The current path index.
     */
    int getCurrentPathIndex();

    /**
     * Sets the health of the bloon.
     * @param health
     */
    void setHealth(int health);
}

