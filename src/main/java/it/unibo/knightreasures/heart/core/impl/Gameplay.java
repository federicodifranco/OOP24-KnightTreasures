package it.unibo.knightreasures.heart.core.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.api.View;
import it.unibo.knightreasures.view.impl.LevelManager;

/**
 * Handles the gameplay logic, including player movement, interactions,
 * and rendering of the game world.
 */
public final class Gameplay extends State implements View {

    /** The player entity. */
    private final PlayerEntity player;

    /** The level manager that controls level rendering and updates. */
    private final LevelManager levelManager;

    /**
     * Constructs a new Gameplay instance.
     *
     * @param game the main application instance.
     */
    public Gameplay(final ApplicationImpl game) {
        super(game);
        this.player = new PlayerEntity(Player.INIT_X, Player.INIT_Y, Player.WIDTH, Player.HEIGHT);
        this.levelManager = new LevelManager(getGame());
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    /**
     * Updates the player and level state.
     */
    @Override
    public void update() {
        player.update();
        levelManager.update();
    }

    /**
     * Draws the game elements, including the player and level.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    /**
     * Handles keyboard input for player movement and game state changes.
     *
     * @param e the key event triggered by the player.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeft(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
            case KeyEvent.VK_BACK_SPACE -> Gamestate.setState(Gamestate.MENU);
            default -> {
                // No action for other keys
            }
        }
    }

    /**
     * Handles key release events to stop player movement.
     *
     * @param e the key event triggered by the player.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
            default -> {
                // No action for other keys
            }
        }
    }

    /**
     * Gets the player entity instance.
     *
     * @return the player entity.
     */
    public PlayerEntity getPlayer() {
        return this.player;
    }

    /**
     * Resets the player's movement when the game window loses focus.
     */
    public void windowLostFocus() {
        player.resetDirBooleans();
    }

    /**
     * Handles mouse clicks for player interactions.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            player.setAttacking(true);
        }
    }

    /**
     * Handles mouse press events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        // Currently not implemented
    }

    /**
     * Handles mouse release events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        // Currently not implemented
    }

    /**
     * Handles mouse movement events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        // Currently not implemented
    }
}
