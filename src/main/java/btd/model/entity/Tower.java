package btd.model.entity;

import btd.model.entity.Entity;
import btd.utils.Position;

import java.util.List;

public interface Tower extends Entity {

    //IF PLAYER HAS ENOUGH MONEY (playerMoney >= towerPrice) RETURN TRUE, ELSE FALSE
    boolean upgradable(Integer playerMoney);

    void upgrade();

    Integer sell();

    Integer getPrice();

    void setPosition(double x, double y);
}
