package btd.model;

import btd.model.entity.Bloon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveImpl implements Wave{

    private List<Bloon> bloons;
    private String bloonType;
    private int numBloons;

    private long delay;

    private String difficulty;

    private Random random = new Random();

    public void Wave(final List<Bloon> bloons){
        this.bloons = bloons;
        this.delay = 1000;
    }
    @Override
    public List<Bloon> getBloons() {
        return List.copyOf(this.bloons);
    }

    @Override
    public boolean isOver() {
        return this.bloons.size() == 0;
    }

    @Override
    public void nextBloons() {

    }
}
