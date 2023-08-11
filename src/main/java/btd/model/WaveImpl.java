package btd.model;

import btd.model.entity.Bloon;

import java.util.List;

/**
 * Represents an implementation of the Wave interface in the tower defense game.
 * A WaveImpl instance defines a wave of bloons that spawn and progress through the game.
 */
public class WaveImpl implements Wave {

    private final List<Bloon> bloons;

    /**
     * Constructs a WaveImpl object with the specified list of bloons.
     *
     * @param bloons The list of bloons present in the wave.
     */
    public WaveImpl(final List<Bloon> bloons) {
        this.bloons = bloons;
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
}

