package btd.model.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TowerSpriteManagerImpl implements TowerSpriteManager {

    private final List<BufferedImage> spriteList;
    private Map<String, String> towerResourceMap;

    public TowerSpriteManagerImpl(String towerName) {
        spriteList = new ArrayList<>();
        towerResourceMap();
        setTowerSprites(towerName, 0);
    }

    @Override
    public void towerResourceMap() {
        towerResourceMap = new HashMap<>();
        towerResourceMap.put("blackAdam", "/towers/blackAdam/");
        towerResourceMap.put("deadColossus", "/towers/deadColossus/");
        towerResourceMap.put("voldelife", "/towers/voldelife/");
        towerResourceMap.put("powerEnhancer", "/towers/powerEnhancer/");
        towerResourceMap.put("rangeEnhancer", "/towers/rangeEnhancer/");
    }

    private void setTowerSprites(String towerName, Integer upgradeNumber) {
        spriteList.clear();
        String towerPath = towerResourceMap.get(towerName);
        if (towerPath != null) {
            for (int i = 0; i < 2; i++) {
                try {
                    spriteList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(towerPath + "Upgrade" + upgradeNumber + "/sprite" + i + ".png"))));
                } catch (IOException e) {
                    System.out.println("No more sprites for tower");
                }
            }
        } else {
            System.out.println("Unknown tower name");
        }
    }

    public List<BufferedImage> getUpgradeSprites(String towerName,Integer upgradeNumber){
        List<BufferedImage> sprites = new ArrayList<>();
        String towerPath = towerResourceMap.get(towerName);
        if(towerPath != null){
            for (int i = 0; i < 2; i++){
                try {
                    sprites.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(towerPath+ "Upgrade"+upgradeNumber+"/sprite"+ i +".png"))));
                }catch (IOException e){
                    System.out.println("No more sprites");
                }
            }
        }
        return sprites;
    }

    @Override
    public void upgrade(String towerName) {
        setTowerSprites(towerName, 1);
    }

    @Override
    public List<BufferedImage> getTowerSpriteList() {
        return spriteList;
    }
}
