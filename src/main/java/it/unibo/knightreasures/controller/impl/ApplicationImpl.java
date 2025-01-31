package it.unibo.knightreasures.controller.impl;

import java.awt.Graphics;

import it.unibo.knightreasures.heart.core.impl.Gameplay;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.GameLoop;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.impl.ApplicationPanel;
import it.unibo.knightreasures.view.impl.ApplicationWindow;
import it.unibo.knightreasures.view.impl.LevelManager;

public class ApplicationImpl implements Runnable {

    private ApplicationPanel applicationPanel;
    private ApplicationWindow applicationWindow;
    private Thread gameThread;
    private Gameplay gameplay;

    public ApplicationImpl() {
        initClasses();
        applicationPanel = new ApplicationPanel(this);
        applicationWindow = new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        gameplay = new Gameplay(this);
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
                
                break;
        
            case PLAYING:
                this.gameplay.update();
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                
                break;
        
            case PLAYING:
                this.gameplay.draw(g);
                break;
        }
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
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

    public void windowLostFocus() {
        if (Gamestate.state == Gamestate.PLAYING) {
            this.gameplay.getPlayer().resetDirBooleans();
        }
    }

    public Gameplay getPlaying() {
        return this.gameplay;
    }
}
