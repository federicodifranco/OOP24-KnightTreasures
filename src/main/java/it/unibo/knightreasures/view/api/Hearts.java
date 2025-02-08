package it.unibo.knightreasures.view.api;

import java.awt.Graphics;

/**
 * Interface representing the player's health (hearts) in the game.
 * It provides methods to update, retrieve, and render the hearts.
 */
public interface Hearts {

    /**
     * Sets the current number of hearts (health points) for the player.
     *
     * @param currentHearts the number of hearts to set.
     */
    void setCurrentHearts(int currentHearts);

    /**
     * Retrieves the current number of hearts the player has.
     *
     * @return the current number of hearts.
     */
    int getCurrentHearts();

    /**
     * Draws the hearts on the screen.
     *
     * @param g the {@link Graphics} object used for rendering.
     */
    void draw(Graphics g);
}
