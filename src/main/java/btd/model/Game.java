//Batusha
package btd.model;

public class Game implements Runnable {
    private boolean running;
    private Thread gameThread;

    public void start() {
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
        // risorse giocatore
    }

    private void render() {
        // game visuals
    }
}
