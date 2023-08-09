package btd.model.entity;

import btd.utils.BloonValues;
import btd.view.ItemType;
import btd.view.Resources;

import java.awt.*;

/**
 * An enumeration representing different types of Bloons in the tower defense game.
 * Each BloonType specifies its attributes such as speed, health, money, and associated image.
 */
public enum BloonType {

    /**
     * Red bloon type.
     */
    RED_BLOON("red_bloon",
            BloonValues.RED_BLOON_SPEED,
            BloonValues.RED_BLOON_HEALTH,
            BloonValues.RED_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.RED_BLOON)),

    /**
     * Blue bloon type.
     */
    BLUE_BLOON("blue_bloon",
            BloonValues.BLUE_BLOON_SPEED,
            BloonValues.BLUE_BLOON_HEALTH,
            BloonValues.BLUE_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.BLUE_BLOON)),

    /**
     * Black bloon type.
     */
    BLACK_BLOON("black_bloon",
            BloonValues.BLACK_BLOON_SPEED,
            BloonValues.BLACK_BLOON_HEALTH,
            BloonValues.BLACK_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.BLACK_BLOON));

    private double speed;
    private int health, money;

    private Image image;

    /**
     * Constructs a BloonType enum with specified attributes.
     *
     * @param name   The name of the BloonType.
     * @param speed  The speed of the bloon.
     * @param health The health of the bloon.
     * @param money  The amount of money earned by defeating the bloon.
     * @param img    The image associated with the BloonType.
     */
    BloonType(String name, double speed, int health, int money, Image img) {
        this.speed = speed;
        this.health = health;
        this.money = money;
        this.image = img;
    }

    /**
     * Returns the speed attribute of the BloonType.
     *
     * @return The speed of the bloon.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the health attribute of the BloonType.
     *
     * @return The health of the bloon.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the money attribute of the BloonType.
     *
     * @return The amount of money earned by defeating the bloon.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Returns the associated image of the BloonType.
     *
     * @return The image representing the bloon.
     */
    public Image getImage() {
        return image;
    }

}

