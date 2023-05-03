package btd.model.map;

import java.util.ArrayList;
import java.util.List;

public class MapManagerImpl implements MapManager{

    private List<MapElement> mapElementList;
    private int[][] mapNum;
    private MapLoader mapLoader;

    public MapManagerImpl(){
        this.mapElementList = new ArrayList<>();
        //this.mapNum da implementare quando saprò dim mappa
        this.mapLoader = new MapLoaderImpl();
        loadMapImage();
        setMap();
    }

    private void loadMapImage(){
        try {
            /*da implementare quando saprò la quantità di suoli diversi da usare */
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void setMap(){
        this.mapNum = this.mapLoader.loadMap(null); //il nome della mappa sarà preso dal game manager
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public int[][] getMapNum() {
        return this.mapNum;
    }
    
}
