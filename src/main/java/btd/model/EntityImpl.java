package btd.model;

import btd.utils.Position;

import java.util.Optional;

public class EntityImpl implements Entity{

    private String name;
    private Optional<Position> position;
    private GameMap gameMap;

    public EntityImpl(final String name){
        this.name = name;
        this.position = Optional.empty();
    }
    @Override
    public Optional<Position> getPosition() {
        return this.position;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void SetPosition(double x, double y) {

    }

    @Override
    public void setGameMap(GameMap gameMap) {

    }

    @Override
    public GameMap getGameMap() {
        return null;
    }
}