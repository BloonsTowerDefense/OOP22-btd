//Batusha
package btd.model;

import btd.model.entity.Bloon;
import java.util.Iterator;
import java.util.List;

public class Game implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;
    private List<Bloon> bloons;  // Lista dei bloons nel gioco

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

        while (running) {
            update();
            render();
        }
    }

    private void init() {
        // componenti del gioco finestra risorse ecc
    }

    private void update() {
        checkBloons();  // Verifica se i bloons sono morti o hanno raggiunto la fine
        // risorse giocatore
    }

    private void render() {
        // game visuals
    }

    public void checkBloons() {
        Iterator<Bloon> iterator = bloons.iterator();

        while (iterator.hasNext()) {
            Bloon bloon = iterator.next();

            if (bloon.hasReachedEnd()) {
                player.loseHealth(10); // Ogni bloon fa 10 danni
                iterator.remove(); // Rimuove il bloon dalla lista
            } else if (bloon.isDead()) {
                player.gainCoins(bloon.getMoney()); // Aggiunge i soldi del bloon al giocatore
                iterator.remove(); // Rimuove il bloon dalla lista
            }
        }
    }

}
