package btd.model.entity;

import btd.utils.Position;

import java.util.Optional;

/**
 * Represents an entity in the tower defense game, which can have a position and a name.
 */
public interface Entity {

    /**
     * Returns an optional position of the entity.
     *
     * @return An Optional containing the position of the entity, if available; otherwise, an empty Optional.
     */
    Optional<Position> getPosition();

    /**
     * Returns the name of the entity.
     *
     * @return The name of the entity.
     */
    String getName();

    /**
     * Sets the position of the entity to the specified coordinates.
     *
     * @param x The x-coordinate of the new position.
     * @param y The y-coordinate of the new position.
     */
    void setPosition(double x, double y);
}

