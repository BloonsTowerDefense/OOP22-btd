package btd.model.entity;

import btd.model.map.Path;
import btd.utils.BloonValues;
import btd.utils.ImagePath;

public enum BloonType {

    RED_BLOON("red_bloon",
            BloonValues.RED_BLOON_SPEED,
            BloonValues.RED_BLOON_HEALTH,
            BloonValues.RED_BLOON_MONEY,
            ImagePath.RED_BLOON);

    private double speed;
    private int health, money;

    private ImagePath imagePath;

    BloonType(String name, double speed, int health, int money, ImagePath imagePath) {
        this.speed = speed;
        this.health = health;
        this.money = money;
        this.imagePath = imagePath;
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

    public ImagePath getImagePath() {
        return imagePath;
    }

}
