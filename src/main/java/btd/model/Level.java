package btd.model;

import java.util.ArrayList;

/**
 * Represents a level in the tower defense game, which contains information about the waves of bloons that will spawn.
 */
public interface Level {

    /**
     * Returns the current wave of bloons for this level.
     *
     * @return The current wave of bloons.
     */
    public Wave getWave();
}

