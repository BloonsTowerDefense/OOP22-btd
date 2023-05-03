package btd.model;

public interface Tower extends Entity{

    void create();

    boolean upgradable();

    void upgrade();

    void changeApperance();

    void sell();

}
