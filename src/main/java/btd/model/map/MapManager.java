package btd.model.map;
import java.awt.Graphics2D;
public interface MapManager {
    public void draw(Graphics2D graphics2d);
    public int[][] getMapNum();
    public Path getBloonPath();
    public String getMapName();
    public Boolean canPlace(int x, int y);
    //TODO getMap();
}
