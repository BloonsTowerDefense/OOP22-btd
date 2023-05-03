package btd.model.entity;

import btd.utils.Position;

import java.util.Optional;

public interface Entity {

    Optional<Position> getPosition();

    String getName();

    void SetPosition(double x, double y);

    void setGameMap(GameMap gameMap);

    GameMap getGameMap();

}
