package btd.model;

import btd.model.entity.Entity;
import btd.utils.Position;

import java.util.List;
import java.util.Set;

public interface GameModel {

    enum State {
        PLAYING,
        DEFEAT
    }

    void updateState(long time);

    List<Entity> getEntities();

    int getLife();

    Set<Position> getAvailablePositions();

    double getMoney();



}
