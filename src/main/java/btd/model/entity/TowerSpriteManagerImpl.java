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
        towerResourceMap.put("blackAdam", "/resources/towers/blackAdam/");
        towerResourceMap.put("deadColossus", "/resources/towers/deadColossus/");
        towerResourceMap.put("voldelife", "/resources/towers/voldelife/");
        towerResourceMap.put("powerEnhancer", "/resources/towers/powerEnhancer/");
        towerResourceMap.put("rangeEnhancer", "/resources/towers/rangeEnhancer/");
    }

    private void setTowerSprites(String towerName, Integer upgradeNumber) {
        spriteList.clear();
        String towerPath = towerResourceMap.get(towerName);
        if (towerPath != null) {
            for (int i = 0; i < 3; i++) {
                try {
                    spriteList.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(towerPath + "Upgrade" + upgradeNumber + "/sprite" + i))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Unknown tower name");
        }
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
