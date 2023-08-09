package btd.model.score;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import btd.utils.RankElement;

/**
 * This class represents a model for managing score, provides methods for adding scores
 * and getting the rank.
 * The ranked score contains elements of type {@link RankElement}.
 */
public class RankModel {
    private List<RankElement> rank;
    private final int LIMIT_SCORE = 5;
    private final File file;
    private static RankModel rankModelIstance;

    /**
     * Standard constructor for RankModel.
     * It loads the score from memory only if the file exists and is not empty.
     */
    private RankModel() {
        this.rank = new ArrayList<>();
        this.file = new File("./scoreBTD.txt");
        if (!this.file.exists()) {
            try {
                if (this.file.createNewFile()) {
                    System.out.println("Nuovo file creato");
                    saveRankToFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.file.length() == 0) {
            System.out.println("Esiste ma è vuoto");
        } else {
            System.out.println("Esiste e non è vuoto");
            this.rank.addAll(readRankFromFile());
        }
    }

    public static synchronized RankModel getRankModelIstance() {
        if(rankModelIstance == null){
            rankModelIstance = new RankModel();
        }
        return rankModelIstance;
    }

    /**
     * Adds a score to rank with the provided user name and score.
     * The addition is followed by ranking ordering, limiting to 5 scores and saving to file every time.
     *
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(final String user, final Integer score) {
        this.rank.add(new RankElement(user, score));
        orderRank();
        if (this.rank.size() > LIMIT_SCORE) {
            limit();
        }
        saveRankToFile();
    }

    /**
     * Returns a copy of the current ranking.
     * The ranking is always read from the file, ordered, and then returned to be sure that the returned value is updated.
     *
     * @return a copy of the current ranking as a list of {@link RankElement} elements.
     */
    public List<RankElement> getRank() {
        this.rank.clear();
        this.rank.addAll(readRankFromFile());
        if (this.rank != null) {
            orderRank();
        }
        return new ArrayList<>(this.rank);
    }

    /**
     * Used to check if there is at least one score in the list.
     *
     * @return 0 only if the ranking is empty or not initialized yet, otherwise it returns 1.
     */
    public int getRankDimension() {
        if (this.rank != null) {
            return (this.rank.size() == 0 || this.rank == null ? 0 :  1);
        } else {
            return 0;
        }
    }

    /**
     * Returns the score limit.
     *
     * @return score limit.
     */
    public int getLimit() {
        return this.LIMIT_SCORE;
    }

    public void deleteRank(){
        if(this.file != null){
            this.file.delete();
        }
        
    }

    private void limit() {
        Iterator<RankElement> it = this.rank.iterator();
        for (int i = 0; i <= LIMIT_SCORE; i++) {
            it.next();
        }
        it.remove();
    }

    private void saveRankToFile() {
        orderRank();
        try {
            FileOutputStream fileOut = new FileOutputStream(this.file);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this.rank);
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<RankElement> readRankFromFile() {
        try (ObjectInputStream objIn = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(this.file.getPath())))){ 
            return (List<RankElement>) objIn.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void orderRank() {
        Collections.sort(this.rank, (o1, o2) -> o2.getScore().compareTo(o1.getScore()));
    }
}
