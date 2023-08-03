package btd.model.entity;

import btd.utils.Position;

import java.util.Optional;

public class EntityImpl implements Entity {

    private String name;
    private Optional<Position> position;

    public EntityImpl(final String name) {
        this.name = name;
        this.position = Optional.empty();
    }

    @Override
    public Optional<Position> getPosition() {
        return this.position;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setPosition(double x, double y) {
        this.position = Optional.of(new Position(x, y));
    }

    public double getX() {
        return this.position.isPresent() ? this.position.get().getX() : 0;
    }

    public double getY() {
        return this.position.isPresent() ? this.position.get().getY() : 0;
    }
}
//    @Override
//    public void setGameMap(GameMap gameMap) {
//
//    }
//
//    @Override
//    public GameMap getGameMap() {
//        return null;
//    }
//}
