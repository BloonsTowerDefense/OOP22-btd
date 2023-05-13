package btd.model.entity;

import btd.model.entity.Entity;
import btd.utils.Position;

import java.util.List;

public interface Tower extends Entity {

    void create();

    boolean upgradable();

    void upgrade();

    void changeApperance();

    Integer sell();

    Integer getPrice();
}
