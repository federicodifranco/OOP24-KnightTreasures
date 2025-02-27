package it.unibo.knightreasures.heart.core.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.heart.core.api.Gameplay;
import it.unibo.knightreasures.model.impl.EnemyManagerImpl;
import it.unibo.knightreasures.model.impl.ObjectManagerImpl;
import it.unibo.knightreasures.model.impl.PlayerEntityImpl;
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
import it.unibo.knightreasures.view.impl.HeartsImpl;
import it.unibo.knightreasures.view.impl.LevelManagerImpl;
import it.unibo.knightreasures.view.impl.LvlCompleted;
import it.unibo.knightreasures.view.impl.Pause;

/**
 * Handles the gameplay logic, including player movement, interactions, and
 * rendering of the game world.
 */
public final class GameplayImpl extends State implements Gameplay, View {

    private PlayerEntityImpl player;
    private EnemyManagerImpl enemyManager;
    private ObjectManagerImpl objects;
    private LevelManagerImpl levelManager;
    private HeartsImpl hearts;
    private Pause pausedOverlay;
    private GameOver gameOverOverlay;
    private LvlCompleted lvlCompletedOverlay;
    private boolean gameOver, lvlComplete, paused;
    private int xLvlOffset, maxLvlOffsetX;

    /**
     * Constructs a new Gameplay instance.
     *
     * @param game the main application instance.
     */
    public GameplayImpl(final ApplicationImpl game) {
        super(game);
        initClasses();
        calcLvlOffset();
        loadStartLvl();
    }

    private void initClasses() {
        this.hearts = new HeartsImpl(Heart.INIT_X, Heart.INIT_Y);
        this.player = new PlayerEntityImpl(Player.INIT_X, Player.INIT_Y, Player.WIDTH, Player.HEIGHT, this, this.hearts);
        this.levelManager = new LevelManagerImpl(getGame());
        this.enemyManager = new EnemyManagerImpl(this);
        this.objects = new ObjectManagerImpl(this, levelManager);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
        this.pausedOverlay = new Pause(this, this.levelManager, this.getGame());
        this.gameOverOverlay = new GameOver(this, levelManager, getGame());
        this.lvlCompletedOverlay = new LvlCompleted(this, levelManager, getGame());
    }

    /**
     * Calculates the offset of the level.
     */
    private void calcLvlOffset() {
        this.maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
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

    private void loadStartLvl() {
        this.enemyManager.addEnemies(this.levelManager.getCurrentLevel());
        this.objects.loadObjects(this.levelManager.getCurrentLevel());
    }

    @Override
    public void update() {
        if (paused) {
            pausedOverlay.update();
        } else if (lvlComplete) {
            lvlCompletedOverlay.update();
        } else if (gameOver) {
            gameOverOverlay.update();
        } else if (!gameOver) {
            player.update();
            objects.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            hearts.setCurrentHearts(player.getLives());
            checkCloseToBorder();
        }
    }

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
        } else if (gameOver) {
            gameOverOverlay.draw(g);
        } else if (lvlComplete) {
            lvlCompletedOverlay.draw(g);
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (gameOver) {
            gameOverOverlay.keyPressed(e);
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A -> {
                    player.setLeft(true);
                }
                case KeyEvent.VK_D -> {
                    player.setRight(true);
                }
                case KeyEvent.VK_SPACE -> {
                    player.setJump(true);
                }
                case KeyEvent.VK_M -> {
                    player.setAttacking(true);
                }
                case KeyEvent.VK_P -> {
                    paused = !paused;
                }
                default -> {
                    // No action required for unhandled keys
                }
            }
        }
    }

    @Override
    public void unpauseGame() {
        this.paused = false;
    }

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
                default -> {
                    // No action required for unhandled keys
                }
            }
        }
    }

    @Override
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void setLevelCompleted(final boolean lvlComplete) {
        this.lvlComplete = lvlComplete;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public boolean isLevelCompleted() {
        return this.lvlComplete;
    }

    @Override
    public boolean isPaused() {
        return this.paused;
    }

    @Override
    public void loadNextLvl() {
        resetAll();
        levelManager.loadNextLvl();
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
    }

    @Override
    public PlayerEntityImpl getPlayer() {
        return this.player;
    }

    @Override
    public void checkEnemyHit(final Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void windowLostFocus() {
        player.resetDirBooleans();
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pausedOverlay.mousePressed(e);
            } else if (lvlComplete) {
                lvlCompletedOverlay.mousePressed(e);
            }
        } else {
            gameOverOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pausedOverlay.mouseReleased(e);
            } else if (lvlComplete) {
                lvlCompletedOverlay.mouseReleased(e);
            }
        } else {
            gameOverOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pausedOverlay.mouseMoved(e);
            } else if (lvlComplete) {
                lvlCompletedOverlay.mouseMoved(e);
            }
        } else {
            gameOverOverlay.mouseMoved(e);
        }
    }

    @Override
    public EnemyManagerImpl getEnemyManager() {
        return this.enemyManager;
    }

    @Override
    public ObjectManagerImpl getObjectManager() {
        return this.objects;
    }

    @Override
    public LevelManagerImpl getLevel() {
        return this.levelManager;
    }

    @Override
    public void setMaxLvlOffset(final int lvlOffset) {
        this.maxLvlOffsetX = lvlOffset;
    }

    @Override
    public void checkChestOpened(final Rectangle2D.Float hitbox) {
        this.objects.checkChestOpened(hitbox);
    }

    @Override
    public void checkCollectTreasure(final Rectangle2D.Float hitbox) {
        this.objects.checkObjectTouched(hitbox);
    }

    @Override
    public void checkSpikeTouched(final PlayerEntityImpl player, final HeartsImpl hearts) {
        this.objects.checkSpikeTouched(player, hearts);
    }

    @Override
    public void resetAll() {
        gameOver = false;
        paused = false;
        lvlComplete = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        objects.resetAllObjects();
    }
}
