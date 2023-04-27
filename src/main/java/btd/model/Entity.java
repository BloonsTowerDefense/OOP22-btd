package btd.model;

public interface Entity {

    Position getPosition();

    String getName();

    void SetPosition(double x, double y);

    void setGameMap(GameMap gameMap);

    GameMap getGameMap();

}
