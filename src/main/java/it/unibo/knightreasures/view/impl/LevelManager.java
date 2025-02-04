package it.unibo.knightreasures.view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.utilities.ModelConstants.LevelsValues;
import it.unibo.knightreasures.utilities.ResourceFuncUtilities;
import it.unibo.knightreasures.utilities.ViewConstants.Images;
import it.unibo.knightreasures.utilities.ViewConstants.Window;

/**
 * Manages the levels in the game, including rendering and loading level assets.
 */
public final class LevelManager {

    /** Reference to the main game application. */
    private final ApplicationImpl game;

    /** Array storing the level sprites. */
    private BufferedImage[] levelSprite;

    /** Represents the first level of the game. */
    private final Level levelOne;

    /**
     * Constructs a new LevelManager.
     *
     * @param game the game application instance.
     */
    public LevelManager(final ApplicationImpl game) {
        this.game = game;
        importOutsideSprite();
        levelOne = new Level(ResourceFuncUtilities.createLevel());
    }

    /**
     * Loads and imports external level sprites.
     */
    private void importOutsideSprite() {
        final BufferedImage img = ResourceFuncUtilities.loadSources(Images.ENVIRONMENT);
        levelSprite = new BufferedImage[LevelsValues.SPRITES];

        for (int j = 0; j < LevelsValues.SPRITES_ROWS; j++) {
            for (int i = 0; i < LevelsValues.SPRITES_COLUMNS; i++) {
                final int index = j * LevelsValues.SPRITES_COLUMNS + i;
                levelSprite[index] = img.getSubimage(
                        i * LevelsValues.SPRITES_SIZE,
                        j * LevelsValues.SPRITES_SIZE,
                        LevelsValues.SPRITES_SIZE,
                        LevelsValues.SPRITES_SIZE
                );
            }
        }
    }

    /**
     * Draws the current level on the screen.
     *
     * @param g the graphics object used for rendering.
     */
    public void draw(final Graphics g, final int lvlOffset) {
        for (int j = 0; j < Window.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                final int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index],
                        Window.TILES_SIZE * i - lvlOffset,
                        Window.TILES_SIZE * j,
                        Window.TILES_SIZE,
                        Window.TILES_SIZE,
                        null
                );
            }
        }
    }

    /**
     * Updates the state of the level (currently empty, reserved for future updates).
     */
    public void update() {
        // Future level updates will go here.
    }

    /**
     * Gets the current level instance.
     *
     * @return the current level.
     */
    public Level getCurrentLevel() {
        return this.levelOne;
    }
}
