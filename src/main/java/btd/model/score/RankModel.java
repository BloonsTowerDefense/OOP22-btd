package btd.model.score;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class RankModel{
    private Map<Integer, String> rank;
    private final static int LIMIT_SCORE = 5;
    private final String fileName = "/score/score.txt";

    public RankModel(){
        this.rank = new TreeMap<>(Collections.reverseOrder());
        File test = new File(fileName);
        if(test.exists() && test.length() > 0){
            System.out.println("Leggo il file e carico rank");
            this.rank = readRankFromFile();
        }
    }

    public void addScore(String user, Integer score){
        this.rank.put(score, user);
        if(this.rank.size() > LIMIT_SCORE){
            limit();
        }
        saveRankToFile();
    }

    private void limit(){
        Iterator<Entry<Integer, String>> it = this.rank.entrySet().iterator();
        for(int i = 0; i <= LIMIT_SCORE; i++){
            it.next();
        }
        it.remove();
    }

    public Map<Integer, String> getRank(){
        this.rank = readRankFromFile();
        return this.rank;
    }

    public void saveRankToFile(){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this.rank);
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeMap<Integer, String> readRankFromFile(){
        TreeMap<Integer, String> ret = null;
        try {
            //FileInputStream fileIn = new FileInputStream(fileName);
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            ObjectInputStream objIn = new ObjectInputStream(inputStream);
            ret = (TreeMap<Integer, String>) objIn.readObject();
            objIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}