package btd.controller.score;

import java.util.Map;

import btd.model.score.RankModel;


public class RankController {
    private RankModel model;

    public RankController(final RankModel mod){
        this.model = mod;
    }

    public void addScore(String user, Integer score){
        this.model.addScore(user, score);
    }

    public Map<Integer, String> getRank(){
        return this.model.getRank();
    }

    public void saveRank(){
        this.model.saveRankToFile();
    }

    public void readRank(){
        this.model.readRankFromFile();
    }
}
