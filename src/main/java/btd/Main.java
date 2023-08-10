//Batusha
package btd;

import btd.controller.Game;

public class Main {

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Game gameEngine = new Game();
        gameEngine.start();
    }

}
