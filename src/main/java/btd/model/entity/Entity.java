package btd.model.entity;

import btd.utils.Position;

import java.util.Optional;

public interface Entity {

    Optional<Position> getPosition();

    String getName();

    void setPosition(double x, double y);

    void setGameMap(GameMap gameMap);

    GameMap getGameMap();

}
