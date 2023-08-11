package btd.model;

import btd.controller.score.RankController;
import btd.model.entity.Bloon;
import btd.model.entity.BloonImpl;
import btd.model.entity.Bullet;
import btd.model.entity.Tower;
import btd.model.entity.HelpingTower;
import btd.model.entity.ShootingTower;
import btd.model.map.MapManager;
import btd.model.map.MapManagerImpl;
import btd.model.map.Path;
import btd.model.score.RankModel;
import btd.view.GameCondition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.Iterator;

/**
 * This class rapresents the game model, it contains all the information about the game.
 */
public class GameModel {
    private static final Logger LOGGER = Logger.getLogger(GameModel.class.getName());
    private static final long WAVE_WAITING_TIME = 2000;
    private static final long BLOON_SPAWN_WAITING_TIME = 1500;
    private static final int X_TOLERANCE = 40;
    private static final int Y_TOLERANCE = 70;
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
    private String difficulty;
    private MapManager mapManager;
    private int waveSize;
    private int deadBloons;
    private final RankModel rankModel;
    private final RankController rankController;

    /**
     * Constructor of GameModel.
     */
    public GameModel() {
        this.towers = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.player = new Player();
        this.waveInProgress = false;
        this.bloonSpawnInProgress = false;
        this.aliveBloons = new ArrayList<>();
        this.lastSpawnTime = 0;
        this.lastWaveEndTime = 0;
        this.rankModel = RankModel.getRankModelIstance();
        this.rankController = new RankController(rankModel);
    }

    /**
     * Start a new wave.
     */
    public void startWave() {
        if (!waveInProgress && System.currentTimeMillis() - lastWaveEndTime >= WAVE_WAITING_TIME) {
            waveInProgress = true;
            bloonSpawnInProgress = true;
            aliveBloons.clear();
            this.wave = (WaveImpl) level.getWave();
            this.waveSize = this.wave.getBloons().size();
            lastSpawnTime = System.currentTimeMillis(); // Inizializzo il tempo dell'ultimo spawn
            this.bloonsSpawned = 0;
            this.deadBloons = 0;
        }
    }

    /**
     * Set difficulty of the game.
     * @param difficulty  difficulty of the game.
     */
    public void setDifficulty(final String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Set the path of the bloons.
     * @param path  path of the bloons.
     */
    public void setPath(final Path path) {
        this.path = path;
    }

    /**
     * return the difficulty of the game.
     * @return @difficulty
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     * Create a new level.
     * @param difficulty  difficulty of the game.
     * @param path path of the bloons.
     */
    public void setLevel(final String difficulty, final Path path) {
        this.level = new LevelImpl(difficulty, path);
    }

    /**
     * Update the gameModel.
     * @param time time since last update.
     */
    public void update(final long time) {
        // Controllo dello spawn dei bloon
        if (waveInProgress && bloonSpawnInProgress) {
            final long currentTime = System.currentTimeMillis();
            final long bloonSpawnInterval = BLOON_SPAWN_WAITING_TIME;
            if (currentTime - lastSpawnTime >= bloonSpawnInterval) {
                spawnBloons();
                lastSpawnTime = currentTime;
            }
        }

        if (waveInProgress) {
            for (final Bloon bloon : aliveBloons) {
                ((BloonImpl) bloon).update(time);
            }
        }

        final Iterator<Bloon> iterator = aliveBloons.iterator();
        while (iterator.hasNext()) {
            final Bloon bloon = iterator.next();
            if (bloon.hasReachedEnd()) {
                final int healthDecrease = 1;
                player.loseHealth(healthDecrease);
                iterator.remove(); // Removing the bloon from the aliveBloons list
                this.deadBloons++;
            } else if (bloon.isDead()) {
                player.gainCoins(bloon.getType().getMoney());
                player.gainScore(1);
                iterator.remove(); // Removing the bloon from the aliveBloons list
                this.deadBloons++;
            }
        }

        // Controllo fine wave
        if (waveInProgress && this.wave != null && deadBloons == waveSize) {
            bloonSpawnInProgress = false;
            waveInProgress = false;
            lastWaveEndTime = System.currentTimeMillis(); // Memorizziamo il tempo di fine wave
            level.waveFinished(); // wave finsihed
        }
        startWave();
    }
    private void spawnBloons() {
        if (this.wave != null) {
            final List<Bloon> newBloons = wave.getBloons();
            if (bloonsSpawned < newBloons.size()) {
                final Bloon bloon = newBloons.get(bloonsSpawned);
                aliveBloons.add(bloon);
                bloonsSpawned++;
            }
        } else {
            bloonSpawnInProgress = false;
        }
    }

    /**
     * Return the list of alive bloons.
     * @return alive bloons.
     */
    public List<Bloon> getAliveBloons() {
        return this.aliveBloons;
    }

    /**
     * Add a tower to the list of towers.
     * @param tower tower to add.
     */
    public void addTower(final Tower tower) {
        this.towers.add(tower);
        this.player.setCoins(this.player.getCoins() - tower.getPrice());
    }

    /**
     *  Return the game condition.
     * @return game condition.
     */
    public GameCondition getGameCondition() {
        if (player.getHealth() <= 0) {
            return GameCondition.OVER;
        } else {
            return GameCondition.PLAY;
        }
    }

    /**
     * Return the life of the player.
     * @return life of the player.
     */
    public int getLife() {
        return player.getHealth();
    }

    /**
     * Return the money of the player.
     * @return money of the player.
     */
    public int getMoney() {
        return player.getCoins();
    }

    /**
     * Return the current level.
     * @return level.
     */
    public LevelImpl getLevel() {
        return level;
    }

    /**
     * Return the current wave.
     * @return wave.
     */
    public WaveImpl getWave() {
        return this.wave;
    }

    /**
     * Return the path of the bloons.
     * @return path of the bloons.
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Return the list of towers.
     * @return list of towers.
     */
    public List<Tower> getTowers() {
        return this.towers;
    }

    /**
     * Return the list of bullets.
     * @return list of bullets.
     */
    public List<Bullet> getBullets() {
        return this.bullets;
    }

    /**
     * Return the player.
     * @return player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Initialize the game.
     * @param difficulty difficulty of the game.
     * @param mapName  name of the map.
     */
    public void initGame(final String difficulty, final String mapName) {
        this.mapManager = new MapManagerImpl(mapName);
        this.setPath(this.mapManager.getBloonPath());
        this.setLevel(difficulty, this.path);
        this.startWave();
    }

    /**
     * Check if the position is occupied by a tower.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return  tower if the position is occupied, null otherwise.
     */
    public Tower checkIfIsTower(final int x, final int y) {
        for (final Tower tower : towers) {
            final int towerX = (int) tower.getPosition().get().getX();
            final int towerY = (int) tower.getPosition().get().getY();

            // Check if the clicked position is within the tolerance range of the tower's position
            if (Math.abs(towerX - x) <= X_TOLERANCE && Math.abs(towerY - y) <= Y_TOLERANCE) {
                return tower;
            }
        }
        return null;
    }

    /**
     * Tower shoot.
     */
    public void towerShoot() {
        bullets.clear();
        for (final Tower tower : towers) {
            if (tower instanceof ShootingTower shootingTower) {
                final List<Bloon> bloonsInRange = findBloonsInRange(shootingTower);
                // Hit Bloon with the greatest currentPathIndex
                if (!bloonsInRange.isEmpty()) {
                    Bloon targetBloon = findTargetBloon(bloonsInRange);
                    try {
                        final BufferedImage bulletImage = ImageIO.read(Objects.requireNonNull(getClass()
                                .getResource("/towers/bullet.png")));
                        final Bullet bullet = new Bullet(tower.getPosition().get(), bulletImage);
                        bullet.setTargetPosition(targetBloon.getPosition().get());
                        bullets.add(bullet);
                        targetBloon.hit(((ShootingTower) tower).getPower());
                    } catch (IOException e) {
                        LOGGER.log(java.util.logging.Level.SEVERE, "Exception", e);
                    }
                }
            }
        }
    }

    /**
     * Tower helping.
     */
    public void towerHelp() {
        for (final Tower tower1 : towers) {
            if (tower1 instanceof HelpingTower helpingTower1) {
                final List<ShootingTower> towersInRange = findTowersInRange((HelpingTower) tower1);
                for (final ShootingTower shootingTower : towersInRange) {
                    if ("Range".equals(helpingTower1.getFunction())) {
                        shootingTower.setHittingRange(
                                helpingTower1.getFunctionFactor() + 10,
                                helpingTower1.getFunctionFactor() + 10
                        );
                    } else {
                        shootingTower.setPower(helpingTower1.getFunctionFactor() + 10);
                    }
                }
            }
        }
    }

    /**
     * Return the rank model.
     * @return rank model.
     */
    public RankModel getRankModel() {
        return this.rankModel;
    }

    /**
     * Return the rank controller.
     * @return rank controller.
     */
     public RankController getRankController() {
        return this.rankController;
    }

    /**
     * Find tower in range of helping tower.
     * @param helpingTower helping tower.
     * @return list of towers in range.
     */
    private List<ShootingTower> findTowersInRange(final HelpingTower helpingTower) {
        final List<ShootingTower> towersInRange = new ArrayList<>();
        for (final Tower tower : towers) {
            if (tower instanceof ShootingTower && isTowerInRange(helpingTower, tower)) {
                towersInRange.add((ShootingTower) tower);
            }
        }
        return towersInRange;
    }

    /**
     * Find the bloons in range of shooting tower.
     * @param tower shooting tower.
     * @return list of bloons in range.
     */
    private List<Bloon> findBloonsInRange(final ShootingTower tower) {
        final List<Bloon> bloonsInRange = new ArrayList<>();
        for (final Bloon bloon : this.aliveBloons) {
            if (isBloonInRange(bloon, tower)) {
                bloonsInRange.add(bloon);
            }
        }
        return bloonsInRange;
    }

    /**
     * Check if the tower is in range of helping tower.
     * @param helpingTower helping tower.
     * @param shootingTower shooting tower.
     * @return true if the tower is in range, false otherwise.
     */
    private boolean isTowerInRange(final HelpingTower helpingTower, final Tower shootingTower) {
        return Math
                .abs(shootingTower.getPosition().get().getX()
                        - helpingTower.getPosition().get().getX()) <= (int) helpingTower.getHittingRange().getX() * 16
                && Math.abs(shootingTower.getPosition().get().getY()
                        - helpingTower.getPosition().get().getY()) <= (int) helpingTower.getHittingRange().getY() * 16;
    }

    /**
     * Check if the bloon is in range of shooting tower.
     * @param bloon bloon.
     * @param shootingTower shooting tower.
     * @return true if the bloon is in range, false otherwise.
     */
    private boolean isBloonInRange(final Bloon bloon, final ShootingTower shootingTower) {
        return Math
                .abs(shootingTower.getPosition().get().getX()
                        - bloon.getPosition().get().getX()) < (int) shootingTower.getHittingRange().getX() * 16
                && Math.abs(shootingTower.getPosition().get().getY()
                        - bloon.getPosition().get().getY()) < (int) shootingTower.getHittingRange().getY() * 16;
    }

    /**
     * Find the bloon with the greatest currentPathIndex.
     * @param bloons list of bloons in range.
     * @return bloon with the greatest currentPathIndex.
     */
    private Bloon findTargetBloon(final List<Bloon> bloons) {
        Bloon targetBloon = null;
        int maxCurrentPathIndex = -1;

        // Find bloon with the greatest currentPathIndex
        for (final Bloon bloon : bloons) {
            if (bloon.getCurrentPathIndex() > maxCurrentPathIndex) {
                maxCurrentPathIndex = bloon.getCurrentPathIndex();
                targetBloon = bloon;
            }
        }

        return targetBloon;
    }
    /**
     * Return the map manager.
     * @return map manager.
     */
    public MapManager getMapManager() {
        return this.mapManager;
    }

    /**
     * Resets the parameter in order to restart the game.
     */
    public void restartGame() {
        this.towers = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.player = new Player();
        this.waveInProgress = false;
        this.bloonSpawnInProgress = false;
        this.aliveBloons = new ArrayList<>();
        this.lastSpawnTime = 0;
        this.lastWaveEndTime = 0;
    }
}
