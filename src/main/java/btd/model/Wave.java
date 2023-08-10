package btd.model;

import btd.model.entity.Bloon;

import java.util.List;

/**
 * Represents a wave of bloons in the tower defense game.
 * A Wave instance contains a collection of bloons and provides methods to interact with the wave.
 */
public interface Wave {

    /**
     * Returns a list of bloons present in the wave.
     *
     * @return A List containing the bloons in the wave.
     */
    List<Bloon> getBloons();

    /**
     * Checks if the wave is over (all bloons have been defeated or reached their destination).
     *
     * @return {@code true} if the wave is over, {@code false} otherwise.
     */
    boolean isOver();

    /**
     * Removes the specified bloon from the wave.
     *
     * @param bloon The bloon to be removed from the wave.
     */
    void removeBloon(Bloon bloon);
}

