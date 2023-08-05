//Batusha
package btd;

import btd.model.Game;
import btd.view.View;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Game gameEngine = new Game();
        gameEngine.start();
    }
}
