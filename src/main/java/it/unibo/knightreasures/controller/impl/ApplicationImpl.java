package it.unibo.knightreasures.controller.impl;

import java.awt.Graphics;

import it.unibo.knightreasures.utilities.ModelConstants.GameLoop;
import it.unibo.knightreasures.view.impl.ApplicationPanel;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.view.impl.ApplicationWindow;

public class ApplicationImpl implements Runnable {

    private ApplicationPanel applicationPanel;
    private ApplicationWindow applicationWindow;
    private Thread gameThread;
    private PlayerEntity player;

    public ApplicationImpl() {
        initClasses();
        applicationPanel = new ApplicationPanel();
        applicationWindow = new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player = new PlayerEntity(0, 0);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.updateGame();
    }

    public void render(Graphics g){
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
                update++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
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
