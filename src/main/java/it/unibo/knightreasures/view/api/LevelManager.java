package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

import it.unibo.knightreasures.view.impl.LevelImpl;

/**
 * Manages the levels in the game, handling transitions, rendering, 
 * and retrieving level-specific information.
 */
public interface LevelManager {

    /**
     * Loads the next level, resetting the game state accordingly.
     */
    void loadNextLvl();

    /**
     * Draws the current level on the screen.
     *
     * @param g the {@link Graphics} object used for rendering.
     * @param lvlOffset the horizontal offset for level rendering.
     */
    void draw(Graphics g, int lvlOffset);

    /**
     * Updates the state of the level (currently empty, reserved for future updates).
     */
    void update();

    /**
     * Retrieves the current level.
     *
     * @return the {@link LevelImpl} representing the active level.
     */
    LevelImpl getCurrentLevel();

    /**
     * Gets the total number of levels available in the game.
     *
     * @return the number of levels.
     */
    int getAmountOfLvls();

    /**
     * Retrieves the index of the current level.
     *
     * @return the index of the active level.
     */
    int getLevelIndex();
}

