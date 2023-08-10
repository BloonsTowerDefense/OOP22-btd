package btd.controller;

import btd.Main;
import btd.model.GameModel;
import btd.view.GameCondition;
import btd.view.View;
import btd.model.score.RankModel;
import btd.controller.score.RankController;

/**
 * Represents the game engine, which is responsible for the game loop and the game state.
 */
public class Game extends Thread {

    private final long fTime = 200;
    private final long frameTime;
    private final GameModel gameModel;
    private final View view;
    private boolean running;
    private Thread gameThread;
    private GameCondition gameCondition;

    /**
     * Creates a new game engine.
     */
    public Game() {
        this.frameTime = fTime;
        this.gameCondition = GameCondition.MENU;
        this.gameModel = new GameModel();
        this.view = new View(this);
        view.setGameEngine(this);
        this.view.renderMenu();
    }

    /**
     * Waits for the next frame.
     * @param currentTime the start time of the current frame
     */
    private void waitForNextFrame(final long currentTime) {
        long dt = System.currentTimeMillis() - currentTime;
        if (dt < frameTime) {
            try {
                Thread.sleep(frameTime - dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * The loop of the game.
     */
    @Override
    public void start() {
        long lastUpdateTime = System.currentTimeMillis();
        while (runningGame()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;
            this.update(elapsedTime);
            this.waitForNextFrame(currentTime);
            lastUpdateTime = currentTime;
            this.render();
        }
    }

    /**
     * Check if the game is running.
     * @return {@code true} if the game is running, {@code false} otherwise.
     */
    public boolean runningGame() {
        return this.gameCondition != GameCondition.EXIT;
    }

    /**
     * Render the current view based on the game condition.
     */
    public void render() {
        switch (this.gameCondition) {
            case MENU:
                this.view.renderMenu();
                break;
            case PLAY:
                this.view.renderGame();
                break;
            case OVER:
                this.view.renderGameOver();
                break;
            default:
                break;
        }
    }

    /**
     * Updates the game state based on the elapsed time.
     * @param elapsedTime The elapsed time since the last update.
     */
    public void update(final long elapsedTime) {
        switch (this.gameCondition) {
            case PLAY:
                if (this.getGameModel().getGameCondition() == GameCondition.PLAY) {
                    this.gameModel.update(elapsedTime);
                } else {
                    this.gameCondition = GameCondition.OVER;
                }
                break;
            default:
                break;
        }
    }

    /**
     * restart the game.
     */
    public void restart() {
        this.view.restart();
        this.gameCondition = GameCondition.MENU;
        Main.startGame();
    }

    /**
     * exit the game.
     */
    public void exit() {
        this.gameCondition = GameCondition.EXIT;
        this.view.dispose();
        System.exit(0);
    }

    /**
     * return the game model.
     * @return the game model.
     */
    public GameModel getGameModel() {
        return this.gameModel;
    }

    /**
     * set the game condition.
     * @param gameCondition the game condition.
     */
    public void setGameCondition(final GameCondition gameCondition) {
        this.gameCondition = gameCondition;
    }

    /**
     * return the game condition.
     * @return the game condition.
     */
    public GameCondition getGameCondition() {
        return this.gameCondition;
    }

    /**
     * return the view.
     * @return the view.
     */
    public View getView() {
        return this.view;
    }

    /**
     * return the rank model.
     * @return the rank model.
     */
    public RankModel getRankModel() {
        return this.gameModel.getRankModel();
    }

    /**
     * return the rank controller.
     * @return the rank controller.
     */
    public RankController getRankController() {
        return this.gameModel.getRankController();
    }

}