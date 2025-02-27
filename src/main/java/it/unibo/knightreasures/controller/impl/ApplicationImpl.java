package it.unibo.knightreasures.controller.impl;

import java.awt.Graphics;

import it.unibo.knightreasures.controller.api.Application;
import it.unibo.knightreasures.heart.core.impl.GameplayImpl;
import it.unibo.knightreasures.utilities.AudioUtilities;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.GameLoop;
import it.unibo.knightreasures.view.impl.ApplicationPanel;
import it.unibo.knightreasures.view.impl.ApplicationWindow;
import it.unibo.knightreasures.view.impl.Audio;
import it.unibo.knightreasures.view.impl.Menu;
import it.unibo.knightreasures.view.impl.Settings;

/**
 * Main application controller that manages the game loop, rendering, and game
 * states.
 */
public final class ApplicationImpl implements Application, Runnable {

    private final ApplicationPanel applicationPanel;
    private final GameplayImpl gameplay;
    private final Menu menu;
    private final Settings settings;
    private final Audio audio;
    private final AudioUtilities audioUtilities;

    /**
     * Constructs a new ApplicationImpl instance and initializes the game.
     */
    public ApplicationImpl() {
        this.audioUtilities = new AudioUtilities();
        playSong();
        this.audio = new Audio(this);
        this.gameplay = new GameplayImpl(this);
        this.menu = new Menu(this);
        this.settings = new Settings(this);
        this.applicationPanel = new ApplicationPanel(this);
        new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    private void playSong() {
        switch (Gamestate.getState()) {
            case MENU ->
                this.audioUtilities.playMenuSong();
            case PLAYING ->
                this.audioUtilities.playLevelSong();
            default -> {
            }
        }
    }

    @Override
    public void update() {
        switch (Gamestate.getState()) {
            case MENU ->
                this.menu.update();
            case SETTINGS ->
                this.settings.update();
            case PLAYING ->
                this.gameplay.update();
            default -> {
            }
        }
    }

    @Override
    public void render(final Graphics g) {
        switch (Gamestate.getState()) {
            case MENU ->
                this.menu.draw(g);
            case SETTINGS ->
                this.settings.draw(g);
            case PLAYING ->
                this.gameplay.draw(g);
            default -> {
            }
        }
    }

    /**
     * Starts the game loop in a separate thread.
     */
    private void startGameLoop() {
        final Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Runs the game loop, updating and rendering the game at a fixed rate.
     */
    @Override
    public void run() {
        final double timePerFrame = GameLoop.NANOSECOND / GameLoop.FPS_SET;
        final double timePerUpdate = GameLoop.NANOSECOND / GameLoop.UPS_SET;
        double deltaU = 0;
        double deltaF = 0;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (Gamestate.getState() != Gamestate.EXIT) {
            final long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                applicationPanel.repaint();
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }
        Runtime.getRuntime().exit(0);
    }

    @Override
    public void windowLostFocus() {
        if (Gamestate.getState() == Gamestate.PLAYING) {
            this.gameplay.getPlayer().resetDirBooleans();
        }
    }

    @Override
    public GameplayImpl getPlaying() {
        return this.gameplay;
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public Audio getAudio() {
        return this.audio;
    }

    @Override
    public AudioUtilities getAudioUtilities() {
        return this.audioUtilities;
    }
}
