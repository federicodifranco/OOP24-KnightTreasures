package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents the player entity and its interactions within the game.
 */
public interface PlayerEntity {

    /**
     * Retrieves whether the player is moving to the right.
     *
     * @return true if the player is moving right, false otherwise.
     */
    boolean isRight();

    /**
     * Retrieves the number of lives the player currently has.
     *
     * @return The number of remaining lives.
     */
    int getLives();

    /**
     * Loads level data for collision and movement handling.
     *
     * @param lvlData The 2D array representing the level structure.
     */
    void loadLvlData(int[][] lvlData);

    /**
     * Causes the player to lose one heart.
     */
    void loseHeart();

    /**
     * Renders the player on the screen.
     *
     * @param g The graphics object used for rendering.
     * @param lvlOffset The level offset for rendering.
     */
    void render(Graphics g, int lvlOffset);

    /**
     * Resets the player's state, including movement, health, and position.
     */
    void resetAll();

    /**
     * Resets movement direction booleans.
     */
    void resetDirBooleans();

    /**
     * Sets the player's animation based on movement and actions.
     */
    void setAnimation();

    /**
     * Sets whether the player is attacking.
     *
     * @param attacking true if attacking, false otherwise.
     */
    void setAttacking(boolean attacking);

    /**
     * Sets whether the player is jumping.
     *
     * @param jump true if jumping, false otherwise.
     */
    void setJump(boolean jump);

    /**
     * Sets whether the player is moving left.
     *
     * @param left true if moving left, false otherwise.
     */
    void setLeft(boolean left);

    /**
     * Sets whether the player is moving right.
     *
     * @param right true if moving right, false otherwise.
     */
    void setRight(boolean right);

    /**
     * Sets the player's spawn point.
     *
     * @param spawn The new spawn location.
     */
    void setSpawn(Point spawn);

    /**
     * Updates the player's state, including movement and interactions.
     */
    void update();

    /**
     * Updates the player's lives to the default value.
     */
    void updateLives();
}
