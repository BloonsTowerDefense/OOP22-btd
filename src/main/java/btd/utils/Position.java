package btd.utils;

/**
 * Represents a two-dimensional position in a Cartesian coordinate system.
 * The Position class provides methods to manipulate and retrieve the coordinates (x, y) of a point.
 */
public class Position {

    private double x, y;

    /**
     * Constructs a Position object with the specified coordinates (x, y).
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(double x, double y) {
        set(x, y);
    }

    /**
     * Returns the x-coordinate of the position.
     *
     * @return The x-coordinate of the position.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the position.
     *
     * @return The y-coordinate of the position.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x The new x-coordinate value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The new y-coordinate value.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the coordinates (x, y) of the position.
     *
     * @param x The new x-coordinate value.
     * @param y The new y-coordinate value.
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the coordinates of the position to match another Position object.
     *
     * @param pos The Position object whose coordinates will be copied.
     */
    public void setPosition(final Position pos) {
        set(pos.getX(), pos.getY());
    }

    /**
     * Returns a string representation of the position in the format: (x, y).
     *
     * @return A string representation of the position.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

