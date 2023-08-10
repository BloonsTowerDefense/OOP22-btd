package btd;

import btd.controller.Game;

/**
 * The main class of the game.
 */
public class Main {

    /**
     * The main method of the game.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        startGame();
    }

    /**
     * Starts the game.
     */
    public static void startGame() {
        Game gameEngine = new Game();
        gameEngine.start();
    }

}
