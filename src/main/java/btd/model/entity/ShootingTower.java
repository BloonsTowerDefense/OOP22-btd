package btd.model.entity;

import btd.utils.Position;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Implementation of towers of type Shooting. Shooting Towers are the towers that shoot to
 * kill the bloons.
 * */
public class ShootingTower implements Tower {

  private static final int UPGRADE_FACTOR = 2;

  private static final int RANGE_FACTOR = 5;

  private static final int PRICE_FACTOR = 50;


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
  public ShootingTower(final String towerName, final Integer power, final Integer price, final Position position) {
    this.towerSpriteManager = new TowerSpriteManagerImpl(towerName);
    this.towerName = towerName;
    this.power = power;
    this.price = price;
    this.position = position;
    this.hittingRange = new Position(RANGE_FACTOR, RANGE_FACTOR);
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public boolean upgradable(final Integer playerMoney) {
    return playerMoney - this.price >= 0;
  }

  /**
   * @{inheritdoc} .
   **/
  @Override
  public void update() {
    this.towerSpriteManager.upgrade(this.towerName);
    this.power += UPGRADE_FACTOR;
    this.hittingRange = new Position(hittingRange.getX() + RANGE_FACTOR, hittingRange.getY() + RANGE_FACTOR);
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
    return this.price + PRICE_FACTOR;
  }

  /**
  * @{inheritdoc} .
  **/
  @Override
  public BufferedImage getTowerSprite() {
    return towerSpriteManager.getTowerSpriteList().get(0);
  }

  /**
   * @{inheritdoc} .
   */
  @Override
  public Optional<Position> getPosition() {
    return Optional.ofNullable(this.position);
  }

    /**
     * @{inheritdoc} .
     */
  @Override
  public String getName() {
    return this.towerName;
  }

    /**
     * @{inheritdoc} .
     */
  @Override
  public void setPosition(final double x, final double y) {
    this.position = new Position(x, y);
  }

  /**
   * Method used to set the hitting range of the tower.
   * @param x the x-coordinate of the range.
   * @param y the y-coordinate of the range.
   */
  public void setHittingRange(final double x, final double y) {
    this.hittingRange = new Position(x, y);
  }

  /**
   * Method used to set the power of the tower.
   * @param power the power of the tower.
   */
  public void setPower(final Integer power) {
    this.power = power;
  }

  /**
   * @{inheritdoc} .
   */
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

    /**
     * @{inheritdoc} .
     */
  @Override
  public TowerSpriteManager getTowerSpriteManager() {
    return this.towerSpriteManager;
  }
}
