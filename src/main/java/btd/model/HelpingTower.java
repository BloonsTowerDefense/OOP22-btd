package btd.model;

import btd.utils.Position;

import java.util.List;
import java.util.Optional;

public class HelpingTower implements Tower {

    private String name;

    private String function;

    private List<Position> position;

    public HelpingTower(String name,String function){
        super();
        this.name = name;
        this.function = function;
    }

    @Override
    public void create() {

    }

    @Override
    public boolean upgradable() {
        return false;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void changeApperance() {

    }

    @Override
    public void sell() {
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

    }

    @Override
    public void setGameMap(GameMap gameMap) {

    }

    @Override
    public GameMap getGameMap() {
        return null;
    }
}
