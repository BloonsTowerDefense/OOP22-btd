package btd.model;

import btd.model.entity.Bloon;

import java.util.ArrayList;
import java.util.List;

public interface Wave {

    public List<Bloon> getBloons();

    public boolean isOver();

    public void removeBloon(Bloon bloon);

}
