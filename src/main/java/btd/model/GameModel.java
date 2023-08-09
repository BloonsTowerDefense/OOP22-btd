package btd.model;

import btd.model.entity.*;
import btd.model.map.MapManager;
import btd.model.map.MapManagerImpl;
import btd.model.map.Path;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;


public class GameModel {
    private LevelImpl level;
    private WaveImpl wave;
    private Path path;
    private List<Tower> towers;
    private Player player;
    private boolean waveInProgress;
    private boolean bloonSpawnInProgress;
    private List<Bloon> aliveBloons;

    private List<Bullet> bullets;
    private int bloonsSpawned;
    private long lastSpawnTime;
    private long lastWaveEndTime;
    private final long bloonSpawnInterval = 1500; // 1.5 secondi
    private final long waveInterval = 2000; // 10 secondi
    private String difficulty;
    private MapManager mapManager;
    private int waveSize;
    private int deadBloons;


    public enum GameState {
        PLAYING,
        GAME_OVER,
        VICTORY
    }

    public GameModel() {
        this.towers = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.player = new Player();
        this.waveInProgress = false;
        this.bloonSpawnInProgress = false;
        this.aliveBloons = new ArrayList<>();
        this.lastSpawnTime = 0;
        this.lastWaveEndTime = 0;
        //this.mapManager = new MapManagerImpl("map01");
        //initGame("norm", "map01"); //In questo modo mapManager non è null
    }

    public void startWave() {
        if (!waveInProgress && System.currentTimeMillis() - lastWaveEndTime >= waveInterval) {
            waveInProgress = true;
            bloonSpawnInProgress = true;
            aliveBloons.clear();
            this.wave = (WaveImpl) level.getWave();
            this.waveSize = this.wave.getBloons().size();
            lastSpawnTime = System.currentTimeMillis(); // Memorizziamo il tempo di inizio dello spawn
            this.bloonsSpawned = 0;
            this.deadBloons = 0;
        }
    }
    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
    public void setLevel(String difficulty, Path path){
        this.level = new LevelImpl(difficulty, path);
    }

    public void update(long time) {
        // Controllo dello spawn dei bloon
        if (waveInProgress && bloonSpawnInProgress) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSpawnTime >= bloonSpawnInterval) {
                spawnBloons();
                lastSpawnTime = currentTime;
            }
        }
        System.out.println("\nSize waveImpl: " + this.wave.getBloons().size() + " Size bloonsSpawned: " + this.bloonsSpawned + " Size aliveBloons: " + this.aliveBloons.size());
        // Aggiornamento delle torri
        for (Tower tower : towers) {
            // tower.update(time);
        }

        // Aggiornamento delle wave e dei bloons
        if (waveInProgress  /*&& !this.wave.isOver()*/) {
            for (Bloon bloon : aliveBloons) {
                ((BloonImpl) bloon).update(time);
            }
        }

        // Controllo dei bloon morti e arrivati a fine percorso
        //   aliveBloons.removeIf(Bloon::isDead);
        Iterator<Bloon> iterator = aliveBloons.iterator();
        while (iterator.hasNext()) {
            Bloon bloon = iterator.next();
            if (bloon.hasReachedEnd()) {
                System.out.println("\nBloon reached end of path: " + bloon.getPosition());
                int healthDecrease = 1;
                player.loseHealth(healthDecrease);
                //wave.removeBloon(bloon);
                iterator.remove(); // Removing the bloon from the aliveBloons list
                System.out.println("Bloon has reached the end. Bloons in map: " + aliveBloons.size()); // Print statement
                this.deadBloons++;
            } else if (bloon.isDead()) {
                int moneyIncrease = 1;
                player.gainCoins(bloon.getType().getMoney());
                player.gainScore(1);
                //wave.removeBloon(bloon);
                iterator.remove(); // Removing the bloon from the aliveBloons list
                System.out.println("Bloon is dead. Bloons in map: " + aliveBloons.size()); // Print
                this.deadBloons++;
            }
        }

        System.out.println("\n waveInProgress: " + waveInProgress + " bloonSpawnInProgress: " + bloonSpawnInProgress + " aliveBloons: " + aliveBloons.size() + " waveNull: " + (this.wave == null));
        // Controllo fine wave
        if (waveInProgress && this.wave != null && deadBloons == waveSize) {
            bloonSpawnInProgress = false;
            waveInProgress = false;
            lastWaveEndTime = System.currentTimeMillis(); // Memorizziamo il tempo di fine wave
            level.waveFinished(); // wave finsihed
        }

        startWave();

    }


    /*private void spawnBloons() {
        if (wave != null && !wave.isOver()) {
            List<Bloon> newBloons = wave.getBloons();
            aliveBloons.addAll(newBloons);
        } else {
            bloonSpawnInProgress = false;
        }
    }*/
    private void spawnBloons() {
        if (this.wave != null /*&& !wave.isOver()*/) {
            List<Bloon> newBloons = wave.getBloons();
            if(bloonsSpawned < newBloons.size()) {
                Bloon bloon = newBloons.get(bloonsSpawned);
                aliveBloons.add(bloon);
                bloonsSpawned++;
                System.out.println("\nSpawned Bloon position: " + bloon.getPosition());
            }
        } else {
            bloonSpawnInProgress = false;
        }
    }


    public List<Bloon> getAliveBloons(){
        return this.aliveBloons;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }


    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(towers);
        if (waveInProgress && !wave.isOver()) {
            entities.addAll(aliveBloons);
        }
        return entities;
    }

    public GameState gameState() {
        if (player.getHealth() <= 0) {
            return GameState.GAME_OVER;
        } else {
            return GameState.PLAYING;
        }
    }

    public int getLife() {
        return player.getHealth();
    }

    public int getMoney() {
        return player.getCoins();
    }

    public LevelImpl getLevel() {
        return level;
    }

    public WaveImpl getWave() {
        return this.wave;
    }

    public Path getPath() {
        return this.path;
    }

    public List<Tower> getTowers() {
        return this.towers;
    }

    public List<Bullet> getBullets(){return this.bullets;}

    public Player getPlayer() {
        return this.player;
    }

    public void initGame(String difficulty, String mapName){
        System.out.println("Sto passando " + mapName);
        this.mapManager = new MapManagerImpl(mapName);
        //System.out.println("Il mapName di questo mapManager è: " + this.mapManager.getMapName());
        if(this.mapManager != null){
            System.out.println("\n\n\nMAP MANAGER NON è NULL\n\n");
        }
        this.setPath(this.mapManager.getBloonPath());
        System.out.println("LEGGO IL PATH DENTRO INITGAME:\n\n" + this.path);
        if(this.path == null){
            System.out.println("PATH VUOTO");
        }
        //this.setLevel(difficulty,this.path); MODIFICATO
        this.setLevel(difficulty, this.path);
        this.startWave();
    }

    public Tower isTower(int x, int y) {
        for (Tower tower : towers) {
            int towerX = (int) tower.getPosition().get().getX();
            int towerY = (int) tower.getPosition().get().getY();

            // Check if the clicked position is within the tolerance range of the tower's position
            if (Math.abs(towerX - x) <= 16 && Math.abs(towerY - y) <= 50) {
                return tower;
            }
        }
        return null;
    }

    public void towerShoot(){
        bullets.clear();
        for (Tower tower : towers) {
            if (tower instanceof ShootingTower shootingTower) {
                List<Bloon> bloonsInRange = findBloonsInRange(shootingTower);

                // Hit Bloon with the greatest currentPathIndex
                if (!bloonsInRange.isEmpty()) {
                    Bloon targetBloon = findTargetBloon(bloonsInRange);
                    System.out.println("TOWER POSITION: "+tower.getPosition().get().getX());
                    System.out.println("BLOON POSITION :"+targetBloon.getPosition().get().getX()+" BLOON HEALTH :"+targetBloon.getHealth());
                    BufferedImage bulletImage = null;
                    try {
                        bulletImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/towers/bullet.png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Bullet bullet = new Bullet(tower.getPosition().get(),bulletImage);
                    bullet.setTargetPosition(targetBloon.getPosition().get());
                    bullets.add(bullet);
                    //bullet.updatePosition(1,graphics);
                    targetBloon.hit(((ShootingTower) tower).getPower());

                    System.out.println("BLOON POSITION :"+targetBloon.getPosition().get().getX()+"BLOON HEALTH AFTER HIT :"+targetBloon.getHealth());
                }
            }
        }
    }

    public void towerHelp(){
        for (Tower tower1 : towers){
            if (tower1 instanceof HelpingTower helpingTower1){
                List<ShootingTower> towersInRange = findTowersInRange((HelpingTower) tower1);
                for (ShootingTower shootingTower : towersInRange){
                    if(helpingTower1.getFunction().equals("Range")){
                        shootingTower.setHittingRange(helpingTower1.getFunctionFactor()+10,helpingTower1.getFunctionFactor()+10);
                    }else{
                        shootingTower.setPower(helpingTower1.getFunctionFactor()+10);
                    }
                }
            }
        }
    }

    private List<ShootingTower> findTowersInRange(HelpingTower helpingTower){
        List<ShootingTower> towersInRange = new ArrayList<>();
        for (Tower tower : towers){
            if(tower instanceof ShootingTower && isTowerInRange(helpingTower,tower)){
                towersInRange.add((ShootingTower) tower);
            }
        }
        return towersInRange;
    }

    private List<Bloon> findBloonsInRange(ShootingTower tower) {
        List<Bloon> bloonsInRange = new ArrayList<>();
        for (Bloon bloon : this.aliveBloons) {
            if(isBloonInRange(bloon,tower)){
                bloonsInRange.add(bloon);
            }
        }
        return bloonsInRange;
    }

    private boolean isTowerInRange(HelpingTower helpingTower, Tower shootingTower){
        return Math.abs(shootingTower.getPosition().get().getX() - helpingTower.getPosition().get().getX()) <= (int) helpingTower.getHittingRange().getX()*16
                && Math.abs(shootingTower.getPosition().get().getY() - helpingTower.getPosition().get().getY()) <= (int) helpingTower.getHittingRange().getY()*16;
    }

    private boolean isBloonInRange(Bloon bloon, ShootingTower shootingTower){
        return Math.abs(shootingTower.getPosition().get().getX() - bloon.getPosition().get().getX()) < (int) shootingTower.getHittingRange().getX()*16
                && Math.abs(shootingTower.getPosition().get().getY() - bloon.getPosition().get().getY()) < (int) shootingTower.getHittingRange().getY()*16;
    }

    private Bloon findTargetBloon(List<Bloon> bloons) {
        Bloon targetBloon = null;
        int maxCurrentPathIndex = -1;

        // Find bloon with the greatest currentPathIndex
        for (Bloon bloon : bloons) {
            if (bloon.getCurrentPathIndex() > maxCurrentPathIndex) {
                maxCurrentPathIndex = bloon.getCurrentPathIndex();
                targetBloon = bloon;
            }
        }

        return targetBloon;
    }


    public MapManager getMapManager(){
        return this.mapManager;
    }
}