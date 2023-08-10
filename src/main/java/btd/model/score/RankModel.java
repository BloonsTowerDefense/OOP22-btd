package btd.model.score;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import btd.model.map.MapManagerImpl;
import btd.utils.RankElement;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class represents a model for managing score.
 * The ranked score contains elements of type {@link RankElement}.
 */
public final class RankModel {
    private static final Logger LOGGER = Logger.getLogger(MapManagerImpl.class.getName());
    private Map<String, List<RankElement>> rank;
    /**
     * The maximum number of score to save in rank.
     */
    public static final int LIMIT_SCORE = 5; 
    private final File file;
    private static RankModel rankModelIstance;

    @SuppressFBWarnings("MS_SHOULD_BE_FINAL")
    /**
     * Private constructor for RankModel.
     * It loads the score from memory only if the file exists and is not empty.
     */
    private RankModel() {
        this.rank = new HashMap<>();
        this.file = new File("./scoreBTD.txt");
        if (!this.file.exists()) {
            try {
                if (this.file.createNewFile()) {
                    saveRankToFile();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "IOException", e);
            }
        } else if (this.file.length() > 0) {
            this.rank = readRankFromFile();
        } 
    }

    /**
     * Returns the singleton instance of RankModel. It creates a new istance only the first time that
     * it's called.
     *
     * @return the instance of RankModel.
     */
    @SuppressFBWarnings("MS")
    public static synchronized RankModel getRankModelIstance() {
        if (rankModelIstance == null) {
            rankModelIstance = new RankModel();
        }
        return rankModelIstance;
    }

    /**
     * Adds a score to rank with the provided user name and score.
     * The addition is followed by ranking ordering and saving to file every time.
     *
     * @param mapName name of the map played, it works as the key in the HashMap.
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(final String mapName, final String user, final Integer score) {
        if (!this.rank.containsKey(mapName)) {
            this.rank.put(mapName, new ArrayList<>());
        }
        final List<RankElement> tmp = this.rank.get(mapName);
        tmp.add(new RankElement(user, score));
        orderRank(tmp);
        if (tmp.size() > LIMIT_SCORE) {
            limit(tmp);
        }
        this.rank.put(mapName, tmp);
        saveRankToFile();
    }

    /**
     * Returns the current ranking.
     * The ranking is always read from the file and then returned to be sure that the returned value is updated.
     *
     * @return a copy of the current ranking as a list of {@link RankElement} elements.
     */
    public Map<String, List<RankElement>> getRank() {
        this.rank = readRankFromFile();
        return new HashMap<>(this.rank);
    }

    /**
     * This method clears the istance of rank. It's used only for testing.
     */
    public void deleteRank() {
        this.rank.clear();
        saveRankToFile();
    }

    /**
     * Returns the limit for the number of scores to be maintained.
     *
     * @return the limit for the number of scores.
     */
    public int getLimit() {
        return this.LIMIT_SCORE;
    } 

    private void saveRankToFile() {
        // orderRank();
        try (FileOutputStream fileOut = new FileOutputStream(this.file);
                ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
            oos.writeObject(this.rank);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, List<RankElement>> readRankFromFile() {
        try (ObjectInputStream oos = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(this.file.getPath())))) {
            return (HashMap<String, List<RankElement>>) oos.readObject();
        } catch (IOException |  ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Exception", e);
        }
        return new HashMap<>();
    }

    private void orderRank(final List<RankElement> toOrder) {
        Collections.sort(toOrder, (o1, o2) -> o2.getScore().compareTo(o1.getScore()));
    }

    private void limit(final List<RankElement> toLimit) {
        final Iterator<RankElement> it = toLimit.iterator();
        for (int i = 0; i <= LIMIT_SCORE; i++) {
            it.next();
        }
        it.remove();
    }
}
