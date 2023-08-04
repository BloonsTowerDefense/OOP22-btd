package btd.model.entity;

import btd.model.map.Path;
import btd.utils.BloonValues;
import btd.utils.ImagePath;
import btd.view.ItemType;
import btd.view.Resources;

import java.awt.*;

public enum BloonType {

    RED_BLOON("red_bloon",
            BloonValues.RED_BLOON_SPEED,
            BloonValues.RED_BLOON_HEALTH,
            BloonValues.RED_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.RED_BLOON)),
    BLUE_BLOON("blue_bloon",
            BloonValues.BLUE_BLOON_SPEED,
            BloonValues.BLUE_BLOON_HEALTH,
            BloonValues.BLUE_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.BLUE_BLOON)),
    BLACK_BLOON("black_bloon",
            BloonValues.BLACK_BLOON_SPEED,
            BloonValues.BLACK_BLOON_HEALTH,
            BloonValues.BLACK_BLOON_MONEY,
            Resources.getRes().getTextures(ItemType.BLACK_BLOON));
    private double speed;
    private int health, money;

    private Image image;

    BloonType(String name, double speed, int health, int money, Image img) {
        this.speed = speed;
        this.health = health;
        this.money = money;
        this.image = img;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMoney() {
        return this.money;
    }

    public Image getImage() {
        return image;
    }

}
