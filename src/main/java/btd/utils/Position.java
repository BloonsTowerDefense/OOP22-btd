package btd.utils;

public class Position {

    private double x,y;

    public Position(double x, double y) {
        set(x,y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(final Position pos){
        set(pos.getX(),pos.getY());
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
