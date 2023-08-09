package btd.model.score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import btd.utils.RankElement;

/**
 * This class represents a model for managing score.
 * The ranked score contains elements of type {@link RankElement}.
 */
public class RankModel{
    private Map<String, List<RankElement>> rank;
    private final static int LIMIT_SCORE = 5;
    private final File file;
    private static RankModel rankModelIstance;

    /**
     * Standard constructor for RankModel.
     * It loads the score from memory only if the file exists and is not empty.
     */
    private RankModel(){
        this.rank = new HashMap<>();
        this.file = new File("./scoreBTD.txt");
        if(!this.file.exists()){
            try {
                if(this.file.createNewFile()){
                    System.out.println("Nuovo file creato");
                    saveRankToFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.file.length() == 0){
            System.out.println("Esiste ma è vuoto");
        } else {
            System.out.println("Esiste e non è vuoto");
            this.rank = readRankFromFile();
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
     * The addition is followed by ranking ordering and saving to file every time.
     *
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(String mapName, String user, Integer score){
        if(!this.rank.containsKey(mapName)) {
            this.rank.put(mapName, new ArrayList<>());
        }
        List<RankElement> tmp = this.rank.get(mapName);
        tmp.add(new RankElement(user, score));
        orderRank(tmp);
        if(tmp.size() > LIMIT_SCORE){
            limit(tmp);
        }
        this.rank.put(mapName, tmp);
        saveRankToFile();
    }

    private void limit(List<RankElement> toLimit){
        Iterator<RankElement> it = toLimit.iterator();
        for(int i = 0; i <= LIMIT_SCORE; i++){
            it.next();
        }
        it.remove();
    }

    /**
     * Returns the current ranking.
     * The ranking is always read from the file, ordered, and then returned to be sure that the returned value is updated.
     *
     * @return a copy of the current ranking as a list of {@link RankElement} elements.
     */
    public HashMap<String, List<RankElement>> getRank(){
        this.rank = readRankFromFile();
        /*if(this.rank != null){
            orderRank();
        }*/
        return new HashMap<>(this.rank);
    }

    private void saveRankToFile(){
        //orderRank();
        try {
            FileOutputStream fileOut = new FileOutputStream(this.file);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(this.rank);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, List<RankElement>> readRankFromFile(){
        try {
            ObjectInputStream oos = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(this.file.getPath())));
            return (HashMap<String, List<RankElement>>) oos.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private void orderRank(List<RankElement> toOrder){
        Collections.sort(toOrder, (o1, o2) -> o2.getScore().compareTo(o1.getScore()));
    }

    /**
     * Used to check if there is at least one score in the list.
     *
     * @return 0 only if the ranking is empty or not initialized yet, otherwise it returns 1.
     */
    public int getRankDimension(){
        if(this.rank != null){
            return (this.rank.size() == 0 || this.rank == null ? 0 :  1);
        } else {
            return 0;
        }
    }

}