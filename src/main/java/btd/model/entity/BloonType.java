package btd.model.entity;

import btd.model.map.Path;
import btd.utils.BloonValues;

public enum BloonType {

    RED_BLOON("red_bloon",
            BloonValues.RED_BLOON_SPEED,
            BloonValues.RED_BLOON_HEALTH,
            BloonValues.RED_BLOON_MONEY);

    private double speed;
    private int health, money;

    BloonType(String name, double speed, int health, int money) {
        this.speed = speed;
        this.health = health;
        this.money = money;
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


}
