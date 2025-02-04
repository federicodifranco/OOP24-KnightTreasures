package it.unibo.knightreasures.heart.core.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.LevelOffset;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;
import it.unibo.knightreasures.view.impl.LevelManager;
import it.unibo.knightreasures.view.impl.Pause;

/**
 * Handles the gameplay logic, including player movement, interactions, and
 * rendering of the game world.
 */
public final class Gameplay extends State implements View {

    /**
     * The player entity.
     */
    private final PlayerEntity player;

    /**
     * The level manager that controls level rendering and updates.
     */
    private final LevelManager levelManager;

    /**
     * Check when the game is paused or not.
     */
    private boolean paused;

    /**
     * The pause state of the game.
     */
    private final Pause pause;

    /**
     * The level offset and the max offset of the level.
     */
    private int xLvlOffset, maxLvlOffsetX;

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
        this.pause = new Pause(this, this.levelManager, this.getGame());
        calcLvlOffset();
    }

    /**
     * Updates the player and level state.
     */
    @Override
    public void update() {
        if (!this.paused) {
            player.update();
            levelManager.update();
            checkCloseToBorder();
        } else {
            pause.update();
        }
    }

    /**
     * Draws the game elements, including the player and level.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        g.drawImage(ResourceFuncUtilities.loadSources(Images.BACKGROUND), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        if (paused) {
            g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
            g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.draw(g);
        }
    }

    /**
     * Handles keyboard input for player movement and game state changes.
     *
     * @param e the key event triggered by the player.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A ->
                player.setLeft(true);
            case KeyEvent.VK_D ->
                player.setRight(true);
            case KeyEvent.VK_SPACE ->
                player.setJump(true);
            case KeyEvent.VK_BACK_SPACE ->
                Gamestate.setState(Gamestate.MENU);
            case KeyEvent.VK_P ->
                this.paused = !this.paused;
            default -> {
                // No action for other keys
            }
        }
    }

    /**
     * Handles the pause state.
     */
    public void unpauseGame() {
        this.paused = false;
    }

    /**
     * Handles key release events to stop player movement.
     *
     * @param e the key event triggered by the player.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A ->
                player.setLeft(false);
            case KeyEvent.VK_D ->
                player.setRight(false);
            case KeyEvent.VK_SPACE ->
                player.setJump(false);
            default -> {
                // No action for other keys
            }
        }
    }

    /**
     * Check if the player is close to the border.
     */
    private void checkCloseToBorder() {
        final int playerX = (int) player.getHitbox().x;
        final int diff = playerX - xLvlOffset;

        if (diff > LevelOffset.RIGHT_BORDER) {
            xLvlOffset += diff - LevelOffset.RIGHT_BORDER;
        } else if (diff < LevelOffset.LEFT_BORDER) {
            xLvlOffset += diff - LevelOffset.LEFT_BORDER;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }

    /**
     * Calculates the offset of the level.
     */
    private void calcLvlOffset() {
        this.maxLvlOffsetX = (ResourceFuncUtilities.createLevel()[0].length - Window.TILES_IN_WIDTH) * Window.TILES_SIZE;
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
        if (this.paused) {
            this.pause.mousePressed(e);
        }
    }

    /**
     * Handles mouse release events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (this.paused) {
            this.pause.mouseReleased(e);
        }
    }

    /**
     * Handles mouse movement events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        if (this.paused) {
            this.pause.mouseMoved(e);
        }
    }
}
