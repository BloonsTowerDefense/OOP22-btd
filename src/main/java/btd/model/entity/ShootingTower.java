package btd.model.entity;

import btd.utils.Position;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class ShootingTower implements Tower {

    private final static int SELL_PRICE_FACTOR = 100;
    private final String towerName;
    private Integer power;

    private final Integer price;

    private Position position;

    private Position hittingRange;

    private final TowerSpriteManager towerSpriteManager;

    public ShootingTower(String towerName,Integer power,Integer price,Position position){
        this.towerSpriteManager = new TowerSpriteManagerImpl(towerName);
        this.towerName = towerName;
        this.power = power;
        this.price = price;
        this.position = position;
        this.hittingRange = new Position(5,5);
    }

    @Override
    public boolean upgradable(Integer playerMoney) {
        return playerMoney - this.price >= 0;
    }

    @Override
    public void update(){
        this.towerSpriteManager.upgrade(this.towerName);
        this.power += 2;
        this.hittingRange = new Position(hittingRange.getX()+5,hittingRange.getY()+5);
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
    public Integer getUpgradePrice(){
        return this.price+50;
    }

    @Override
    public BufferedImage getTowerSprite() {
        return towerSpriteManager.getTowerSpriteList().get(0);
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.ofNullable(this.position);
    }

    @Override
    public String getName() {
        return this.towerName;
    }

    @Override
    public void setPosition(double x, double y) {
        this.position = new Position(x,y);
    }

    public void setHittingRange(double x,double y){
        this.hittingRange = new Position(x,y);
    }

    public void setPower(Integer power){
        this.power = power;
    }

    @Override
    public Position getHittingRange(){
        return this.hittingRange;
    }

    public Integer getPower(){
        return this.power;
    }

    @Override
    public TowerSpriteManager getTowerSpriteManager(){
        return this.towerSpriteManager;
    }
}
