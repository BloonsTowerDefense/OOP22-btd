//Batusha
package btd;

import btd.model.Game;
import btd.view.View;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.print("inizio main");
        View view = new View();
        Game gameEngine = new Game(view);
        System.out.print("\ntest game");
        gameEngine.run();
        System.out.print("\ntest start");
        System.out.print("\nfine main");
    }
}
