package btd.controller.score;

import java.util.List;

import btd.model.score.RankModel;
import btd.utils.RankElement;

/**
 * This class acts as a connection between {@link RankView} and {@link RankModel}.
 */
public class RankController {
    private RankModel model;

    /**
     * Standard constructs for RankController instance with a given RankModel.
     *
     * @param mod The RankModel instance to associate with the controller.
     */
    public RankController(final RankModel mod){
        this.model = mod;
    }

    /**
     * Adds a score to the ranking system.
     *
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(String user, Integer score){
        this.model.addScore(user, score);
    }

    /**
     * Returns the current rank.
     *
     * @return a list of {@link RankElement} objects representing the current rank.
     */
    public List<RankElement> getRank(){
        return this.model.getRank();
    }

    /**
     * Returns the dimension of the current rank.
     *
     * @return the dimension of rank, which is the number of saved scores.
     */
    public int getRankDimension(){
        return this.model.getRankDimension();
    }
}
