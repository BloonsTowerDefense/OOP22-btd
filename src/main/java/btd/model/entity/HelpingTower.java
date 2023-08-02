package btd.model.entity;

import btd.utils.Position;

import java.util.List;
import java.util.Optional;

public class HelpingTower implements Tower {

    private final static int SELL_PRICE_FACTOR = 100;

    private String towerName;

    private String function;

    private Integer price;

    private List<Position> position;

    public HelpingTower(String name,final String function){
        super();
        this.towerName = name;
        this.function = function;
    }

    @Override
    public boolean upgradable(Integer playerMoney) {
        return false;
    }

    @Override
    public void update() {
        return ;
    }

    private void changeAppearance() {
        return;
    }

    @Override
    public Integer sell() {
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
        return this.towerName;
    }

    @Override
    public void setPosition(double x, double y) {
        return ;
    }

}
