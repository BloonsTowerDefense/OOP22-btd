package btd.model;

import btd.model.entity.Bloon;

import java.util.ArrayList;

public interface Wave {

    public ArrayList<Bloon> getBloons();

    public boolean isOver();

    public void nextBloons();
}
