package it.unibo.knightreasures.heart.core.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.model.impl.EnemyManager;
import it.unibo.knightreasures.model.impl.ObjectManager;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Heart;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.LevelOffset;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.utilities.ViewConstants.Window;
import it.unibo.knightreasures.view.api.View;
import it.unibo.knightreasures.view.impl.GameOver;
import it.unibo.knightreasures.view.impl.Hearts;
import it.unibo.knightreasures.view.impl.LevelManager;
import it.unibo.knightreasures.view.impl.LvlCompleted;
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

    private final EnemyManager enemyManager;

    private final ObjectManager objects;

    /**
     * The level manager that controls level rendering and updates.
     */
    private final LevelManager levelManager;

    private final Hearts hearts;

    /**
     * The pause state of the game.
     */
    private final Pause pausedOverlay;

    private final GameOver gameOverOverlay;

    private boolean gameOver, lvlComplete, paused;

    private final LvlCompleted lvlCompletedOverlay;

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
        this.hearts = new Hearts(Heart.INIT_X, Heart.INIT_Y);
        this.player = new PlayerEntity(Player.INIT_X, Player.INIT_Y, Player.WIDTH, Player.HEIGHT, this, this.hearts);
        this.levelManager = new LevelManager(getGame());
        this.enemyManager = new EnemyManager(this);
        this.objects = new ObjectManager(this, levelManager);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        this.pausedOverlay = new Pause(this, this.levelManager, this.getGame());
        this.gameOverOverlay = new GameOver(this, levelManager, game);
        this.lvlCompletedOverlay = new LvlCompleted(this, levelManager, game);
        calcLvlOffset();
        loadStartLvl();
    }

    /**
     * Updates the player and level state.
     */
    @Override
    public void update() {
        if (paused)
            pausedOverlay.update();
        else if (lvlComplete)
            lvlCompletedOverlay.update();
        else if (gameOver)
            gameOverOverlay.update();
        else if (!gameOver) {
            player.update();
            objects.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            hearts.setCurrentHearts(player.getLives());
            checkCloseToBorder();
        }
    }

    /**
     * Draws the game elements, including the player and level.
     *
     * @param g the graphics object used for rendering.
     */
    @Override
    public void draw(final Graphics g) {
        g.drawImage(ResourceFuncUtilities.loadSources(Images.BACKGROUND), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT,
                null);
        levelManager.draw(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);
        objects.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        hearts.draw(g);
        if (paused) {
            g.setColor(new Color(0, 0, 0, LevelsValues.GREY_BACKGROUND));
            g.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pausedOverlay.draw(g);
        } else if (gameOver)
            gameOverOverlay.draw(g);
        else if (lvlComplete)
            lvlCompletedOverlay.draw(g);
    }

    /**
     * Handles keyboard input for player movement and game state changes.
     *
     * @param e the key event triggered by the player.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (gameOver) {
            gameOverOverlay.keyPressed(e);
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A ->
                    player.setLeft(true);
                case KeyEvent.VK_D ->
                    player.setRight(true);
                case KeyEvent.VK_SPACE ->
                    player.setJump(true);
                case KeyEvent.VK_M ->
                    player.setAttacking(true);
                case KeyEvent.VK_P ->
                    paused = !paused;
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
        if (!gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A ->
                    player.setLeft(false);
                case KeyEvent.VK_D ->
                    player.setRight(false);
                case KeyEvent.VK_SPACE ->
                    player.setJump(false);
            }
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void loadStartLvl() {
        this.enemyManager.addEnemies(this.levelManager.getCurrentLevel());
        this.objects.loadObjects(this.levelManager.getCurrentLevel());
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
        this.maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
    }

    /**
     * Gets the player entity instance.
     *
     * @return the player entity.
     */
    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
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
        if (!gameOver) {
            if (paused) pausedOverlay.mousePressed(e);
            else if (lvlComplete) lvlCompletedOverlay.mousePressed(e);
        } else gameOverOverlay.mousePressed(e);
    }

    /**
     * Handles mouse release events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        if (!gameOver) {
            if (paused) pausedOverlay.mouseReleased(e);
            else if (lvlComplete) lvlCompletedOverlay.mouseReleased(e);
        } else gameOverOverlay.mouseReleased(e);
    }

    /**
     * Handles mouse movement events.
     *
     * @param e the mouse event triggered by the player.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        if (!gameOver) {
            if (paused) pausedOverlay.mouseMoved(e);
            else if (lvlComplete) lvlCompletedOverlay.mouseMoved(e);
        } else gameOverOverlay.mouseMoved(e);
    }

    public EnemyManager getEnemyManager() {
        return this.enemyManager;
    }

    public ObjectManager getObjectManager() {
        return this.objects;
    }

    public LevelManager getLevel() {
        return this.levelManager;
    }

    public void setMaxLvlOffset(int lvlOffset) {
        this.maxLvlOffsetX = lvlOffset;
    }

}
