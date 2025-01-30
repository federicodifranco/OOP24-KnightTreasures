package it.unibo.knightreasures.controller.impl;

import java.awt.Graphics;

import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.ModelConstants.GameLoop;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.impl.ApplicationPanel;
import it.unibo.knightreasures.view.impl.ApplicationWindow;
import it.unibo.knightreasures.view.impl.LevelManager;

public class ApplicationImpl implements Runnable {

    private ApplicationPanel applicationPanel;
    private ApplicationWindow applicationWindow;
    private Thread gameThread;
    private PlayerEntity player;
    private LevelManager levelManager;

    public ApplicationImpl() {
        initClasses();
        applicationPanel = new ApplicationPanel(this);
        applicationWindow = new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player = new PlayerEntity(200, 200, Player.WIDTH, Player.HEIGHT);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = GameLoop.NANOSECOND / GameLoop.FPS_SET;
        double timePerUpdate = GameLoop.NANOSECOND / GameLoop.UPS_SET;
        double deltaU = 0;
        double deltaF = 0;

        long previusTime = System.nanoTime();
        
        long lastCheck = System.currentTimeMillis();

        int fps = 0;
        int updates = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime -previusTime) / timePerUpdate;
            deltaF += (currentTime - previusTime) / timePerFrame;
            previusTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                applicationPanel.repaint();
                deltaF--;
                fps++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + fps + " | UPS: " + updates);
                fps = 0;
                updates = 0;
            }
        }
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void windowLostFocus() {
        player.resetDirBooleans();
    }
}
