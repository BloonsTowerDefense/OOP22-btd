package btd.score;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import btd.model.score.RankModel;
import btd.utils.RankElement;

import java.util.List;

public class RankModelTest {

    private RankModel rankModel;

    @BeforeEach
    public void setUp() {
        rankModel = new RankModel();
    }

    @AfterEach
    public void delete() {
        rankModel.deleteRank();
    }

    @Test
    public void testAddScore() {
        rankModel.addScore("User1", 100);
        rankModel.addScore("User2", 150);
        
        List<RankElement> rank = rankModel.getRank();
        assertEquals(2, rank.size());
        
        assertEquals("User2", rank.get(0).getUser());
        assertEquals(150, rank.get(0).getScore());
        
        assertEquals("User1", rank.get(1).getUser());
        assertEquals(100, rank.get(1).getScore());
    }

    @Test
    public void testLimit() {
        rankModel.addScore("User1", 100);
        rankModel.addScore("User2", 150);
        rankModel.addScore("User3", 75);
        rankModel.addScore("User4", 200);
        rankModel.addScore("User5", 50);
        rankModel.addScore("User6", 180);

        List<RankElement> rank = rankModel.getRank();
        assertEquals(5, rank.size());

        assertEquals("User4", rank.get(0).getUser());
        assertEquals("User6", rank.get(1).getUser());
        assertEquals("User2", rank.get(2).getUser());
        assertEquals("User1", rank.get(3).getUser());
        assertEquals("User3", rank.get(4).getUser());
    }

    @Test
    public void testGetRankDimension() {
        assertEquals(0, rankModel.getRankDimension());

        rankModel.addScore("User1", 100);
        assertEquals(1, rankModel.getRankDimension());

        rankModel.addScore("User2", 150);
        assertEquals(1, rankModel.getRankDimension());
    }

    @Test
    public void testGetLimit() {
        assertEquals(5, rankModel.getLimit());
    }

    @Test
    public void testEmptyRank() {
        List<RankElement> rank = rankModel.getRank();
        assertEquals(0, rank.size());
    }
}
