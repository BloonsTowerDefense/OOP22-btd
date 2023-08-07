package btd.model;

import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.Entity;
import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.model.entity.Tower;
import btd.model.map.MapManager;
import btd.model.map.MapManagerImpl;
import btd.model.map.Path;
import btd.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private LevelImpl level;
    private WaveImpl wave;
    private Path path;
    private List<Tower> towers;
    private Player player;
    private boolean waveInProgress;
    private boolean bloonSpawnInProgress;
    private List<Bloon> aliveBloons;
    private int bloonsSpawned;
    private long lastSpawnTime;
    private long lastWaveEndTime;
    private final long bloonSpawnInterval = 1500; // 1.5 secondi
    private final long waveInterval = 10000; // 10 secondi
    private String difficulty;
    private MapManager mapManager;

    public enum GameState {
        PLAYING,
        GAME_OVER,
        VICTORY
    }

    public GameModel() {
        this.towers = new ArrayList<>();
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
            wave = (WaveImpl) level.getWave();
            lastSpawnTime = System.currentTimeMillis(); // Memorizziamo il tempo di inizio dello spawn
            this.bloonsSpawned = 0;
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

        // Aggiornamento delle torri
        for (Tower tower : towers) {
            // tower.update(time);
        }

        // Aggiornamento delle wave e dei bloons
        if (waveInProgress && !wave.isOver()) {
            for (Bloon bloon : aliveBloons) {
                ((BloonImpl) bloon).update(time);
            }
        }

        // Controllo dei bloon morti e arrivati a fine percorso
        aliveBloons.removeIf(Bloon::isDead);
        aliveBloons.forEach(bloon -> {
            if (bloon.hasReachedEnd()) {
                int healthDecrease = 1;
                player.loseHealth(healthDecrease);
                wave.removeBloon(bloon);
            } else if (bloon.isDead()) {
                int moneyIncrease = 1;
                player.gainCoins(bloon.getType().getMoney());
                player.gainScore(1);
                wave.removeBloon(bloon);
            }
        });

        // Controllo fine wave
        if (waveInProgress && wave.isOver() && aliveBloons.isEmpty()) {
            bloonSpawnInProgress = false;
            waveInProgress = false;
            lastWaveEndTime = System.currentTimeMillis(); // Memorizziamo il tempo di fine wave
        } else {
            startWave(); // Inizia una nuova wave se possibile
        }
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
        if (wave != null && !wave.isOver()) {
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
        return wave;
    }

    public Path getPath() {
        return path;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public Player getPlayer() {
        return player;
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

    public MapManager getMapManager(){
        return this.mapManager;
    }
}
