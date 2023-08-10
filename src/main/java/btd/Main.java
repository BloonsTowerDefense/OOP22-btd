//Batusha
package btd;

import btd.model.Game;
import btd.view.View;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Game gameEngine = new Game();
        gameEngine.start();
    }

}
