package btd.model;

import btd.model.entity.Bloon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an implementation of the Wave interface in the tower defense game.
 * A WaveImpl instance defines a wave of bloons that spawn and progress through the game.
 */
public class WaveImpl implements Wave {

    private List<Bloon> bloons;
    private String bloonType;
    private int numBloons;

    private long delay;

    private String difficulty;

    private Random random = new Random();

    /**
     * Constructs a WaveImpl object with the specified list of bloons.
     *
     * @param bloons The list of bloons present in the wave.
     */
    public WaveImpl(final List<Bloon> bloons) {
        this.bloons = bloons;
        this.delay = 1000; // Default delay of 1000 milliseconds between bloon spawns
    }

    /**
     * Returns a list containing the bloons in the wave.
     *
     * @return A List containing the bloons in the wave.
     */
    @Override
    public List<Bloon> getBloons() {
        return List.copyOf(this.bloons);
    }

    /**
     * Checks if the wave is over (all bloons have been defeated or reached their destination).
     *
     * @return {@code true} if the wave is over, {@code false} otherwise.
     */
    @Override
    public boolean isOver() {
        return this.bloons.size() == 0;
    }

    /**
     * Removes the specified bloon from the wave.
     *
     * @param bloon The bloon to be removed from the wave.
     */
    @Override
    public void removeBloon(Bloon bloon) {
        bloons.remove(bloon);
    }
}

