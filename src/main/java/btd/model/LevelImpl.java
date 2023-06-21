package btd.model;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelImpl implements Level{
    private int round;
    private int difficultyMultiplier;
    private Random rand;

    public LevelImpl (String difficulty) {
        this.round = 1;
        this.rand = new Random();
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
        List<Bloon> bloons = new ArrayList<>();
        int numBloons = rand.nextInt(10) + round * difficultyMultiplier;
        for (int i=0; i<numBloons; i++) {
            int bloonType = i % 3;
            int bloonHealth;
            if (bloonType == 0) {
                bloonHealth = 10 + round;
            } else if (bloonType == 1) {
                bloonHealth = 20 + round;
            } else {
                bloonHealth = 30 + round;
            }
            bloons.add(new BloonImpl(bloonType,bloonHealth));
        }
        round++;
        return new WaveImpl(bloons);
    }

    public int getRound() {
        return this.round;
    }
}
