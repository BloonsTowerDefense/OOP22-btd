package btd.model;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.BloonType;
import btd.model.map.Path;
import btd.model.map.PathImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an implementation of the Level interface in the tower defense game.
 * A LevelImpl instance defines a level in the game, including the progression of waves of bloons that spawn.
 */
public class LevelImpl implements Level {

    private int round;
    private int difficultyMultiplier;
    private Random rand;
    private boolean waveInProgress;
    private Path path;
    private long timeBetweenWaves = 10000; // 10 seconds between waves

    /**
     * Constructs a LevelImpl object with the specified difficulty and path.
     *
     * @param difficulty The difficulty level of the level (e.g., "facile", "normale", "difficile").
     * @param path       The path that bloons will follow in this level.
     */
    public LevelImpl(String difficulty, Path path) {
        this.path = path;
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

    /**
     * Generates and returns the next wave of bloons for this level.
     *
     * @return A Wave object representing the next wave of bloons.
     */
    @Override
    public Wave getWave() {
        if (waveInProgress) {
            return null;
        }
        List<Bloon> bloons = new ArrayList<>();
        int numBloons = rand.nextInt(10) + round * difficultyMultiplier;
        for (int i = 0; i < numBloons; i++) {
            int bloonType = i % 3;
            int bloonHealth;
            Bloon b = null;
            if (bloonType == 0) {
                bloonHealth = 10 + round;
                b = new BloonImpl(BloonType.RED_BLOON, this.path);
                b.setPath(path);
                b.setPosition(path.getSpawnPosition().getX(), this.path.getSpawnPosition().getY());
            } else if (bloonType == 1) {
                bloonHealth = 20 + round;
                b = new BloonImpl(BloonType.BLUE_BLOON, path);
                b.setPath(path);
                b.setPosition(path.getSpawnPosition().getX(), path.getSpawnPosition().getY());
            } else {
                bloonHealth = 30 + round;
                b = new BloonImpl(BloonType.BLACK_BLOON, path);
                b.setPath(path);
                b.setPosition(path.getSpawnPosition().getX(), path.getSpawnPosition().getY());
            }
            bloons.add(b);
        }
        round++;
        waveInProgress = true;
        return new WaveImpl(bloons);
    }

    /**
     * Marks the current wave as finished.
     */
    public void waveFinished() {
        waveInProgress = false;
    }

    /**
     * Returns the current round of the level.
     *
     * @return The current round of the level.
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Returns the time interval between waves.
     *
     * @return The time interval between waves in milliseconds.
     */
    public long getTimeBetweenWaves() {
        return this.timeBetweenWaves;
    }
}

