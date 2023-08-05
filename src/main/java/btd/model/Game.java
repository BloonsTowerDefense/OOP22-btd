package btd.model;

import javax.swing.*;
import java.awt.*;

import btd.view.GameCondition;
import btd.view.View;
import btd.view.menu.MainMenu;
import btd.model.map.MapPanel;
import btd.model.entity.Bloon;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends Thread {

    private long frameTime;
    private long lastUpdateTime;
    private long currentTime;
    private GameModel gameModel;
    private View view;
    private boolean running;
    private Thread gameThread;
    private GameCondition gameCondition;
    private LevelImpl level;
    private List<Bloon> bloons = new CopyOnWriteArrayList<>(); // CopyOnWriteArrayList per evitare errori di Concurrent Modification durante update di bloon.


    public Game() {
        System.out.print("\ninizio costruttore");
        this.gameCondition = GameCondition.MENU;
        System.out.print("\n1 costruttore");
        this.gameModel = new GameModel();
        System.out.print("\n2 costruttore");
        this.view = new View(this);
        view.setGameEngine(this);
        System.out.print("\nfine costruttore");
        this.view.renderMenu();
    }
    /*public void start() {

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
        System.out.print("isRunning");
        this.gameCondition = GameCondition.PLAY;
        System.out.print("isRunning");
    }*/

    /*public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    private void waitForNextFrame(long currentTime) {
        long dt = System.currentTimeMillis() - currentTime;
        if (dt < frameTime) {
            try {
                Thread.sleep(frameTime - dt);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void start() {
        System.out.print("\nsono arrivato qui");
        this.lastUpdateTime = System.currentTimeMillis();
        //double interval = 1000000000/4; //60
        //double nextDraw = System.nanoTime() + interval;
        System.out.print("\nci sono quasi");
        while(exitGame()){
            //System.out.println("Is running");
            this.currentTime = System.currentTimeMillis();
            long elapsedTime = this.currentTime - currentTime;
            this.update(elapsedTime);
            this.waitForNextFrame(currentTime);
            this.lastUpdateTime = this.currentTime;
            this.render();
        }
        }

    public boolean exitGame(){
        return this.gameCondition != GameCondition.EXIT;
    }

    public void render(){
        switch (this.gameCondition){
            case MENU:
                this.view.renderMenu();
                break;
            case PLAY:
                this.view.renderGame();
        }
    }
    public void update(long elapsedTime){
        switch (this.gameCondition){
            case PLAY:
                this.gameModel.update(elapsedTime);
                break;
            default:
                break;
        }
    }
    private void init() {
        // componenti del gioco finestra risorse ecc
    }

    public GameModel getGameModel(){
        return this.gameModel;
    }


    /*public void update() {
        this.gameModel.update();
    }*/


    /*private void repaint() {
        // Redraw
        if (mapPanel != null) {
            mapPanel.repaint();
        }
    }*/

    public void setGameCondition(GameCondition gameCondition) {
        this.gameCondition = gameCondition;
    }

    public GameCondition getGameCondition() {
        return this.gameCondition;
    }

}
