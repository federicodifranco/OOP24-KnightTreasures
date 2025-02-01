package it.unibo.knightreasures.controller.impl;

import java.awt.Graphics;

import it.unibo.knightreasures.heart.core.impl.Gameplay;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.GameLoop;
import it.unibo.knightreasures.view.impl.ApplicationPanel;
import it.unibo.knightreasures.view.impl.ApplicationWindow;
import it.unibo.knightreasures.view.impl.Menu;

/**
 * Main application controller that manages the game loop, rendering, and game
 * states.
 */
public final class ApplicationImpl implements Runnable {

    /**
     * The game panel where the game is drawn.
     */
    private final ApplicationPanel applicationPanel;

    /**
     * The gameplay instance managing the game logic.
     */
    private final Gameplay gameplay;

    /**
     * The menu instance managing the menu logic.
     */
    private final Menu menu;

    /**
     * Constructs a new ApplicationImpl instance and initializes the game.
     */
    public ApplicationImpl() {
        this.gameplay = new Gameplay(this);
        this.menu = new Menu(this);
        this.applicationPanel = new ApplicationPanel(this);
        new ApplicationWindow(applicationPanel);
        applicationPanel.requestFocus();
        startGameLoop();
    }

    /**
     * Updates the game state based on the current gamestate.
     */
    public void update() {
        switch (Gamestate.getState()) {
            case MENU:
                this.menu.update();
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
    public void render(final Graphics g) {
        switch (Gamestate.getState()) {
            case MENU:
                this.menu.draw(g);
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

        while (true) {
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
    }

    /**
     * Handles the event when the game window loses focus.
     */
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
    public Gameplay getPlaying() {
        return this.gameplay;
    }

    /**
     * Gets the menu instance.
     *
     * @return the menu instance.
     */
    public Menu getMenu() {
        return this.menu;
    }
}
