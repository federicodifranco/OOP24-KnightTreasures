package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

/**
 * Interface representing the stars system in the game, used to display
 * a rating based on player performance.
 */
public interface Stars {

    /**
     * Updates the state of the stars based on game conditions such as enemy status,
     * player lives, and collected treasures.
     *
     * @param enemiesInactive    {@code true} if all enemies are inactive (defeated), {@code false} otherwise.
     * @param playerLives        The number of lives the player has remaining.
     * @param collectedTreasure  The number of treasures collected by the player.
     */
    void updateStarStates(boolean enemiesInactive, int playerLives, int collectedTreasure);

    /**
     * Draws the stars representation on the screen.
     *
     * @param g The {@link Graphics} object used to render the stars.
     */
    void draw(Graphics g);
}
