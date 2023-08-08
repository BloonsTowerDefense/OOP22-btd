package btd.utils;

import java.io.Serializable;

/**
 * This class represents an element in a ranking system, it contains
 * information about a user and its associated score.
 */
public class RankElement implements Serializable {

    private String user;
    private Integer score;

    /**
     * Standard constructor for RankElement.
     *
     * @param user  name of the user associated with the score.
     * @param score score value associated with the user.
     */
    public RankElement(final String user, final Integer score) {
        this.user = user;
        this.score = score;
    }

     /**
     * Returns the user name associated with the RankElement.
     *
     * @return user name.
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns the score associated with the RankElement.
     *
     * @return score value.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Returns a string that rapresents the RankElement.
     *
     * @return a string that contains the user name and the score.
     */
    @Override
    public String toString() {
        return "RankElement [user=" + user + ", score=" + score + "]";
    }
}
