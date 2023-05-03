package btd.model;

import btd.model.entity.Entity;

public interface Tower extends Entity {

    void create();

    boolean upgradable();

    void upgrade();

    void changeApperance();

    void sell();
}
