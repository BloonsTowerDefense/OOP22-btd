//Batusha
package btd.model;

//import btd.model.entity.Bloon;
//import btd.model.entity.BloonImpl;
//import btd.model.entity.BloonType;
//import btd.model.entity.Tower;
//import btd.model.map.Path;
//import btd.model.map.PathImpl;
//
//import btd.model.entity.Tower;
//import btd.model.entity.ShootingTower;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import btd.utils.Position;
//
//import javax.swing.*;

public class Game implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;

    public void start() {
        this.player = new Player();
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
    }

    private void init() {
        // componenti del gioco finestra risorse ecc
    }

}
