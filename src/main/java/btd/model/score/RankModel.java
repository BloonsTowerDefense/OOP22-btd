package btd.model.score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import btd.utils.RankElement;

/**
 * This class represents a model for managing score.
 * The ranked score contains elements of type {@link RankElement}.
 */
public class RankModel{
    private List<RankElement> rank;
    private final static int LIMIT_SCORE = 5;
    //private final String fileName = "src//main//resources//score//score.txt";
    private final String fileName = "/score/score.txt";

    /**
     * Standard constructor for RankModel.
     * It loads the score from memory only if the file exists and is not empty.
     */
    public RankModel(){
        this.rank = new ArrayList<>();
        InputStream fileIn = getClass().getResourceAsStream(fileName);
        if(fileIn != null){
            this.rank = readRankFromFile();
        }
        /*File test = new File("src//main//resources//score//score.txt");
        if(test.exists() && (test.length() > 0)){
            System.out.println("condizione file vera");
            this.rank = readRankFromFile();
            
        */ else {
            System.out.println("\n\nFile non trovato\n\n");
        }
    }

    /**
     * Adds a score to rank with the provided user name and score.
     * The addition is followed by ranking ordering and saving to file every time.
     *
     * @param user  the user name associated with the score.
     * @param score the score to add to rank.
     */
    public void addScore(String user, Integer score){
        this.rank.add(new RankElement(user, score));
        orderRank();
        if(this.rank.size() > LIMIT_SCORE){
            limit();
        }
        saveRankToFile();
    }

    private void limit(){
        Iterator<RankElement> it = this.rank.iterator();
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
    public List<RankElement> getRank(){
        this.rank = readRankFromFile();
        if(this.rank != null){
            orderRank();
        }
        return new ArrayList<>(this.rank);
    }

    private void saveRankToFile(){
        System.out.println("Salvo il punteggio");
        orderRank();
        try {
            //OutputStream fileOut = new FileOutputStream(fileName);
            OutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this.rank);
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<RankElement> readRankFromFile(){
        ArrayList<RankElement> ret = null;
        try {
            //FileInputStream fileIn = new FileInputStream(fileName);
            InputStream fileIn = getClass().getResourceAsStream(fileName);
            if(fileIn!=null){
                System.out.println("\n\n\n\n\n\nFileIn non è null\n\n\n\n");
            } else {
                System.out.println("\n\n\n\n\n\nFileIn è null\n\n\n\n");
            }
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            //if(objIn instanceof List){
            ret = (ArrayList<RankElement>) objIn.readObject();
            //}
            objIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void orderRank(){
        Collections.sort(this.rank, (o1, o2) -> o2.getScore().compareTo(o1.getScore()));
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