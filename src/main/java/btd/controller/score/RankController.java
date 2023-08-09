package btd.controller.score;


import java.util.HashMap;
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
     * @param mapName the name of the map palyed, it's used as key in the HashMap.
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(String mapName, String user, Integer score){
        this.model.addScore(mapName, user, score);
    }

    /**
     * Returns the current rank.
     *
     * @return an HashMap<String, List<RankElement>> representing the current rank.
     */
    public HashMap<String, List<RankElement>> getRank(){
        return this.model.getRank();
    }
}
