package btd.model.entity;

import btd.model.map.MapElement;
import btd.utils.Position;

import java.util.List;
import java.util.Optional;

public class ShootingTower implements Tower {

    private String name;

    private Integer power;

    private List<Position> position;

    @Override
    public void create() {
        return ;
    }

    @Override
    public boolean upgradable() {
        return false;
    }

    @Override
    public void upgrade() {
        return ;
    }

    @Override
    public void changeApperance() {
        return ;
    }

    @Override
    public void sell() {
        return ;
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void SetPosition(double x, double y) {
        return;
    }

    @Override
    public void setGameMap(MapElement gameMap) {

    }

    @Override
    public MapElement getGameMap() {
        return null;
    }

}
