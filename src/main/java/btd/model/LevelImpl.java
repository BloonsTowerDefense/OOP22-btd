package btd.model;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.BloonType;
import btd.model.map.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelImpl implements Level{
    private int round;
    private int difficultyMultiplier;
    private Random rand;
    private boolean waveInProgress;

    private Path path;

    public LevelImpl (String difficulty) {
        this.round = 1;
        this.rand = new Random();
        this.waveInProgress = false;
        if (difficulty.equals("facile")) {
            this.difficultyMultiplier = 1;
        } else if (difficulty.equals("normale")) {
            this.difficultyMultiplier = 2;
        } else {
            this.difficultyMultiplier = 3;
        }
    }
    @Override
    public Wave getWave() {
        if (waveInProgress) {
            return null;
        }
        List<Bloon> bloons = new ArrayList<>();
        int numBloons = rand.nextInt(10) + round * difficultyMultiplier;
        for (int i=0; i<numBloons; i++) {
            int bloonType = i % 3;
            int bloonHealth;
            Bloon b = null;
            if (bloonType == 0) {
                bloonHealth = 10 + round;
                b = new BloonImpl(BloonType.RED_BLOON);
                b.setPath(path);
            } else if (bloonType == 1) {
                bloonHealth = 20 + round;
            } else {
                bloonHealth = 30 + round;
            }
            bloons.add(b);
        }
        round++;
        waveInProgress = true;
        return new WaveImpl(bloons);
    }

    public void waveFinished(){
        waveInProgress = false;
    }

    public int getRound() {
        return this.round;
    }

    public void setPath(final Path path) {
        this.path = path;
    }
}
