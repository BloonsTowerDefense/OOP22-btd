package btd.model;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.BloonType;
import btd.model.map.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an implementation of the Level interface in the tower defense game.
 * A LevelImpl instance defines a level in the game, including the progression of waves of bloons that spawn.
 */
public class LevelImpl implements Level {
    private int round;
    private final int difficultyMultiplier;
    private final Random rand;
    private boolean waveInProgress;
    private final Path path;

    /**
     * Constructs a LevelImpl object with the specified difficulty and path.
     *
     * @param difficulty The difficulty level of the level (e.g., "facile", "normale", "difficile").
     * @param path       The path that bloons will follow in this level.
     */
    public LevelImpl(final String difficulty, final Path path) {
        this.path = path;
        this.round = 1;
        this.rand = new Random();
        this.waveInProgress = false;
        if ("facile".equals(difficulty)) {
            this.difficultyMultiplier = 1;
        } else if ("normale".equals(difficulty)) {
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
        final List<Bloon> bloons = new ArrayList<>();
        final int numBloons = rand.nextInt(3, 10) + round * difficultyMultiplier;
        for (int i = 0; i < numBloons; i++) {
            final int bloonType = i % 3;
            Bloon b = null;
            if (bloonType == 0) {
                b = new BloonImpl(BloonType.RED_BLOON, this.path);
                b.setPath(path);
                b.setHealth(b.getType().getHealth() + round);
                b.setPosition(path.getSpawnPosition().getX(), this.path.getSpawnPosition().getY());
            } else if (bloonType == 1) {
                b = new BloonImpl(BloonType.BLUE_BLOON, path);
                b.setPath(path);
                b.setHealth(b.getType().getHealth() + round);
                b.setPosition(path.getSpawnPosition().getX(), path.getSpawnPosition().getY());
            } else {
                b = new BloonImpl(BloonType.BLACK_BLOON, path);
                b.setPath(path);
                b.setHealth(b.getType().getHealth() + round);
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
}

