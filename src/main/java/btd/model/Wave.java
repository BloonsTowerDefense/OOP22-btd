package btd.model;

import java.util.ArrayList;

public interface Wave {

    public ArrayList<Bloon> getBloons();

    public boolean isOver();

    public void nextBloons();
}
