package btd.model.entity;

import btd.utils.Position;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Implementation of towers of type Helping. Helping Towers are towers used to help shooting
 * towers to enhance their shooting range or their power.
 * */
public class HelpingTower implements Tower {

  private static final int FUNCTION_FACTOR = 5;

  private static final int RANGE_FACTOR = 10;

  private static final int PRICE_FACTOR = 50;

  private final String towerName;

  private final String function;

  private final Integer price;

  private Integer functionFactor;

  private Position position;

  private Position hittingRange;

  private final TowerSpriteManager towerSpriteManager;

  /**
   * Builds a Helping Tower with the specified name, function, price, position.
   *
   * @param towerName The name of the tower.
   * @param  function The function of the tower.
   * @param price The price of the tower.
   * @param position The position of the tower.
   **/
  public HelpingTower(final String towerName, final String function, final Integer price, final Position position) {
    this.towerSpriteManager = new TowerSpriteManagerImpl(towerName);
    this.towerName = towerName;
    this.price = price;
    this.function = function;
    this.functionFactor = 10;
    this.position = position;
    this.hittingRange = new Position(this.functionFactor, this.functionFactor);
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
    this.hittingRange = new Position(hittingRange.getX() + RANGE_FACTOR, hittingRange.getY() + RANGE_FACTOR);
    this.functionFactor += FUNCTION_FACTOR;
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
  public Position getHittingRange() {
    return this.hittingRange;
  }

  /**
   * Method used to get the function factor of the helping tower.
   * The function factor is the analog of the power for shooting tower,
   * instead the helping tower cannot shoot, so it will enhance only. The
   * function factor indicates how much to enhance
   *
   * @return returns the Integer of the enhancing factor
   **/
  public Integer getFunctionFactor() {
    return this.functionFactor;
  }

    /**
     * @{inheritdoc} .
     **/
  @Override
  public Optional<Position> getPosition() {
    return Optional.ofNullable(this.position);
  }

    /**
     * @{inheritdoc} .
     **/
  @Override
  public String getName() {
    return this.towerName;
  }

  /**
  * Method used to return the function type of the helping tower.
  *
  * @return returns the String of the function.
  **/
  public String getFunction() {
    return this.function;
  }

    /**
     * @{inheritdoc} .
     **/
  @Override
  public void setPosition(final double x, final double y) {
    this.position = new Position(x, y);
  }

  /**
   * @{inheritdoc} .
   */
  @Override
  public BufferedImage getTowerSprite() {
    return towerSpriteManager.getTowerSpriteList().get(0);
  }

    /**
     * @{inheritdoc} .
     */
  @Override
  public TowerSpriteManager getTowerSpriteManager() {
    return this.towerSpriteManager;
  }
}
