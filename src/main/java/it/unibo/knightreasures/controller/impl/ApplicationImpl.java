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

    /**
     * The game panel where the game is drawn.
     */
    private final ApplicationPanel applicationPanel;

    /**
     * The gameplay instance managing the game logic.
     */
    private final GameplayImpl gameplay;

    /**
     * The menu instance managing the menu logic.
     */
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

    /**
     * Updates the game state based on the current gamestate.
     */
    @Override
    public void update() {
        switch (Gamestate.getState()) {
            case MENU:
                this.menu.update();
                break;
            case SETTINGS:
                this.settings.update();
                break;
            case PLAYING:
                this.gameplay.update();
                break;
            default:
                break;
        }
    }

    /**
     * Renders the game graphics based on the current gamestate.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void render(final Graphics g) {
        switch (Gamestate.getState()) {
            case MENU:
                this.menu.draw(g);
                break;
            case SETTINGS:
                this.settings.draw(g);
                break;
            case PLAYING:
                this.gameplay.draw(g);
                break;
            default:
                break;
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

    /**
     * Handles the event when the game window loses focus.
     */
    @Override
    public void windowLostFocus() {
        if (Gamestate.getState() == Gamestate.PLAYING) {
            this.gameplay.getPlayer().resetDirBooleans();
        }
    }

    /**
     * Gets the gameplay instance.
     *
     * @return the gameplay instance.
     */
    @Override
    public GameplayImpl getPlaying() {
        return this.gameplay;
    }

    /**
     * Gets the menu instance.
     *
     * @return the menu instance.
     */
    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }

    /**
     * Gets the audio instance.
     *
     * @return the audio instance.
     */
    @Override
    public Audio getAudio() {
        return this.audio;
    }

    /**
     * Gets the audio utilities instance.
     *
     * @return the audio utilities instance.
     */
    @Override
    public AudioUtilities getAudioUtilities() {
        return this.audioUtilities;
    }
}
