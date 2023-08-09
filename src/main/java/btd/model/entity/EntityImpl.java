package btd.model.entity;

import btd.utils.Position;

import java.util.Optional;

/**
 * Represents a basic implementation of the Entity interface in the tower defense game, which can have a position and a name.
 */
public class EntityImpl implements Entity {

    private String name;
    private Optional<Position> position;

    /**
     * Constructs an EntityImpl object with the specified name and no initial position.
     *
     * @param name The name of the entity.
     */
    public EntityImpl(final String name) {
        this.name = name;
        this.position = Optional.empty();
    }

    /**
     * Returns an optional position of the entity.
     *
     * @return An Optional containing the position of the entity, if available; otherwise, an empty Optional.
     */
    @Override
    public Optional<Position> getPosition() {
        return this.position;
    }

    /**
     * Returns the name of the entity.
     *
     * @return The name of the entity.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets the position of the entity to the specified coordinates.
     *
     * @param x The x-coordinate of the new position.
     * @param y The y-coordinate of the new position.
     */
    @Override
    public void setPosition(double x, double y) {
        this.position = Optional.of(new Position(x, y));
    }

    /**
     * Returns the x-coordinate of the entity's position.
     *
     * @return The x-coordinate of the entity's position, or 0 if no position is set.
     */
    public double getX() {
        return this.position.isPresent() ? this.position.get().getX() : 0;
    }

    /**
     * Returns the y-coordinate of the entity's position.
     *
     * @return The y-coordinate of the entity's position, or 0 if no position is set.
     */
    public double getY() {
        return this.position.isPresent() ? this.position.get().getY() : 0;
    }
}


