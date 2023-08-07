package btd.model.entity;

import btd.utils.Position;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class HelpingTower implements Tower {

    private final static int SELL_PRICE_FACTOR = 100;

    private final String towerName;

    private final String function;

    private Integer price;

    private Position position;

    private Position hittingRange;
    private final TowerSpriteManager towerSpriteManager;

    public HelpingTower(String towerName,final String function, Position position){
        this.towerSpriteManager = new TowerSpriteManagerImpl(towerName);
        this.towerName = towerName;
        this.function = function;
        this.position = position;
    }

    @Override
    public boolean upgradable(Integer playerMoney) {
        return false;
    }

    @Override
    public void update() {
        this.towerSpriteManager.upgrade(this.towerName);
        this.hittingRange = new Position(hittingRange.getX()+10, hittingRange.getY()+10);
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
        return Optional.ofNullable(this.position);
    }

    @Override
    public String getName() {
        return this.towerName;
    }

    public String getFunction(){
        return this.function;
    }

    @Override
    public void setPosition(double x, double y) {
        return ;
    }

    @Override
    public BufferedImage getTowerSprite(){
        return towerSpriteManager.getTowerSpriteList().get(0);
    }

    @Override
    public TowerSpriteManager getTowerSpriteManager(){
        return this.towerSpriteManager;
    }

}
