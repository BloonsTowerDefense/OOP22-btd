package btd.model.entity;

import btd.model.map.MapElement;
import btd.utils.Position;

import java.util.List;
import java.util.Optional;

public class ShootingTower implements Tower {

    private final static Integer SELL_PRICE_FACTOR = 100;
    private String name;

    private Integer power;

    //Price increments after upgrade
    private Integer price;

    private List<Position> position;

    public ShootingTower(String name){
        super();
        this.name = name;
    }

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
    public Integer sell() {
        //When player sells a tower the price is lower than the initial price
        return getPrice() - SELL_PRICE_FACTOR;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void SetPosition(double x, double y) {

    }

    @Override
    public void setGameMap(MapElement gameMap) {

    }

    @Override
    public MapElement getGameMap() {
        return null;
    }
}
