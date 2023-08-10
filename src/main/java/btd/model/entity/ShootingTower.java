package btd.model.entity;

import btd.utils.Position;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Implementation of towers of type Shooting. Shooting Towers are the towers that shoot to
 * kill the bloons.
 * */
public class ShootingTower implements Tower {
  private final String towerName;
  private Integer power;

  private final Integer price;

  private Position position;

  private Position hittingRange;

  private final TowerSpriteManager towerSpriteManager;

  /**
   * Builds the Shooting tower with the specified name, power, price, position.
   *
   * @param towerName The name of the tower.
   * @param power The power of the tower.
   * @param price The price of the tower.
   * @param position The position of the tower.
   **/
  public ShootingTower(String towerName, Integer power, Integer price, Position position) {
    this.towerSpriteManager = new TowerSpriteManagerImpl(towerName);
    this.towerName = towerName;
    this.power = power;
    this.price = price;
    this.position = position;
    this.hittingRange = new Position(5, 5);
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public boolean upgradable(Integer playerMoney) {
    return playerMoney - this.price >= 0;
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public void update() {
    this.towerSpriteManager.upgrade(this.towerName);
    this.power += 2;
    this.hittingRange = new Position(hittingRange.getX() + 5, hittingRange.getY() + 5);
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public Integer getPrice() {
    return this.price;
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public Integer getUpgradePrice() {
    return this.price + 50;
  }

  /**
  * @{inheritdoc} .
  **/
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
    this.position = new Position(x, y);
  }

  public void setHittingRange(double x, double y) {
    this.hittingRange = new Position(x, y);
  }

  public void setPower(Integer power) {
    this.power = power;
  }

  @Override
  public Position getHittingRange() {
    return this.hittingRange;
  }

  /**
  * Method used to get the power of the shooting tower.
  *
  * @return returns the Integer of the power.
  **/
  public Integer getPower() {
    return this.power;
  }

  @Override
  public TowerSpriteManager getTowerSpriteManager() {
    return this.towerSpriteManager;
  }
}
